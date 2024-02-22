package com.acmlab.file.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lixin
 * @since 2021-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public User(){

    }

    /**
    * 提供一个两个参数的构造函数 登录时可以只发用户名和密码两个参数
    */
    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
    * 三参构造，用于注册
    */
    User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


}
