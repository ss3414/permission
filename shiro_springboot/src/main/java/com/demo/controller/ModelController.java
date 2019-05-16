package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/model")
public class ModelController {

    @ResponseBody
    @RequestMapping("/list")
    public Map list() {
        Map result = new HashMap();
        result.put("msg", "查看Model");
        return null;
    }

    @ResponseBody
    @RequestMapping("/create")
    public Map create() {
        Map result = new HashMap();
        result.put("msg", "新增Model");
        return null;
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map update() {
        Map result = new HashMap();
        result.put("msg", "修改Model");
        return null;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map delete() {
        Map result = new HashMap();
        result.put("msg", "删除Model");
        return null;
    }

}
