package com.dly.auth.service;

import com.dly.RESTful.ReqObject;
import com.dly.RESTful.ReqQuery;
import com.dly.RESTful.ResList;
import com.dly.auth.constant.ErrMsgConstant;
import com.dly.auth.mapper.DictionaryMapper;
import com.dly.auth.model.entity.Dictionary;
import com.dly.auth.model.vo.DictionaryXlsx;
import com.dly.auth.util.ExcelCustomUtil;
import com.dly.auth.util.ExcelDataReaderUtil;
import com.dly.id.IDGenerator;
import com.dly.util.DateUtil;
import com.dly.util.TempFileConf;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;


@Service
@Slf4j
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Autowired
    ExcelCustomUtil excelCustomUtil;

    @Autowired
    private TempFileConf tempConf;

    //导入excel数据
    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    public String importDictionaryFromEcel(HttpServletRequest request) throws IOException, OpenXML4JException, SAXException {
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        // 将上传的文件重命名
        String fileSaveName = "数据字典" + DateUtil.longDateTime(calendar).replaceAll(":", "").replaceAll("-", "").replaceAll(" ", "");
        // 上传后的文件路径
        String namePath = excelCustomUtil.createFileName((MultipartHttpServletRequest) request, tempConf.getTempPath(), fileSaveName);
        List<DictionaryXlsx> dictionaryXlsxList = new ArrayList<>();
        //读取excel
        dictionaryXlsxList = readDictionaryExcel(request, namePath);
        // 检查数据合法性
        int count = dictionaryXlsxList.size();
        if (count < 1) {
            throw ErrMsgConstant.UPLOAD_FILE_ERROR.exception();
        }

        // 删除全部数据字典
//        dictionaryMapper.removeAllDictionary();
        // 批量插入数据字典
//        dictionaryMapper.batchInsertDictionary(dictionaryList);

        return namePath;
    }


    // 查询全部数据字典类型
    @Override
    public ResList<Dictionary> getAllDictionaryTypes(ReqObject<ReqQuery<Dictionary>> reqObject) {
        ReqQuery<Dictionary> query = reqObject.getObject();
        Dictionary dictionary = query.getObject();

        ResList<Dictionary> resList;

        int startPage = query.getStartPage();
        int pageRow = query.getPageRow();
        if (startPage == 0 && pageRow == 0) {
//            List<Dictionary> list = dictionaryMapper.getAllDictionaryTypes(dictionary);
//            resList = new ResList<>(list);
//            if (list.size() > ReqQuery.totalCount) {
//                throw BasicErrCodes.DATASET_TOO_LARGE.exception(String.valueOf(list.size()),
//                        String.valueOf(ReqQuery.totalCount));
//            }
        } else {
//            PageHelper.startPage(startPage, pageRow);
//            List<Dictionary> list = dictionaryMapper.getAllDictionaryTypes(dictionary);
//            PageInfo<Dictionary> page = new PageInfo<>(list);
//
//            resList = new ResList<>(list);
//            resList.setStartPage(startPage);
//            resList.setPageRow(pageRow);
//            resList.setTotalRow(page.getTotal());
        }

//        return resList;
        return null;
    }

    // 解析导出数据参数
    private <REQ> ReqObject<REQ> getExportInfo(HttpServletRequest request, Class<REQ> objClass) {
        ReqObject<REQ> reqObject = new ReqObject<>();
        String fieldValue = request.getParameter("reqObject");
        if (fieldValue != null && !fieldValue.isEmpty()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                JavaType jType = objectMapper.getTypeFactory().constructParametricType(ReqObject.class, objClass);
                reqObject = objectMapper.readValue(fieldValue, jType);
            } catch (Exception e) {
                log.error("JSON[" + fieldValue + "]转对象错误", e);
            }
        }
        return reqObject;
    }

    //读取数据字典Excel
    private List<DictionaryXlsx> readDictionaryExcel(HttpServletRequest request, String namePath) throws IOException, OpenXML4JException, SAXException {
        List<DictionaryXlsx> recordSet = new ArrayList<DictionaryXlsx>(10000);

        // 数据库字段名map
        Map<String, Integer> fieldMap = new HashMap<>();
        fieldMap.put("uuid", 0);
        fieldMap.put("dicId", 1);
        fieldMap.put("dicName", 2);
        fieldMap.put("dicItm", 3);
        fieldMap.put("dicItmName", 4);
        fieldMap.put("dicSort", 5);
        fieldMap.put("active", 6);

        Map<Integer, String> sheetNameMap = new HashMap<>();

        // excel列头
        Map<String, Integer> nameMap = new HashMap<>();
        nameMap.put("uuid", 0);
        nameMap.put("字典类型id", 1);
        nameMap.put("字典类型名称", 2);
        nameMap.put("字典类型项", 3);
        nameMap.put("字典类型项称", 4);
        nameMap.put("排序", 5);
        nameMap.put("状态", 6);

        new ExcelDataReaderUtil(namePath) {
            protected void outputRow(List<String> datas, int rowIndex, int sheetIndex, String sheetName) {
                sheetNameMap.put(sheetIndex, sheetName.trim());
                //sheetName全部设定为模板数据
                if (rowIndex == 0 && sheetName.trim().equals("dictionary")) {
                    if (nameMap.size() != datas.size()) {
                        datas.clear();
                        throw ErrMsgConstant.IMPORT_TEMPLATE_ERROR.exception();
                    } else {
                        for (String data : datas) {
                            if (!nameMap.containsKey(data)) {
                                datas.clear();
                                throw ErrMsgConstant.IMPORT_TEMPLATE_ERROR.exception();
                            }
                        }
                    }
                }

                // 此处输出每一行的数据
                if (rowIndex != 0 && sheetName.trim().equals("dictionary")) {
                    if (datas.size() < 1) return;
                    int count = 0;
                    for (String data : datas) {

                        if (data == null) {
                            count++;
                        }
                        if (count == datas.size()) {
                            return;
                        }
                    }

                    DictionaryXlsx dictionaryXlsx = ExcelCustomUtil.toBean(DictionaryXlsx.class, fieldMap, datas);
                    dictionaryXlsx.setUuid(IDGenerator.UUID.generate());
                    recordSet.add(dictionaryXlsx);
                }
            }
        };

        return recordSet;
    }
}
