package com.controller;

import com.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/back")
public class BackController {

    /*
     * 后台首页
     * ①登录成功跳转后台
     * */
    @RequestMapping("/home")
    public ModelAndView home(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user"); /* 登录成功时写入Session中的User */
        User user2 = (User) request.getSession().getAttribute("sessionUser"); /* Interceptor写入的User */
        return new ModelAndView("/back/home");
    }

    /* 权限1 */
    @ResponseBody
    @RequestMapping("/permission1")
    public Map permission1() {
        Map result = new HashMap();
        result.put("success", "permission1");
        return result;
    }

    /* 权限2 */
    @ResponseBody
    @RequestMapping("/permission2")
    public Map permission2() {
        Map result = new HashMap();
        result.put("success", "permission1");
        return result;
    }

}
