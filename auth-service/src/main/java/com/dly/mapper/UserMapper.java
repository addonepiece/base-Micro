package com.dly.mapper;

import com.dly.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int createUser(User user);
}
