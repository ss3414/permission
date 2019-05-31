package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/front")
public class FrontController {

    /* 前台首页 */
    @ResponseBody
    @RequestMapping("/home")
    public Map home() {
        Map result = new HashMap();
        result.put("permission", "/front/home");
        return result;
    }

}
