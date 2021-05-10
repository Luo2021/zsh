package cn.zsh.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author luoli
 * @date 2021/3/30 10:10
 */
@Data
@Table(name = "tb_user")
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 用户名
    @Column(name = "userName")
    private String userName;

    // 密码
    @Column(name = "passWord")
    private String passWord;

    // 年龄
    private Integer age;

    // 性别，1男性，2女性
    private Integer sex;

    // 手机号码
    private String phone;

    // 注册时间
    @Column(name = "registerTime")
    private Date registerTime;

    // 上一次登录时间
    @Column(name = "registerTime")
    private Date lastLoginTime;

    //退出时间
    @Column(name = "lastLoginOutTime")
    private Date lastLoginOutTime;

    //在线总时长（单位是小时）
    @Column(name = "allTime")
    private Double allTime;

    // 角色(0:平台运营者, 1:直属单位管理小组, 2:捐助者, 3:受助人, 4:学校管理者, 5:守护者)
    private Integer roles;

    //备注
    @Transient
    private String note;

}
