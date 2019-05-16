package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.model.ShiroRole;
import com.demo.model.ShiroUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<ShiroRole> {

    List<ShiroRole> selectRoleList(ShiroUser user);

}
