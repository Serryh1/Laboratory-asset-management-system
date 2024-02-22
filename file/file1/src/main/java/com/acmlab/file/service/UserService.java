package com.acmlab.file.service;

import com.acmlab.file.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lixin
 * @since 2021-08-01
 */
public interface UserService extends IService<User> {
    String register(User user);

    String rewite(String id, String password);

}
