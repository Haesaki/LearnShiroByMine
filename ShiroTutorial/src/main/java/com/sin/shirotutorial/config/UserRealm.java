package com.sin.shirotutorial.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class UserRealm extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addStringPermission("user:add");
        //获取当前登录的对象
        Subject subject = SecurityUtils.getSubject();

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");

        String name = "admin";
        String password = "admin";

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //从token中取到用户名再去查用户密码
        if (name.equals(userToken.getUsername()) && password.equals(userToken.getPassword())) {
            return null;
        }

        return new SimpleAuthenticationInfo("", password, "");
    }
}