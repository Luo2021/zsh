package cn.zsh.service.impl;

import cn.zsh.event.EventPublisher;
import cn.zsh.mapper.UserMapper;
import cn.zsh.pojo.User;
import cn.zsh.service.UserService;
import cn.zsh.utils.CodecUtils;
import cn.zsh.utils.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author luoli
 * @date 2021/3/30 17:04
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EventPublisher eventPublisher;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String KEY_PREFIX = "user:code:phone";

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Boolean checkData(String data, Integer type) {
        User user = new User();
        switch (type){
            case 1 :
                user.setUserName(data);
                break;
            case 2 :
                user.setPhone(data);
                break;
            default:
                return null;
        }
        return this.userMapper.selectCount(user) == 0;
    }

    /**
     * 发送短信验证码
     * @param phone
     */
    @Override
    public Boolean sendVerifyCode(String phone) {

        //1.生成验证码
        String code = NumberUtils.generateCode(6);

        try {
            Map<String,String> msg = new HashMap<>();
            msg.put("phone",phone);
            msg.put("code",code);
            //2.发送短信
            eventPublisher.publisher(msg);

            //3.将code存入redis
            this.stringRedisTemplate.opsForValue().set(KEY_PREFIX + phone,code,5, TimeUnit.MINUTES);

            return true;

        }catch (Exception e){
            logger.error("发送短信失败。phone：{}，code：{}",phone,code);
            return false;
        }
    }

    @Override
    public Boolean register(User user, String code) {
        String key = KEY_PREFIX + user.getPhone();
        //1.从redis中取出验证码
        String codeCache = this.stringRedisTemplate.opsForValue().get(key);
        //2.检查验证码是否正确
        if(!code.equals(codeCache)){
            //不正确，返回
            return false;
        }
        user.setId(null);
        user.setRegisterTime(new Date());
        //3.密码加密
        String encodePassword = CodecUtils.passwordBcryptEncode(user.getUserName().trim(),user.getPassWord().trim());
        user.setPassWord(encodePassword);
        //4.写入数据库
        boolean result = this.userMapper.insertSelective(user) == 1;
        //5.如果注册成功，则删掉redis中的code
        if (result){
            try{
                this.stringRedisTemplate.delete(KEY_PREFIX + user.getPhone());
            }catch (Exception e){
                logger.error("删除缓存验证码失败，code:{}",code,e);
            }
        }
        return result;
    }

    /**
     * 用户验证
     * @param username
     * @param password
     * @return
     */
    @Override
    public User queryUser(String username, String password) {
        //1.查询
        User record = new User();
        record.setUserName(username);
        User user = this.userMapper.selectOne(record);

        //2.校验用户名
        if (user == null){
            return null;
        }
        //3. 校验密码
        boolean result = CodecUtils.passwordConfirm(username + password,user.getPassWord());
        if (!result){
            return null;
        }

        //4.用户名密码都正确
        return user;
    }

    @Override
    public User queryById(Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> queryAll() {
        return this.userMapper.selectAll();
    }

    @Override
    public void deleteById(Long id) {
        this.userMapper.deleteByPrimaryKey(id);
    }
}

