package com.hly.realm;

import com.hly.entity.User;
import com.hly.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.Action;

/**
 * @author :hly
 * @github :github.com/SiriusHly
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/8/29
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userId = (String) authenticationToken.getPrincipal();
        User user = userService.getUserById(userId);
        System.err.println(user.toString());
        if (user != null) {
            //保存当前用户到session
            SecurityUtils.getSubject().getSession().setAttribute("currentUser", user);
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserId(), user.getUserPassword(), "realmName");
            System.err.println(user.toString());
            return authenticationInfo;
        } else {
            return null;
        }

    }
}
