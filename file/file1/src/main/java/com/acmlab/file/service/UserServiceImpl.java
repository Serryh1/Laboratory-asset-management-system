package com.acmlab.file.service;

import com.acmlab.file.config.shrio.utils.PasswordUtils;
import com.acmlab.file.entity.User;
import com.acmlab.file.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lixin
 * @since 2021-08-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public String register(User user) {
        int count = userMapper.selectCount(new QueryWrapper<User>()
                .eq("username", user.getUsername())
                .last("LIMIT 1"));
        if (count != 0) {
            return "用户名重复";
        }
        user.setPassword(PasswordUtils.HashMD5(user.getUsername(), user.getPassword()));
        userMapper.insert(user);
        return "登录成功";
    }

    @Override
    public String rewite(String username, String password) {
        int count = userMapper.selectCount(new QueryWrapper<User>()
                .eq("username", username)
                .last("LIMIT 1"));
        if (count == 0) {
            return "未查找到该用户名，请确认用户名是否输入正确";

        } else {
            try {

                String updatepassword = PasswordUtils.HashMD5(username, password);
                userMapper.resetpassword(updatepassword, username);

            } catch (Exception e) {
                e.printStackTrace();
                return "修改密码失败";
            }
        }
        return "已修改成功，请返回登录";
    }
}
