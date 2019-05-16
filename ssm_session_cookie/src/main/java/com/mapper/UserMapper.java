package com.mapper;

import com.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    
    User selectUser(User user);

}