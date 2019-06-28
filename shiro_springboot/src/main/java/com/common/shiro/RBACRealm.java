package com.common.shiro;

import com.module.demo.mapper.PermissionMapper;
import com.module.demo.mapper.RoleMapper;
import com.module.demo.mapper.UserMapper;
import com.module.demo.model.Permission;
import com.module.demo.model.Role;
import com.module.demo.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RBACRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /* 登录 */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String name = token.getUsername();
        String password = new String(token.getPassword());

        User select = new User();
        select.setUserName(name);
        User result = userMapper.selectUser(select);
        /*
         * ①用户不存在/密码错误都会报错
         * ②传入的result与token比对密码
         * */
        return new SimpleAuthenticationInfo(result, result.getUserPassword(), getName());
    }

    /* 授权 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User select = (User) principalCollection.getPrimaryPrincipal();

        List<Role> roleList = roleMapper.selectRoleList(select);
        Set<String> stringRoles = new HashSet<>();
        for (int i = 0; i < roleList.size(); i++) {
            stringRoles.add(roleList.get(i).getRoleName());
        }

        List<Permission> permissionList = permissionMapper.selectPermissionList(select);
        Set<String> stringPermissions = new HashSet<>();
        for (int i = 0; i < permissionList.size(); i++) {
            stringPermissions.add(permissionList.get(i).getPermissionUrl());
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(stringRoles);
        info.setStringPermissions(stringPermissions);
        return info;
    }

}
