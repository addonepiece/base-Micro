package com.dly.auth.controller;

import com.dly.auth.model.entity.User;
import com.dly.auth.model.filter.RegisterData;
import com.dly.auth.model.filter.UserFilter;
import com.dly.auth.service.AccountService;
import com.dly.auth.service.UserService;
import dly.RESTful.ReqObject;
import dly.RESTful.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Description:手机号邮箱登录注册找回密码控制器
 *
 * @author yxr
 *
 */

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService;

    @PostMapping("/checkAccount")
    public ResObject<Boolean> checkAccount(@RequestBody ReqObject<String> reqObject, HttpServletRequest request,
                                           HttpServletResponse response) {
        try {
            String account = reqObject.getObject();
            boolean result = accountService.checkAccount(account);
            return new ResObject<Boolean>(reqObject, result);
        } catch (Exception e) {
            return new ResObject<Boolean>(reqObject, e);
        }
    }

    @PostMapping("/checkPhone")
    public ResObject<Boolean> checkPhone(@RequestBody ReqObject<String> reqObject, HttpServletRequest request,
                                         HttpServletResponse response) {
        try {
            String phone = reqObject.getObject();
            boolean result = accountService.checkPhone(phone);
            return new ResObject<Boolean>(reqObject, result);
        } catch (Exception e) {
            return new ResObject<Boolean>(reqObject, e);
        }
    }

    @PostMapping("/checkEmail")
    public ResObject<Boolean> checkEmail(@RequestBody ReqObject<String> reqObject, HttpServletRequest request,
                                         HttpServletResponse response) {
        try {
            String email = reqObject.getObject();
            boolean result = accountService.checkEmail(email);
            return new ResObject<Boolean>(reqObject, result);
        } catch (Exception e) {
            return new ResObject<Boolean>(reqObject, e);
        }
    }

    @RequestMapping("/getMsgCode")
    public ResObject<String> getMsgCode(@RequestBody ReqObject<RegisterData> data, HttpServletRequest request,
                                        HttpServletResponse response) {
        try {
            String result = accountService.getMsgCode(data.getObject(), (String s1, String s2) -> {
                return Pattern.matches(s1, s2);
            });
            return new ResObject<String>(data, result);
        } catch (Exception e) {
            return new ResObject<String>(data, e);
        }
    }

    @RequestMapping("/validateMsgCode")
    public ResObject<Boolean> verRegisterCode(@RequestBody ReqObject<RegisterData> reqObject,
                                              HttpServletRequest request, HttpServletResponse response) {
        try {
            RegisterData data = reqObject.getObject();
            boolean result = accountService.validateMsgCode(data.getPhone(), data.getVerCode(), data.getVerCodeType(),
                    data.isSingleTime());
            return new ResObject<Boolean>(reqObject, result);
        } catch (Exception e) {
            return new ResObject<Boolean>(reqObject, e);
        }
    }

    @RequestMapping("/registerUser")
    public ResObject<User> registerUser(@RequestBody ReqObject<RegisterData> reqObject, HttpServletRequest request,
                                        HttpServletResponse response) {
        try {
            RegisterData data = reqObject.getObject();
            User user = accountService.registerUser(data);
            return new ResObject<User>(reqObject, user);
        } catch (Exception e) {
            return new ResObject<User>(reqObject, e);
        }
    }

    @RequestMapping("/getCodeByEmail")
    public ResObject<String> getCodeByEmail(@RequestBody ReqObject<RegisterData> data, HttpServletRequest request,
                                            HttpServletResponse response) {
        try {
            String result = accountService.getCodeByEmail(data.getObject(), (String s1, String s2) -> {
                return Pattern.matches(s1, s2);
            });
            return new ResObject<String>(data, result);
        } catch (Exception e) {
            return new ResObject<String>(data, e);
        }
    }

    @RequestMapping("/registerEmailUser")
    public ResObject<User> registerEmailUser(@RequestBody ReqObject<RegisterData> reqObject, HttpServletRequest request,
                                             HttpServletResponse response) {
        try {
            RegisterData data = reqObject.getObject();
            User user = accountService.registerEmailUser(data);
            return new ResObject<User>(reqObject, user);
        } catch (Exception e) {
            return new ResObject<User>(reqObject, e);
        }
    }

    @GetMapping("/activeEmailUser")
    // http://localhost:1505/account/activeEmailUser?email=yan.xinrong@icerno.com&code=2018-11-08 11:48:16
    public void registerEmailUser(@RequestParam String email, @RequestParam String code, HttpServletRequest request,
                                  HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("code", code);
        // 激活账号
        accountService.activeEmailUser(map);
        String url = "http://manager-cs.xilaikd.com/xilai.html?href=xilai/dataCenter/OperateLogPage/?fromDashboard=true";
        try {
            response.reset();
            response.sendRedirect(url);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/resetPassword")
    public ResObject<UserFilter> resetPassword(@RequestBody ReqObject<RegisterData> reqObject,
                                               HttpServletRequest request, HttpServletResponse response) {
        try {
            // 重置密码
            UserFilter filter = accountService.resetPasswordByPhone(reqObject.getObject());
            filter.setPassword(null);
            return new ResObject<UserFilter>(reqObject, filter);
        } catch (Exception e) {
            return new ResObject<UserFilter>(reqObject, e);
        }
    }

    @RequestMapping("/resetPasswordByEmail")
    public ResObject<UserFilter> resetPasswordByEmail(@RequestBody ReqObject<RegisterData> reqObject,
                                                      HttpServletRequest request, HttpServletResponse response) {
        try {
            // 重置密码
            UserFilter filter = accountService.resetPasswordByEmail(reqObject.getObject());
            filter.setPassword(null);
            return new ResObject<UserFilter>(reqObject, filter);
        } catch (Exception e) {
            return new ResObject<UserFilter>(reqObject, e);
        }
    }

    @RequestMapping("/changePassword")
    public ResObject<UserFilter> changePassword(@RequestBody ReqObject<RegisterData> reqObject,
                                                HttpServletRequest request, HttpServletResponse response) {
        try {
            // 修改密码
            UserFilter filter = accountService.changePassword(reqObject.getObject());
            filter.setPassword(null);
            filter.setNewPassword(null);
            return new ResObject<UserFilter>(reqObject, filter);
        } catch (Exception e) {
            return new ResObject<UserFilter>(reqObject, e);
        }
    }

}