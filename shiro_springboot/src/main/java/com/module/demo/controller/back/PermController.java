package com.module.demo.controller.back;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.module.demo.mapper.PermissionMapper;
import com.module.demo.model.Permission;
import com.module.demo.service.ShiroFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/perm")
public class PermController {

    @Autowired
    private PermissionMapper permMapper;

    @Autowired
    private ShiroFilterService shiroFilterService;

    @GetMapping("/list")
    public Map<String, Object> list() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("msg", "权限列表信息");
        result.put("data", permMapper.selectList(new QueryWrapper<Permission>()));
        return result;
    }

    /* fixme 每次新增权限自动附加到管理员角色上并即时生效 */
    @PostMapping("/create")
    public Map<String, Object> create(Permission perm) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            permMapper.insert(perm);
            result.put("msg", "权限创建成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "权限创建失败");
        }
        return result;
    }

    @GetMapping("/get")
    public Map<String, Object> get(Integer id) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            result.put("msg", "权限获取成功");
            result.put("data", permMapper.selectById(id));
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "权限获取失败");
        }
        return result;
    }

    /* 不准修改perm_name */
    @PostMapping("/update")
    public Map<String, Object> update(Permission perm) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            permMapper.updateById(perm);
            result.put("msg", "权限更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "权限更新失败");
        }
        return result;
    }

    /* fixme 正在使用的权限无法删除+多级权限问题+同时删除role_permission中对应数据 */
    @GetMapping("/delete")
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            permMapper.deleteById(id);
            result.put("msg", "权限删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "权限删除失败");
        }
        return result;
    }

}
