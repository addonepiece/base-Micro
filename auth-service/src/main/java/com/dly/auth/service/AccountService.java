package com.dly.auth.service;

import com.dly.auth.model.entity.User;
import com.dly.auth.model.filter.RegisterData;

import java.util.Map;
import java.util.function.BiPredicate;

import com.aliyuncs.exceptions.ClientException;
import com.dly.auth.model.filter.UserFilter;

public interface AccountService {

    String getMsgCode(RegisterData data, BiPredicate<String, String> biPredicate) throws ClientException;

    // 发送验证码到邮箱
    String getCodeByEmail(RegisterData data, BiPredicate<String, String> biPredicate) throws ClientException;

    boolean checkAccount(String account);

    boolean checkPhone(String phone);

    boolean checkEmail(String email);

    boolean validateMsgCode(String phone, String verCode, String VerCodeType, boolean isSingleTime);

    User registerUser(RegisterData data);

    User registerEmailUser(RegisterData data);

    UserFilter resetPasswordByPhone(RegisterData data);

    UserFilter resetPasswordByEmail(RegisterData data);

    UserFilter changePassword(RegisterData data);

    int activeEmailUser(Map<String, String> map);

}
