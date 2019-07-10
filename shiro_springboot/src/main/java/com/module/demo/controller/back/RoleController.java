package com.module.demo.controller.back;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.module.demo.mapper.RoleMapper;
import com.module.demo.mapper.RolePermissionMapper;
import com.module.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolepermMapper;

    @GetMapping("/list")
    public Map<String, Object> list() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("msg", "角色列表信息");
        result.put("data", roleMapper.selectList(new QueryWrapper<Role>()));
        return result;
    }

    @PostMapping("/create")
    public Map<String, Object> create(Role role) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            roleMapper.insert(role);
            result.put("msg", "角色创建成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "角色创建失败");
        }
        return result;
    }

    /* fixme 查看角色+角色拥有的权限 */
    @GetMapping("/get")
    public Map<String, Object> get(Integer id) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            result.put("msg", "角色获取成功");
            result.put("data", roleMapper.selectById(id));
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "角色获取失败");
        }
        return result;
    }

    @PostMapping("/update")
    public Map<String, Object> update(Role role) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            roleMapper.updateById(role);
            result.put("msg", "角色更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "角色更新失败");
        }
        return result;
    }

    /* fixme 正在使用的角色无法删除+同时删除user_role中对应数据 */
    @GetMapping("/delete")
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            roleMapper.deleteById(id);
            result.put("msg", "角色删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "角色删除失败");
        }
        return result;
    }

    /* fixme 角色-权限关联（获取所有关联信息，删除旧的创建新的） */

}
