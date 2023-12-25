package com;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.Map;

@EnableSwagger2Doc
@RestController
@SpringBootApplication
@MapperScan("com.**.mapper")
public class JWT {

    public static void main(String[] args) {
        SpringApplication.run(JWT.class, args);
    }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    @GetMapping("/json")
    public Map json() {
        Map map = new LinkedHashMap();
        map.put("msg", "中文");
        return map;
    }

}
