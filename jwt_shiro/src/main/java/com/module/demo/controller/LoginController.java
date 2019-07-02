package com.module.demo.controller;

import com.common.shiro.JWTUtil;
import com.module.demo.mapper.UserMapper;
import com.module.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
            result.put("msg", "用户不存在");
        } else if (!user.getUserPassword().equals(md5)) {
            result.put("msg", "密码错误");
        } else {
            result.put("msg", "登录成功");
            result.put("token", JWTUtil.sign(name, md5));
        }
        return result;
    }

}
