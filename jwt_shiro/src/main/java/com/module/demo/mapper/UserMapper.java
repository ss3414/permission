package com.module.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.module.demo.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

    /* 查询是否存在此用户 */
    User selectUser(User user);

}
