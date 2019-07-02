package com.module.demo.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @RequiresPermissions("/model/list")
    public Map list() {
        Map result = new HashMap();
        result.put("msg", "/model/list");
        return result;
    }

    @ResponseBody
    @RequestMapping("/create")
    @RequiresPermissions("/model/create")
    public Map create() {
        Map result = new HashMap();
        result.put("msg", "/model/create");
        return result;
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("/model/update")
    public Map update() {
        Map result = new HashMap();
        result.put("msg", "/model/update");
        return result;
    }

    @ResponseBody
    @RequestMapping("/delete")
    @RequiresPermissions("/model/delete")
    public Map delete() {
        Map result = new HashMap();
        result.put("msg", "/model/delete");
        return result;
    }

}
