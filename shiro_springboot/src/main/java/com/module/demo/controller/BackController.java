package com.module.demo.controller;

import com.module.demo.mapper.PermissionMapper;
import com.module.demo.mapper.RoleMapper;
import com.module.demo.model.Permission;
import com.module.demo.model.Role;
import com.module.demo.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/back")
public class BackController {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /* 后台首页（登录后就允许访问） */
    @RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView view = new ModelAndView("/back/home");
        /* 根据用户拥有的权限决定其内容 */
        Subject subject = SecurityUtils.getSubject();
        User select = (User) subject.getPrincipal();
        List<Role> roleList = roleMapper.selectRoleList(select);
        List<Permission> permissionList = permissionMapper.selectPermissionList(select);
        view.addObject("roleList", roleList);
        view.addObject("permissionList", permissionList);
        return view;
    }

}
