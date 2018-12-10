package com.dly.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dly.model.entity.User;
import com.dly.model.filter.UserFilter;
import com.dly.model.vo.UserVo;
import com.dly.service.UserService;
import dly.RESTful.ReqObject;
import dly.RESTful.ReqQuery;
import dly.RESTful.ResList;
import dly.RESTful.ResObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 用户管理控制器
 *
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    // 增加用户表
    @PostMapping(value = "/create")
    public ResObject<User> create(@RequestBody ReqObject<User> data, HttpServletRequest request,
                                  HttpServletResponse response) {
        try {
            User user = userService.create(data);
            return new ResObject<>(data, user);
        } catch (Exception e) {
            return new ResObject<>(data, e);
        }
    }

    // 修改用户表
    @RequestMapping("/update")
    public ResObject<UserFilter> update(@RequestBody ReqObject<UserFilter> data, HttpServletRequest request,
                                        HttpServletResponse response) {
        try {
            UserFilter filter = userService.update(data);
            return new ResObject<>(data, filter);
        } catch (Exception e) {
            return new ResObject<>(data, e);
        }
    }

    // 删除用户表
    @RequestMapping("/remove")
    public ResObject<Integer> remove(@RequestBody ReqObject<User> data, HttpServletRequest request,
                                     HttpServletResponse response) {
        try {
            Integer count = userService.remove(data);
            return new ResObject<>(data, count);
        } catch (Exception e) {
            return new ResObject<>(data, e);
        }
    }

    // 查询用户
    @RequestMapping("/retrieve")
    public ResObject<ResList<UserVo>> retrieve(@RequestBody ReqObject<ReqQuery<UserFilter>> data,
                                               HttpServletRequest request, HttpServletResponse response) {
        try {
            ResList<UserVo> UserVoList = userService.retrieve(data);
            return new ResObject<>(data, UserVoList);
        } catch (Exception e) {
            return new ResObject<>(data, e);
        }
    }

}
