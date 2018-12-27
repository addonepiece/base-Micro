package com.dly.auth.model.filter;

import com.dly.auth.constant.ErrMsgConstant;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 *
 * @author yxr
 *
 */

@Data
@Slf4j
public class RegisterData {

    protected String phone;

    protected String email;

    protected String verCode;

    protected String account;

    protected String nickname;

    protected String password;

    protected String NewPassword;

    // 1注册2修改密码3登录
    protected String verCodeType;

    protected boolean isSingleTime;

    protected String term;

    public void validFilter() {
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(verCode) || StringUtils.isEmpty(account)
                || StringUtils.isEmpty(password) || StringUtils.isEmpty(verCodeType)) {
            throw ErrMsgConstant.PARAM_ERROR.exception();
        }
    }

}