package com.sin.shirotutorial.config;

import com.sin.shirotutorial.dao.UserMapper;
import com.sin.shirotutorial.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addStringPermission("user:add");
        //获取当前登录的对象
        Subject subject = SecurityUtils.getSubject();

        User user = (User) subject.getPrincipal();
        info.addStringPermission(user.getPrams());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User user = userMapper.selectUserByName(((UsernamePasswordToken) token).getUsername());
        //从token中取到用户名再去查用户密码
        if (user == null || user.getPasswd() == null || !user.getPasswd().equals(String.valueOf(userToken.getPassword()))) {
            return null;
        }

        return new SimpleAuthenticationInfo(user, user.getPasswd(), "");
    }
}