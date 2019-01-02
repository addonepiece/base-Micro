package com.dly.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StringUtil {
    // 十六进制数据
    private static final char[] hexCode = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'
    };

    private StringUtil()
    {

    }

    /**
     *
     * Method description : 判断参数是否为空。
     *
     */
    public static boolean isEmpty(String s) {
        if (null == s || "".equals(s)) {
            return true;
        }
        return false;
    }

    public static boolean equals( String s1, String s2 )
    {
        return (s1 == s2 || (s1 != null && s1.equals(s2)));
    }

    /**
     @param value
     @return java.lang.String
     */
    public static String filter(String value)
    {
        if (value == null || value.length() == 0 ) {
            return value;
        }

        int len = value.length();
        char[] content = new char[len];
        value.getChars(0, len, content, 0);

        for (int i = 0; i < len; i++) {
            switch(content[i]){
                case '\\':
                    content[i] = '/';
                    break;
                case '\n':
                    content[i] = '`';
                    break;
                case '\r':
                    content[i] = '`';
                    break;
                case '\'':
                    content[i] = '`';
                    break;
            }
        }

        return String.valueOf( content );
    }

    /**
     * 把\转换成/
     */
    public static String replaceSlash(String value)
    {
        if (value == null) {
            return (null);
        }

        int len = value.length();
        char content[] = new char[len];
        value.getChars(0, len, content, 0);

        for (int i = 0; i < len; i++) {
            switch (content[i]) {
                case '\\':
                    if( i == len-1 || content[i+1] != 'n' ){
                        content[i] = '/';
                    }

                    break;
                case 0x0a:
                    content[i] = '`';
                    break;
                case 0x0d:
                    content[i] = '`';
            }
        }

        return String.valueOf( content );
    }

    /**
     * 根据逗号和分号分割字符串
     * @param value
     * @return
     */
    public static String[] split( String value )
    {
        List<String> result = new ArrayList<String>();

        int len = value.length();
        char content[] = new char[len];
        value.getChars(0, len, content, 0);

        int i;
        int start = 0;
        for (i = 0; i < len; i++) {
            if( content[i] == ';' || content[i] == ',' ){
                String v = String.valueOf(content, start, i-start);
                start = i + 1;
                result.add( v );
            }
        }

        String v = String.valueOf(content, start, i-start);
        result.add( v );

        return result.toArray( new String[0] );
    }

    public static String[] split( String value, char splitChar )
    {
        List<String> result = new ArrayList<String>();

        int len = value.length();
        char[] content = new char[len];
        value.getChars(0, len, content, 0);

        int i;
        int start = 0;
        if( splitChar == ' ' ){
            for( i = 0; i<len; i++ ){
                if( content[i] == splitChar ){
                    if( i > start ){
                        String v = String.valueOf(content, start, i-start);
                        result.add( v );
                    }

                    start = i + 1;
                }
            }

            if( i > start ){
                String v = String.valueOf(content, start, i-start);
                result.add( v );
            }
        }
        else{
            for (i = 0; i < len; i++) {
                if( content[i] == splitChar ){
                    String v = String.valueOf(content, start, i-start);
                    start = i + 1;
                    result.add( v );
                }
            }

            String v = String.valueOf(content, start, i-start);
            result.add( v );
        }

        return result.toArray( new String[0] );
    }

    public static String[] split( String value, char splitChar1, char splitChar2 )
    {
        List<String> result = new ArrayList<String>();

        int len = value.length();
        char[] content = new char[len];
        value.getChars(0, len, content, 0);

        int i;
        int start = 0;
        for (i = 0; i < len; i++) {
            if( content[i] == splitChar1 || content[i] == splitChar2 ){
                if( i > start ){
                    String v = String.valueOf(content, start, i-start);
                    result.add( v );
                }

                start = i + 1;
            }
        }

        if( i > start ){
            String v = String.valueOf(content, start, i-start);
            result.add( v );
        }

        return result.toArray( new String[0] );
    }

    /**
     * 连接字符串
     * @param value
     * @param splitChar
     * @return
     */
    public static String join( String[] value, char splitChar )
    {
        if( value == null || value.length == 0 ){
            return null;
        }

        StringBuilder result = new StringBuilder();
        result.append( value[0] );

        for( int ii=1; ii<value.length; ii++ ){
            result.append( splitChar );
            result.append( value[ii] );
        }

        return result.toString();
    }

    public static String join( String[] value, String splitChar )
    {
        if( value == null || value.length == 0 ){
            return null;
        }

        StringBuilder result = new StringBuilder();
        result.append( value[0] );

        for( int ii=1; ii<value.length; ii++ ){
            result.append( splitChar );
            result.append( value[ii] );
        }

        return result.toString();
    }


    /**
     * 删除右边的空格
     * @param value
     * @return
     */
    public static String rtrim( String value )
    {
        if( value == null || value.isEmpty() ){
            return value;
        }

        char[] val = value.toCharArray();
        int len = val.length;

        while( (0 < len) && (val[len - 1] <= ' ') ){
            len--;
        }

        return String.valueOf( val, 0, len );
    }

    /**
     * 转换成G.B.K字符
     * @param value
     * @param charsetName
     * @return
     */
    public static String toGB2312( String value, String charsetName )
    {
        try{
            value = new String(
                    value.getBytes(charsetName),
                    Constants.CHARSET_NAME
            );
        }
        catch( Exception e ){

        }

        return value;
    }

    /**
     * 把G.B.K的字符串转换成其他字符串
     * @param value
     * @param charsetName
     * @return
     */
    public static String fromGB2312( String value, String charsetName )
    {
        try{
            value = new String( value.getBytes(Constants.CHARSET_NAME), charsetName );
        }
        catch( Exception e ){

        }

        return value;
    }

    /**
     * 二进制数据转换成十六进制
     * @param b
     * @return
     */
    public static String byte2hex( byte[] b )
    {
        int c;
        int i = 0;
        char[] result = new char[2*b.length];
        for( int n=0; n<b.length; n++ ){
            c = (b[n] & 0XFF) >> 4;
            result[i++] = hexCode[c];

            c = b[n] & 0X0F;
            result[i++] = hexCode[c];
        }

        return String.valueOf( result );
    }

    /**
     * 十六进制转换成二进制数据
     * @param hex
     * @return
     */
    public static byte[] hex2byte( String hex )
    {
        int c1, c2;
        int i = 0;
        hex = hex.toUpperCase();
        byte[] data = hex.getBytes();
        int len = data.length;

        // 转换
        byte[] result = new byte[len/2];
        for( int n=0; n<len; n+=2 ){
            c1 = data[n];
            if( c1>='0' && c1<='9' ){
                c1 = c1 - '0';
            }
            else{
                c1 = 10 + c1 - 'A';
            }

            c2 = data[n+1];
            if( c2>='0' && c2<='9' ){
                c2 = c2 - '0';
            }
            else{
                c2 = 10 + c2 - 'A';
            }

            result[i++] = (byte)(c1*16 + c2);
        }

        return result;
    }

    // 输出二进制数据
    public static void outputBinary( byte[] data )
    {
        int k = 0;
        StringBuilder r = new StringBuilder();
        for( int ii=0; ii<data.length; ii++ ){
            int x = data[ii];
            String s = Integer.toHexString( x & 0XFF );
            if( s.length() == 1 ){
                s = "0" + s;
            }

            r.append(s).append(" ");
            if(++k == 8){
                r.append( "\n" );
                k = 0;
            }
        }

        log.info( r.toString() );
    }

    /**
     * 连接字符串
     * @param value
     * @return
     */
    public static String toString( String... value )
    {
        StringBuilder result = new StringBuilder();
        if( value != null && value.length > 0 ){
            result.append( value[0] );
            for( int ii=1; ii<value.length; ii++ ){
                result.append( ',' );
                if( value[ii] != null && !value[ii].isEmpty() ){
                    result.append( value[ii] );
                }
            }
        }

        return result.toString();
    }

    public static String formatClazzName( String str )
    {
        String formatName = "";
        String[] names = StringUtil.split(str, '_');
        for( String name : names ){
            if( !name.isEmpty() ){
                String firstChar = name.substring(0, 1);
                formatName = formatName + firstChar.toUpperCase() + name.substring(1);
            }
        }

        return formatName;
    }

    public static String formatVarName( String str )
    {
        String formatName = "";
        String[] names = StringUtil.split(str, '_');
        for( String name : names ){
            if( !name.isEmpty() ){
                if( formatName.isEmpty() ){
                    formatName = name;
                }
                else{
                    String firstChar = name.substring(0, 1);
                    formatName = formatName + firstChar.toUpperCase() + name.substring(1);
                }
            }
        }

        return formatName;
    }

}