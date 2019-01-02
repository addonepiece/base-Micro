package com.dly.RESTful;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReqObject<T> implements Serializable {

    private static final long serialVersionUID = 2969975725154314044L;

    // 客户端流水号
    private String flowNo = "";

    // 终端类型
    private String term = "";

    // 企业编号
    private String corp = "";

    // 请求数据
    private T object = null;

    public ReqObject() {
    }

    public ReqObject(T object) {
        this.object = object;
    }

    public T getObject() {
        if(object == null){
            // 异常待分装
            // TODO by dly
        }
        return object;
    }
}
