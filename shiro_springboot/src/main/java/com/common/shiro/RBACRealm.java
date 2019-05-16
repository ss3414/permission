package com.common.shiro;

import com.demo.mapper.PermissionMapper;
import com.demo.mapper.RoleMapper;
import com.demo.mapper.UserMapper;
import com.demo.model.ShiroPermission;
import com.demo.model.ShiroRole;
import com.demo.model.ShiroUser;
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

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String name = token.getUsername();
        String password = new String(token.getPassword());

        ShiroUser select = new ShiroUser();
        select.setUserName(name);
        ShiroUser result = userMapper.selectUser(select);
        /*
         * ①用户不存在/密码错误都会报错
         * ②传入的result与token比对密码
         * */
        return new SimpleAuthenticationInfo(result, result.getUserPassword(), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ShiroUser select = (ShiroUser) principalCollection.getPrimaryPrincipal();

        List<ShiroRole> roleList = roleMapper.selectRoleList(select);
        Set<String> stringRoles = new HashSet<>();
        for (int i = 0; i < roleList.size(); i++) {
            stringRoles.add(roleList.get(i).getRoleName());
        }

        List<ShiroPermission> permissionList = permissionMapper.selectPermissionList(select);
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
