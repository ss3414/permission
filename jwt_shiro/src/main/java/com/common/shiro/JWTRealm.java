package com.common.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.util.JWTUtil;
import com.module.demo.mapper.PermissionMapper;
import com.module.demo.mapper.RoleMapper;
import com.module.demo.mapper.UserMapper;
import com.module.demo.model.Permission;
import com.module.demo.model.Role;
import com.module.demo.model.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JWTRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /*
     * ①必须重写此方法，否则报错
     * ②此处将AuthenticationToken转换为JWTToken
     * */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /* 登录 */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials(); /* 此处token由JWTFilter获取 */
        String name = JWTUtil.getName(token);
        if (name == null) {
            throw new AuthenticationException("token无效");
        } else {
            User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUserName, name));
            if (user == null) {
                throw new AuthenticationException("用户不存在");
            } else if (!JWTUtil.verify(token, name, user.getUserPassword())) {
                throw new AuthenticationException("密码错误");
            }
        }
        return new SimpleAuthenticationInfo(token, token, "JWTRealm");
    }

    /* 授权 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = (String) principalCollection.getPrimaryPrincipal();
        User select = User.builder().build();
        select.setUserName(JWTUtil.getName(token));

        List<Role> roleList = roleMapper.selectRoleList(select);
        Set<String> stringRoles = new HashSet<>();
        for (Role role : roleList) {
            stringRoles.add(role.getRoleName());
        }

        List<Permission> permissionList = permissionMapper.selectPermissionList(select);
        Set<String> stringPermissions = new HashSet<>();
        for (Permission permission : permissionList) {
            stringPermissions.add(permission.getPermissionPerm());
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(stringRoles);
        info.setStringPermissions(stringPermissions);
        return info;
    }

}
