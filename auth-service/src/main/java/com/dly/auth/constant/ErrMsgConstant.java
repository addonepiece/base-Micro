package com.dly.auth.constant;


import com.dly.exception.code.ProjectErrorCode;
import com.dly.exception.code.ProjectErrorCodeOneArg;

public class ErrMsgConstant {
    public static final ProjectErrorCodeOneArg PROMPT = new ProjectErrorCodeOneArg("10000", "{0}");
    public static final ProjectErrorCode PARAM_ERROR = new ProjectErrorCode("10001", "参数错误！");
    public static final ProjectErrorCode UPLOAD_FILE_ERROR = new ProjectErrorCode("30001", "上传文件为空,请重试！");
    public static final ProjectErrorCode IMPORT_TEMPLATE_ERROR = new ProjectErrorCode("30007", "导入模板错误，请检查表格头！");

}
