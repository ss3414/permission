package com;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SaToken {

    public static void main(String[] args) {
        SpringApplication.run(SaToken.class, args);
        System.out.println("Sa-Token配置：" + SaManager.getConfig());
    }

    @RequestMapping("/login")
    public SaResult login(String name, String pwd) {
        if ("user".equals(name) && "123456".equals(pwd)) {
            StpUtil.login(10001);
            return SaResult.ok("登录成功");
        }
        return SaResult.error("登录失败");
    }

    @RequestMapping("/isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    @RequestMapping("/tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    @RequestMapping("/session")
    public SaResult session() {
        StpUtil.getSession().set("name", "user");
        return SaResult.data(StpUtil.getSession().get("name"));
    }

    /* 需要权限才能访问 */
    @SaCheckLogin
    @RequestMapping("/test")
    public SaResult test() {
        return SaResult.data(StpUtil.getSession().get("name"));
    }

    @RequestMapping("/logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

}
