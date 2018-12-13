package com.dly.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dly.auth.model.filter.UserFilter;
import com.dly.auth.model.vo.UserVo;
import com.dly.auth.model.entity.User;
import com.dly.auth.service.UserService;
import dly.RESTful.ReqObject;
import dly.RESTful.ReqQuery;
import dly.RESTful.ResList;
import dly.RESTful.ResObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理控制器
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    // 新增用户
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

    // 查询用户
    @PostMapping("/retrieve")
    public ResObject<ResList<UserVo>> retrieve(@RequestBody ReqObject<ReqQuery<UserFilter>> data,
                                               HttpServletRequest request, HttpServletResponse response) {
        try {
            ResList<UserVo> UserVoList = userService.retrieve(data);
            return new ResObject<>(data, UserVoList);
        } catch (Exception e) {
            return new ResObject<>(data, e);
        }
    }

    // 修改用户表
    @PutMapping("/update")
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
    @DeleteMapping("/remove")
    public ResObject<Integer> remove(@RequestBody ReqObject<User> data, HttpServletRequest request,
                                     HttpServletResponse response) {
        try {
            Integer count = userService.remove(data);
            return new ResObject<>(data, count);
        } catch (Exception e) {
            return new ResObject<>(data, e);
        }
    }

}
