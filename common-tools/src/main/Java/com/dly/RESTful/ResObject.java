package com.dly.RESTful;

import com.dly.id.IDGenerator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
public class ResObject<T> implements Serializable {
    private static final long serialVersionUID = -27268557189775472L;

    // 错误代码
    private String errCode;

    // 错误信息描述
    private String errDesc;

    // 服务端流水号
    private String svcFlowNo;

    // 客户端流水号
    private String cliFlowNo;

    // 返回信息
    private T object;

    private Exception exception;

    public ResObject(@SuppressWarnings("rawtypes") ReqObject req, T obj) {
        errCode = "000000";
        errDesc = "";
        svcFlowNo = IDGenerator.UUID.generate();
        this.cliFlowNo = (req == null) ? "1" : req.getFlowNo();

        object = obj;
    }

    public ResObject(@SuppressWarnings("rawtypes") ReqObject req, String errCode, String errDesc) {
        this.errCode = errCode;
        this.errDesc = errDesc;
        svcFlowNo = IDGenerator.UUID.generate();
        this.cliFlowNo = (req == null) ? "1" : req.getFlowNo();

        if (errCode == null || errCode.isEmpty()) {
            errCode = "999999";
            errDesc = "未知的错误";
        }

        object = null;
    }

    public ResObject(@SuppressWarnings("rawtypes") ReqObject req, Exception e) {
        exception = e;
//        if (e instanceof LzException) {
//            LzException e2 = (LzException) e;
//            this.errCode = e2.getErrCode();
//            this.errDesc = e.getMessage();
        if (false) {
            // TODO by dly 异常封装

            if (errCode == null || errCode.isEmpty()) {
                errCode = "999999";
                errDesc = "未知的错误";
            }
        } else {
            String name = e.getClass().getSimpleName();
            if ("DuplicateKeyException".equals(name)) {
                this.errCode = "999990";
                this.errDesc = "操作数据库时记录重复";
            } else {
                this.errCode = "999999";
                this.errDesc = e.getMessage();
            }
        }

        svcFlowNo = IDGenerator.UUID.generate();
        this.cliFlowNo = (req == null) ? "1" : req.getFlowNo();
        object = null;

        log.info("处理错误", e);
    }

    public ResObject() {
        errCode = "000000";
        errDesc = "";
        svcFlowNo = IDGenerator.UUID.generate();
    }


}
