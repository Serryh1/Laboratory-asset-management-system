package com.acmlab.file.mapper;

import com.acmlab.file.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lixin
 * @since 2021-08-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User getUserByUsername(String username);

    void resetpassword(@Param("password") String password,@Param("username") String username );
}


