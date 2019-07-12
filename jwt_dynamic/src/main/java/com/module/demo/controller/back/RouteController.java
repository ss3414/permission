package com.module.demo.controller.back;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.module.demo.mapper.RouteMapper;
import com.module.demo.model.Route;
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

@Api(description = "路由管理")
@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RouteMapper routeMapper;

    @ApiOperation("路由列表")
    @GetMapping("/list")
    public Map<String, Object> list() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("msg", "路由列表信息");
        result.put("data", routeMapper.selectList(new QueryWrapper<Route>()));
        return result;
    }

    @ApiOperation("创建路由")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "routeSort", value = "路由排序"),
            @ApiImplicitParam(name = "routeUrl", value = "路由路径", required = true),
            @ApiImplicitParam(name = "routeName", value = "路由名", required = true),
    })
    @PostMapping("/create")
    public Map<String, Object> create(Route route) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            Route exist = routeMapper.selectOne(new QueryWrapper<Route>().lambda().eq(Route::getRouteUrl, route.getRouteUrl()));
            if (exist != null) {
                result.put("msg", "路由已被创建");
            } else {
                routeMapper.insert(route);
                result.put("msg", "路由创建成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "路由创建失败");
        }
        return result;
    }

    @ApiOperation("获取路由")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "路由Id", required = true),
    })
    @GetMapping("/get")
    public Map<String, Object> get(Integer id) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            result.put("msg", "路由获取成功");
            result.put("data", routeMapper.selectById(id));
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "路由获取失败");
        }
        return result;
    }

    /* 同一个路由不能有多种权限 */
    @ApiOperation("更新路由")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "路由Id", required = true),
            @ApiImplicitParam(name = "routeSort", value = "路由排序"),
            @ApiImplicitParam(name = "routeUrl", value = "路由路径"),
            @ApiImplicitParam(name = "routeName", value = "路由名"),
    })
    @PostMapping("/update")
    public Map<String, Object> update(Route route) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            routeMapper.updateById(route);
            result.put("msg", "权限更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "权限更新失败");
        }
        return result;
    }

    @ApiOperation("删除路由")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "路由Id", required = true),
    })
    @GetMapping("/delete")
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            routeMapper.deleteById(id);
            result.put("msg", "路由删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "路由删除失败");
        }
        return result;
    }

}
