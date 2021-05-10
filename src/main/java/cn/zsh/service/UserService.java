package cn.zsh.service;

import cn.zsh.pojo.User;

import java.util.List;

/**
 * @author luoli
 * @date 2021/3/30 10:10
 */
public interface UserService {
    /**
     * 检查用户名和手机号是否可用
     * @param data
     * @param type
     * @return
     */
    Boolean checkData(String data, Integer type);

    /**
     * 发送手机验证码
     * @param phone
     * @return
     */
    Boolean sendVerifyCode(String phone);

    /**
     * 用户注册
     * @param user
     * @param code
     * @return
     */
    Boolean register(User user, String code);

    /**
     * 用户验证
     * @param username
     * @param password
     * @return
     */
    User queryUser(String username, String password);

    User queryById(Long id);

    List<User> queryAll();

    void deleteById(Long id);
}
