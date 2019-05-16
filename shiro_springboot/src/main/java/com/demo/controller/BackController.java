package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/back")
public class BackController {

    /* 后台首页（登录后就允许访问） */
    @RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView view = new ModelAndView("/back/home");
        return view;
    }

}
