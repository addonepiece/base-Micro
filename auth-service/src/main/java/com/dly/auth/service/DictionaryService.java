package com.dly.auth.service;

import com.dly.RESTful.ReqObject;
import com.dly.RESTful.ReqQuery;
import com.dly.RESTful.ResList;
import com.dly.auth.model.entity.Dictionary;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface DictionaryService {

    // 数据字典excel上传并导入数据到数据库
    String importDictionaryFromEcel( HttpServletRequest request ) throws IOException, OpenXML4JException, SAXException;

    // 查询全部数据字典类型
    ResList<Dictionary> getAllDictionaryTypes(ReqObject<ReqQuery<Dictionary>> reqObject);

}
