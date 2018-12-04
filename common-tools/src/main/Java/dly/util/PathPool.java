package dly.util;

import org.springframework.beans.factory.annotation.Value;

public class PathPool {

    @Value("${win.temp.path}")
    private String winTempPath;

    @Value("${unix.temp.path}")
    private String unixTempPath;

    @Value("${win.log.path}")
    private String winLogPath;

    @Value("${unix.log.path}")
    private String unixLogPath;

    private static PathPool instance = new PathPool();
    private PathPool()
    {

    }

    public static String getTempFilePath()
    {
        String osType = Constants.getOsType();
        if( "unix".equals(osType) ){
            return instance.unixTempPath;
        }
        else{
            return instance.winTempPath;
        }
    }

    public static String getLogFilePath()
    {
        String osType = Constants.getOsType();
        if( "unix".equals(osType) ){
            return instance.unixLogPath;
        }
        else{
            return instance.winLogPath;
        }
    }
}