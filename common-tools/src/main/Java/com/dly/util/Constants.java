package com.dly.util;

public class Constants {
    /**
     * 缺省字符集
     */
    public static final String CHARSET_NAME = "GBK";
    public static final String MAIL_CHARSET_NAME = "UTF-8";

    /**
     * 用于保存单点登入的令牌节点
     */
    public static final String SSO_TOKEN_NODE = "SSOTOKEN";
    public static final String SSO_TOKEN_KEY = "LOGIN_KEY";

    // 非压缩报文长度
    public static final int REP_PLAIN_LENGTH = 16384;

    public final static String APP = "APP";
    public final static String WEB = "WEB";

    public final static String HTTP_PREFIX = "http://";
    public final static String COMMA = ",";
    public final static String COLON = ":";

    /**
     * 取操作系统类型
     * windows  or  unix
     */
    public static String getOsType()
    {
        String osName = System.getProperty( "os.name" );
        if( osName == null || osName.length() == 0 ){
            osName = "windows";
        }
        else{
            osName = osName.toLowerCase();
            if( osName.startsWith("windows") ){
                osName = "windows";
            }
            else{
                osName = "unix";
            }
        }

        return osName;
    }

}