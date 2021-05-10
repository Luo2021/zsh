package cn.zsh.controller;

import cn.zsh.pojo.User;
import cn.zsh.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author luoli
 * @date 2021/3/30 10:10
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户 -- 登录注册")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value= "通过主键id查找用户接口")
    @GetMapping("/queryById/{id}")
    @ResponseBody
    public User queryById(@PathVariable("id") @ApiParam(value ="主键id") Long id) {
        User user = this.userService.queryById(id);
        return user;
    }

    @ApiOperation(value= "查所有用户接口")
    @GetMapping("/queryAll")
    public List<User> queryAll() {
        return this.userService.queryAll();
    }

    @ApiOperation(value= "通过主键id删除用户接口")
    @GetMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") @ApiParam(value ="主键id") Long id) {
        this.userService.deleteById(id);
    }

    /**
     * 用户数据检查
     * @param data
     * @param type
     * type=1 date则是username;type=2 data则是phone
     * @return
     */
    @ApiOperation(value= "用户数据检查接口")
    @GetMapping("check/{data}/{type}")
    public ResponseEntity<Boolean> checkUserData(@PathVariable("data") String data, @PathVariable(value = "type") Integer type){
        Boolean result = this.userService.checkData(data,type);
        if (result == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 发送短信验证码
     * @param phone
     * @return
     */
    @ApiOperation(value= "发送短信验证码接口")
    @PostMapping("code")
    public ResponseEntity senVerifyCode(@RequestParam("phone") String phone){
        Boolean result = this.userService.sendVerifyCode(phone);
        if (result == null || !result){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
         return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 注册
     * @param user
     * @param code
     * @return
     */
    @ApiOperation(value = "注册接口")
    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody @Valid User user, @RequestParam("code") String code){
        Boolean result = this.userService.register(user,code);
        if(result == null || !result){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**
     * 用户验证
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value= "用户验证接口")
    @GetMapping("query")
    public ResponseEntity<User> queryUser(@RequestParam("username")String username,@RequestParam("password")String password){
        User user = this.userService.queryUser(username,password);
        if (user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(user);
    }
}
