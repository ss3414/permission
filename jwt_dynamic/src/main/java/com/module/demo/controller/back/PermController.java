package com.module.demo.controller.back;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.module.demo.mapper.PermissionMapper;
import com.module.demo.model.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@Api(description = "权限管理")
@RestController
@RequestMapping("/perm")
public class PermController {

    @Autowired
    private PermissionMapper permissionMapper;

    @ApiOperation("权限列表")
    @GetMapping("/list")
    public Map<String, Object> list() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("msg", "权限列表信息");
        result.put("data", permissionMapper.selectList(new QueryWrapper<Permission>()));
        return result;
    }

    /* fixme 每次新增权限自动附加到管理员角色上并即时生效 */
    @ApiOperation("创建权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父级权限"),
            @ApiImplicitParam(name = "permissionName", value = "权限名", required = true),
            @ApiImplicitParam(name = "permissionUrl", value = "权限路由"),
            @ApiImplicitParam(name = "permissionPerm", value = "权限值", required = true),
    })
    @PostMapping("/create")
    public Map<String, Object> create(Permission perm) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            permissionMapper.insert(perm);
            result.put("msg", "权限创建成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "权限创建失败");
        }
        return result;
    }

    @ApiOperation("获取权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "权限Id", required = true),
    })
    @GetMapping("/get")
    public Map<String, Object> get(Integer id) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            result.put("msg", "权限获取成功");
            result.put("data", permissionMapper.selectById(id));
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "权限获取失败");
        }
        return result;
    }

    @ApiOperation("更新权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "权限Id", required = true),
            @ApiImplicitParam(name = "parentId", value = "父级权限"),
            @ApiImplicitParam(name = "permissionName", value = "权限名"),
            @ApiImplicitParam(name = "permissionUrl", value = "权限路由"),
            @ApiImplicitParam(name = "permissionPerm", value = "权限值"),
    })
    @PostMapping("/update")
    public Map<String, Object> update(Permission perm) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            permissionMapper.updateById(perm);
            result.put("msg", "权限更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "权限更新失败");
        }
        return result;
    }

    /* fixme 正在使用的权限无法删除+多级权限问题+同时删除role_permission中对应数据 */
    @ApiOperation("删除权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "权限Id", required = true),
    })
    @GetMapping("/delete")
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            permissionMapper.deleteById(id);
            result.put("msg", "权限删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "权限删除失败");
        }
        return result;
    }

}
