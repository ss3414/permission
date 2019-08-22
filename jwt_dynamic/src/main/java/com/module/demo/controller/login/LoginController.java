package com.module.demo.controller.login;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.util.JWTUtil;
import com.common.util.MD5Util;
import com.module.demo.mapper.UserMapper;
import com.module.demo.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@Api(description = "登录")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @ApiOperation("登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true),
    })
    @PostMapping("/doLogin")
    public Map doLogin(String name, String password) {
        User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUserName, name));

        Map result = new LinkedHashMap();
        if (user == null) {
            result.put("msg", "用户不存在");
        } else if (!user.getUserPassword().equals(MD5Util.md5(password))) {
            result.put("msg", "密码错误");
        } else {
            result.put("msg", "登录成功");
            result.put("token", JWTUtil.sign(name, MD5Util.md5(password)));
        }
        return result;
    }

}
