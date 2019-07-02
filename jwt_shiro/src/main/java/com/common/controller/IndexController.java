package com.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("")
public class IndexController {

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/index");
        return view;
    }

    @ResponseBody
    @RequestMapping("/json")
    public Map json() {
        Map map = new HashMap();
        map.put("status", 1000);
        return map;
    }

}
