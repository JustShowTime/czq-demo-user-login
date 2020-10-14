 package com.example.demo.config;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

/**
 * @Author: czq
 * @Date: 2020/5/7
 * @Version: 1.0
 */


public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("-------------------------------> 执行授权逻辑");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //1.获取当前Principal，也就是登录的对象User，这个是认证的时候赋值进去的
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        //2.把相应的角色，权限赋值到SimpleAuthorizationInfo
        info.addStringPermission(principal.getRemark().toString());
        //3.返回SimpleAuthorizationInfo（方便后面其他模块进行权限验证）
        return info;
    }


    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------------------------------> 执行认证逻辑");
        //1.拿到UsernamePasswordToken，这个UsernamePasswordToken是用户登录时，设置进去的
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //2.从UsernamePasswordToken取得账户username，password
        User user = userService.selectUserByName(token.getUsername());
        //3.校验身份认证
        if (user == null) {
            return null;
        }

        if (user.getState() == 1) {
            throw new LockedAccountException("用户被锁定!!!");
        }
        //4.返回SimpleAuthenticationInfo,这个后续可以在授权模块用到principal
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
