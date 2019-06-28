package com.module.demo.controller;

import com.common.shiro.JWTUtil;
import com.module.demo.mapper.UserMapper;
import com.module.demo.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    /* 登录页面 */
    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView view = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.isAuthenticated()) {
            view.setViewName("redirect:/back/home"); /* 已登录状态下重复访问直接跳转后台 */
        } else {
            view.setViewName("/login/login");
        }
        return view;
    }

    /* 登录行为 */
    @ResponseBody
    @RequestMapping("/doLogin")
    public Map doLogin(String name, String password) {
        User user = new User();
        user.setUserName(name);
        user = userMapper.selectUser(user);

        /* 用户密码MD5加密 */
        String md5 = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            md5 = new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Map result = new HashMap();
        if (user == null) {
            result.put("redirect", "/login/loginFailure");
        } else if (!user.getUserPassword().equals(md5)) {
            result.put("redirect", "/login/loginFailure");
        } else {
            result.put("token", JWTUtil.sign(name, md5));
            result.put("redirect", "/back/home");
        }
        return result;
    }

    /* 登录失败 */
    @ResponseBody
    @RequestMapping("/loginFailure")
    public Map loginFailure() {
        Map result = new HashMap();
        result.put("msg", "用户不存在/密码错误");
        return result;
    }

    /* 没有角色/权限 */
    @ResponseBody
    @RequestMapping("/noRolePermission")
    public Map noRolePermission() {
        Map result = new HashMap();
        result.put("msg", "没有角色/权限");
        return result;
    }

}
