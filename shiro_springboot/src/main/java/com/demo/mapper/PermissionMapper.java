package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.model.ShiroPermission;
import com.demo.model.ShiroUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper extends BaseMapper<ShiroPermission> {

    List<ShiroPermission> selectPermissionList(ShiroUser user);

}
