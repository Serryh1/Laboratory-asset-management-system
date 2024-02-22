package com.acmlab.file.config.shrio;

import com.acmlab.file.entity.User;
import com.acmlab.file.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 *
 * @program: shiro-web
 * @author: lixin
 * @create: 2021-05-29 19:53
 **/
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    UserMapper userMapper;

    /**
     * 限定这个 Realm 只处理 UsernamePasswordToken
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        System.out.println("进入授权.");

        //授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //拿到当前登录对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        simpleAuthorizationInfo.addStringPermission("权限");
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        System.out.println("进入认证.");
        //用户认证
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userMapper.getUserByUsername(token.getUsername());
        if(user == null|| !username.equals(token.getUsername())) {
            // 抛出异常UnknownAccountException
            return null;
        }


        /*认证 SimpleAuthenticationInfo
        * 1.principal：认证的实体信息，可以是username，也可以是数据表对于的用户实体类对象
        * 2.credentials：密码
        * 3.realmName：当前realm对象的name，调用父类的getName（）方法。
        * 4.slat：盐值*/
        ByteSource salt = ByteSource.Util.bytes(username);
        return new SimpleAuthenticationInfo(username, user.getPassword(), salt, this.getName());
    }
}
