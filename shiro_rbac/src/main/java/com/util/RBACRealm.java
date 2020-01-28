package com.util;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

public class RBACRealm extends AuthorizingRealm {

    /*
     * ①认证（登录）
     * ②subject.login()时被调用
     * ③登录成功后，即可满足@RequiresAuthentication注解或subject.isAuthenticated()
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String name = token.getUsername();
        String password = new String(token.getPassword());

        /* 如果为空就是账号不存在，如果不相同就是密码错误 */
        String passwordInDB = new RBACDao().getPassword(name);
        if (null == passwordInDB || !passwordInDB.equals(password)) {
            throw new AuthenticationException();
        }

        /* 认证信息里存放账号密码，getName()返回当前类名 */
        return new SimpleAuthenticationInfo(name, password, getName());
    }

    /*
     * ①授权（权限）
     * ②@RequiresPermissions注解/subject.isPermitted()时被调用
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name = (String) principalCollection.getPrimaryPrincipal();
        Set<String> permissions = new RBACDao().listPermissions(name);
        Set<String> roles = new RBACDao().listRoles(name);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissions);
        info.setRoles(roles);
        return info;
    }

}
