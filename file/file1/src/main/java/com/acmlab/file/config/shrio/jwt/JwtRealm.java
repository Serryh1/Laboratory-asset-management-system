package com.acmlab.file.config.shrio.jwt;

import com.acmlab.file.entity.User;
import com.acmlab.file.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JwtRealm只负责校验JwtToken
 *
 * @program: NovelClient
 * @author: lixin
 * @create: 2021-06-01 15:30
 **/

public class JwtRealm extends AuthorizingRealm {
    @Autowired
    UserMapper userMapper;

    /**
     * 限定这个 Realm 只处理我们自定义的 JwtToken
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        // 获取当前用户
//        UserEntity currentUser = (UserEntity) SecurityUtils.getSubject().getPrincipal();
//        // UserEntity currentUser = (UserEntity) principals.getPrimaryPrincipal();
//        // 查询数据库，获取用户的角色信息
//        Set<String> roles = ShiroRealm.roleMap.get(currentUser.getName());
//        // 查询数据库，获取用户的权限信息
//        Set<String> perms = ShiroRealm.permMap.get(currentUser.getName());
        return new SimpleAuthorizationInfo();
    }

    /**
     * 认证
     * 此处的 SimpleAuthenticationInfo 可返回任意值，密码校验时不会用到它
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authcToken;
        if (jwtToken.getPrincipal() == null) {
            throw new AccountException("JWT token参数异常！");
        }
        // 从 JwtToken 中获取当前用户
        String username = jwtToken.getPrincipal().toString();
        System.out.println(" user auth : " + username);
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            throw new UnknownAccountException("用户不存在！");
        }
        return new SimpleAuthenticationInfo(user, username, getName());
    }
}