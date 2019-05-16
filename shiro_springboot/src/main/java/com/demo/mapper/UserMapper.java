package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.model.ShiroUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<ShiroUser> {

    /* 查询是否存在此用户 */
    ShiroUser selectUser(ShiroUser user);

}
