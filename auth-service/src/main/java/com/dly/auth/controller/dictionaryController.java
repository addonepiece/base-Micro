package com.dly.auth.controller;

import com.dly.RESTful.ReqObject;
import com.dly.RESTful.ResObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/dictionary")
public class dictionaryController {

    // 查询数据字典类型
    @RequestMapping("/getDictionaryType")
    public ResObject<String> getDictionaryType(@RequestBody ReqObject<String> reqObject, HttpServletRequest request, HttpServletResponse response) {

        return null;
    }

    // 查询数据字典子项
    @RequestMapping("/getDictionaryItm")
    public ResObject<String> getDictionaryItm(@RequestBody ReqObject<String> reqObject, HttpServletRequest request, HttpServletResponse response) {

        return null;
    }

}
