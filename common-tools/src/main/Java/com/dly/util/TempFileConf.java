package com.dly.util;

import com.dly.exception.code.BasicErrCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TempFileConf
{

//    @Value("${temp.path.linux}")
    @Value("/Users/yanxinrong/temp")
    private String linuxPath;

//    @Value("${temp.path.win}")
    @Value("d:/temp")
    private String winPath;

    // 文件序号
    private static int fileIdx = 1;


    public String getTempPath()
    {
        String tempPath = null;
        String osType = Constants.getOsType();
        if( "unix".equals(osType) ){
            tempPath = linuxPath;
        }
        else{
            tempPath = winPath;
        }

        tempPath = tempPath.replace( '\\', '/' );
        if( !tempPath.endsWith("/") ){
            tempPath = tempPath + "/";
        }

        return tempPath;
    }

    public String getJarFile()
    {
    	/*Properties envs = System.getProperties();
    	Set<Entry<Object, Object>> eSet = envs.entrySet();
    	for( Entry<Object, Object> i : eSet ){
    		log.info( i.getKey().toString() + "=" + i.getValue().toString() );
    	}*/

        String fileName = System.getProperty( "sun.java.command" );
        int pos = fileName.indexOf( "--" );
        if( pos > 0 ){
            fileName = fileName.substring(0,  pos).trim();
        }

        if( !fileName.endsWith(".jar") && !fileName.endsWith(".war") ){
            throw BasicErrCodes.NOT_JAR_FILE.exception(fileName);
        }

        if(fileName.startsWith("/")||fileName.contains(":\\")){
            return fileName;
        }

        String userPath = System.getProperty( "user.dir" );
        return userPath + "/" + fileName;
    }

    public synchronized String getTempFileName()
    {
        String fc = String.valueOf( fileIdx++ );
        return getTempPath() + "data"+fc+".xlsx";
    }

}
