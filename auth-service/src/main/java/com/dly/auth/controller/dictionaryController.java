package com.dly.auth.controller;

import com.dly.RESTful.ReqObject;
import com.dly.RESTful.ReqQuery;
import com.dly.RESTful.ResList;
import com.dly.RESTful.ResObject;
import com.dly.auth.model.entity.Dictionary;
import com.dly.auth.service.DictionaryService;
import com.dly.exception.ProjectErrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/dictionary")
public class dictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    // 数据字典excel上传并导入数据到数据库
    @RequestMapping("/importDictionaryFromEcel")
    public ResObject<String> importDictionaryFromEcel(HttpServletRequest request, HttpServletResponse response) {
        // 判断 request是否有文件上传,即多部分请求
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        StandardMultipartHttpServletRequest standardMultipartHttpServletRequest = (StandardMultipartHttpServletRequest)request;

        if (!(standardMultipartHttpServletRequest.getMultiFileMap().size() > 0)) {
            return new ResObject<>(null, "FILE91", "请上传文件");
        }

        try {
            String fileName = dictionaryService.importDictionaryFromEcel(request);
            return new ResObject(null, fileName);

        } catch (Exception e) {
            if (e instanceof SAXException) {
                SAXException sAXException = (SAXException) e;
                ProjectErrException projectErrException = (ProjectErrException) sAXException.getException();
                String errCode = projectErrException.getErrCode();
                return new ResObject<>(null, errCode, e.getMessage());
            } else {
                return new ResObject<>(null, e);
            }
        }
    }

    // 查询全部数据字典类型
    @PostMapping("/getAllDictionaryTypes")
    public ResObject<ResList<Dictionary>> getAllDictionaryTypes(@RequestBody ReqObject<ReqQuery<Dictionary>> reqObject, HttpServletRequest request, HttpServletResponse response) {
        try {
            ResList<Dictionary> resList = dictionaryService.getAllDictionaryTypes(reqObject);
            return new ResObject<>(reqObject, resList);
        } catch (Exception e) {
            return new ResObject<>(reqObject, e);
        }

    }


    // 根据dic_id查询数据字典
    @PostMapping("/getDictionaryTypeByDicId")
    public ResObject<ResList<Dictionary>> getAllDictionaryType(@RequestBody ReqObject<ReqQuery<Dictionary>> reqObject, HttpServletRequest request, HttpServletResponse response) {

        return null;
    }

    // 查询数据字典子项
    @PostMapping("/getDictionaryItm")
    public ResObject<String> getDictionaryItm(@RequestBody ReqObject<Dictionary> reqObject, HttpServletRequest request, HttpServletResponse response) {

        return null;
    }

}
