package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/perm")
public class PermController {

    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView view = new ModelAndView("");
        return view;
    }

    @RequestMapping("/create")
    public ModelAndView create() {
        ModelAndView view = new ModelAndView("");
        return view;
    }

    @RequestMapping("/update")
    public ModelAndView update() {
        ModelAndView view = new ModelAndView("");
        return view;
    }

    @RequestMapping("/delete")
    public ModelAndView delete() {
        ModelAndView view = new ModelAndView("");
        return view;
    }

}
