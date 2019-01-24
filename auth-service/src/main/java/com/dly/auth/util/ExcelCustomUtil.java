package com.dly.auth.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dly.exception.code.BasicErrCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
@Slf4j
public class ExcelCustomUtil {

    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";
    private static Object lock = new Object();
    private final static String EXCEL2007U = ".xlsx";
    private static int fileIndex = 1;

    public String createFileName(MultipartHttpServletRequest request, String tempPath, String fileSaveName) {
        String fileName = "";
        // 取得request中的所有文件名
        Iterator<String> iterator = request.getFileNames();
        while (iterator.hasNext()) {
            String fieldName = iterator.next();
            MultipartFile multipartFile = request.getFile(fieldName);
            if (multipartFile != null) {
                // 获取原始文件名
                String originalFilename = multipartFile.getOriginalFilename();
                // 判断文件是否存在
                if (!originalFilename.isEmpty()) {
                    String prefixFileName = "";
                    int position = originalFilename.lastIndexOf('.');
                    if (position > 0) {
                        prefixFileName = originalFilename.substring(position);
                    }
                    // 定义上传路径
                    fileName = getTempFile(tempPath, prefixFileName, fileSaveName);
                    File localFile = new File(fileName);
                    try {
                        multipartFile.transferTo(localFile);
                        (new File(originalFilename)).delete();
                    } catch (Exception e) {
                        log.error("删除文件[" + originalFilename + "]错误", e);
                    }
                }
            }
        }
        return fileName;
    }

    private String getTempFile(String tempPath, String ext, String fileSaveName) {
        try {
            File file = new File(tempPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            throw BasicErrCodes.FILE_CREATE_ERROR.exception(tempPath, e);
        }

        String fileName;

        tempPath = tempPath.replace('\\', '/');
        if (!tempPath.endsWith("/")) {
            tempPath = tempPath + "/";
        }

        fileName = tempPath + fileSaveName + ext;
        File file = new File(fileName);
        if (file.exists()) {
            boolean rc = file.delete();
            if (!rc) {
                log.error("删除文件[" + fileName + "]错误");
                throw BasicErrCodes.FILE_DELETE_ERROR.exception(fileName);
            }
        }

        return fileName;
    }

    @SuppressWarnings("rawtypes")
    public static <T> T toBean(Class<T> clazz, Map map, List<String> list) {
        T obj = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            obj = clazz.newInstance(); // 创建 JavaBean 对象

            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                    Object value = map.get(propertyName);
                    Object[] args = new Object[1];
                    if (Integer.parseInt(value + "") > list.size() - 1) {
                        continue;
                    }
                    args[0] = list.get(Integer.parseInt(value + ""));
                    try {
                        descriptor.getWriteMethod().invoke(obj, args);
                    } catch (InvocationTargetException e) {
                        System.out.println("字段映射失败");
                    }
                }
            }
        } catch (IllegalAccessException e) {
            log.error("实例化 JavaBean失败", e);
        } catch (IntrospectionException e) {
            log.error("分析类属性失败", e);
        } catch (IllegalArgumentException e) {
            log.error("映射错误", e);
        } catch (InstantiationException e) {
            log.error("实例化 JavaBean失败", e);
        }
        return (T) obj;
    }

}