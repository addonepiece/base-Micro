package com.dly.service;

import com.dly.mapper.UserMapper;
import com.dly.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int createUser(User user) {
        return userMapper.createUser(user);
    }
}
