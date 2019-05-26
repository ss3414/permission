package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class DemoController {

    @RequestMapping("/login1")
    public ModelAndView login1(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("/login");
        System.out.println(request.getRemoteUser());
        System.out.println(request.getUserPrincipal());

        view.addObject("user", request.getRemoteUser());
        return view;
    }

    @RequestMapping("/logout1")
    public ModelAndView logout1(HttpSession session) {
        ModelAndView view = new ModelAndView("/logout");

        session.invalidate();
        return view;
    }

    @RequestMapping("/login2")
    public ModelAndView login2(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("/login");
        System.out.println(request.getRemoteUser());
        System.out.println(request.getUserPrincipal());

        view.addObject("user", request.getRemoteUser());
        return view;
    }

}
