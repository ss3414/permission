package com.module.demo.controller.back;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.util.MD5Util;
import com.module.demo.mapper.UserMapper;
import com.module.demo.mapper.UserRoleMapper;
import com.module.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @GetMapping("/list")
    public Map<String, Object> list() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("msg", "用户列表信息");
        result.put("data", userMapper.selectList(new QueryWrapper<User>()));
        return result;
    }

    /* user_name唯一，密码MD5加密 */
    @PostMapping("/create")
    public Map<String, Object> create(User user) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            User exist = userMapper.selectUser(user);
            if (exist != null) {
                result.put("msg", "用户名已创建");
            } else {
                user.setUserPassword(MD5Util.md5(user.getUserPassword()));
                userMapper.insert(user);
                result.put("msg", "用户创建成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "用户创建失败");
        }
        return result;
    }

    /* fixme 查看用户+用户拥有的角色 */
    @GetMapping("/get")
    public Map<String, Object> get(Integer id) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            result.put("msg", "用户获取成功");
            result.put("data", userMapper.selectById(id));
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "用户获取失败");
        }
        return result;
    }

    /* 不准修改user_name */
    @PostMapping("/update")
    public Map<String, Object> update(User user) {
        if (user.getUserPassword() != null) {
            user.setUserPassword(MD5Util.md5(user.getUserPassword()));
        }
        user.setUserName(null);

        Map<String, Object> result = new LinkedHashMap<>();
        try {
            userMapper.updateById(user);
            result.put("msg", "用户更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "用户更新失败");
        }
        return result;
    }

    @GetMapping("/delete")
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            userMapper.deleteById(id);
            result.put("msg", "用户删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "用户删除失败");
        }
        return result;
    }

    /* fixme 用户-角色关联（获取所有关联信息，删除旧的创建新的） */

}
