package com.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

    /* 欢迎页 */
    @RequestMapping("")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/index");
        return view;
    }

}
