package com.dly.controller;

import com.dly.model.entity.User;
import com.dly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserCtrl {
    @Autowired
    UserService userService;


    @RequestMapping("/create")
    public String CreateUser() {
        User user = new User();
        return userService.createUser(user) + "";
    }
}
