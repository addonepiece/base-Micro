package com.dly.auth.constant;


import dly.exception.code.ProjectErrorCode;
import dly.exception.code.ProjectErrorCodeOneArg;

public class ErrMsgConstant {
    public static final ProjectErrorCodeOneArg PROMPT = new ProjectErrorCodeOneArg("10000", "{0}");
    public static final ProjectErrorCode PARAM_ERROR = new ProjectErrorCode("10001", "参数错误！");

}
