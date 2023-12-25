package com;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@EnableCasClient
@RestController
@SpringBootApplication
public class CAS extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CAS.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(CAS.class, args);
    }

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    @RequestMapping("/login1")
    public ModelAndView login1(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();

        System.out.println(request.getRemoteUser());
        System.out.println(request.getUserPrincipal());

        view.addObject("user", request.getRemoteUser());
        view.setViewName("/login");
        return view;
    }

    @RequestMapping("/logout1")
    public ModelAndView logout1(HttpSession session) {
        session.invalidate();
        return new ModelAndView("/logout");
    }

    @RequestMapping("/login2")
    public ModelAndView login2(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        System.out.println(request.getRemoteUser());
        System.out.println(request.getUserPrincipal());

        view.addObject("user", request.getRemoteUser());
        view.setViewName("/login");
        return view;
    }

}
