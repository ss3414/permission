package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@SpringBootApplication
public class Shiro1 {

    public static void main(String[] args) {
        SpringApplication.run(Shiro1.class, args);
    }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

}
