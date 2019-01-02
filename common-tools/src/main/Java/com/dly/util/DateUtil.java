package com.dly.util;

import com.dly.exception.ProjectErrException;
import com.dly.exception.ProjectException;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DateUtil {
    // 中文数字
    private static final String s1[] = { "О", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };

    private DateUtil()
    {

    }

    /**
     *
     * Method description : 比较两个日期的大小。
     *
     * @param DATE1
     * @param DATE2
     * @return 比较结果。(DATE1在DATE2前,返回-1;DATE1在DATE2后,返回1;DATE1和DATE2相等返回0。)
     *
     */
    public static int compareDate(String DATE1, String DATE2) throws ProjectException {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            //DATE1>DATE2
            if (dt1.getTime() > dt2.getTime()) {
                return -1;
            }
            //DATE1<DATE2
            else if (dt1.getTime() < dt2.getTime()) {
                return 1;
            }
            //DATE1=DATE2
            else {
                return 0;
            }
        } catch (Exception exception) {
            throw new ProjectErrException( "100405", "日期的格式转换错误。" );
        }
    }

    /**
     *
     * Method description : 比较两个日期的大小。
     *
     * @param DATE1
     * @param DATE2
     * @return 比较结果。(DATE1在DATE2前,返回-1;DATE1在DATE2后,返回1;DATE1和DATE2相等返回0。)
     *
     */
    public static int compareDate2(String DATE1, String DATE2) throws ProjectException {

        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            //DATE1>DATE2
            if (dt1.getTime() > dt2.getTime()) {
                return -1;
            }
            //DATE1<DATE2
            else if (dt1.getTime() < dt2.getTime()) {
                return 1;
            }
            //DATE1=DATE2
            else {
                return 0;
            }
        } catch (Exception exception) {
            throw new ProjectErrException( "100405", "日期的格式转换错误。" );
        }
    }

    /**
     * 当前日期[yyyy-mm-dd hh:MM:ss]
     * @return
     * @throws ParseException
     */
    public static Date currentStringToDate(String date) throws ParseException
    {
        Date dates=null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(date!=null&&!("").equals(date)){
            dates = sdf.parse(date);
        }

        return dates;
    }

    /**
     * 当前日期[yyyy-mm-dd]
     * @return
     */
    public static String currentSySDate()
    {
        Calendar cal = Calendar.getInstance();
        String val = cal.get(Calendar.YEAR) +"-"+
                (cal.get(Calendar.MONTH)+1) + "-"+
                cal.get(Calendar.DAY_OF_MONTH);

        return val;
    }

    /**
     * 当前日期[yyyymmdd]
     * @return
     */
    public static String currentDate()
    {
        Calendar cal = Calendar.getInstance();
        int val = cal.get(Calendar.YEAR) * 10000 +
                (cal.get(Calendar.MONTH)+1) * 100 +
                cal.get(Calendar.DAY_OF_MONTH);

        return String.valueOf(val);
    }

    /**
     * 取当前日期的偏移日期
     * @param days 日数量
     * @return
     */
    public static String afterDate( int days )
    {
        // 当前日期
        Calendar cal = Calendar.getInstance();

        // 计算偏移
        cal.add( Calendar.DATE, days );

        int val = cal.get(Calendar.YEAR) * 10000 +
                (cal.get(Calendar.MONTH)+1) * 100 +
                cal.get(Calendar.DAY_OF_MONTH);

        return String.valueOf(val);
    }

    /**
     * 计算下一个日期
     * @param date 日期字符串[yyyymmdd]
     * @param days 日数量
     * @return
     */
    public static String afterDate( String date, int days )
    {
        // 当前日期
        Calendar cal = Calendar.getInstance();

        try{
            cal.set( Calendar.YEAR, Integer.parseInt(date.substring(0,4)) );
            cal.set( Calendar.MONTH, Integer.parseInt(date.substring(4,6))-1 );
            cal.set( Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6,8)) );
        }
        catch( Throwable e ){

        }

        // 计算偏移
        cal.add( Calendar.DATE, days );

        int val = cal.get(Calendar.YEAR) * 10000 +
                (cal.get(Calendar.MONTH)+1) * 100 +
                cal.get(Calendar.DAY_OF_MONTH);

        return String.valueOf(val);
    }

    /**
     * 取当前日期的偏移日期
     * @param months 月数量
     * @return
     */
    public static String afterMonth( int months )
    {
        // 当前日期
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.set( Calendar.DAY_OF_MONTH, 1 );

        // 计算偏移
        cal.add( Calendar.MONTH, months );

        // 取月的最大天数
        int day1 = cal.getActualMaximum( Calendar.DAY_OF_MONTH );
        if( day > day1 ){
            day = day1;
        }

        int val = cal.get(Calendar.YEAR) * 10000 +
                (cal.get(Calendar.MONTH)+1) * 100 +
                day;

        return String.valueOf(val);
    }

    /**
     * 计算下一个日期
     * @param date 日期字符串[yyyymmdd]
     * @param months 月数量
     * @return
     */
    public static String afterMonth( String date, int months )
    {
        // 当前日期
        Calendar cal = Calendar.getInstance();

        int day = 0;
        try{
            cal.set( Calendar.YEAR, Integer.parseInt(date.substring(0,4)) );
            cal.set( Calendar.MONTH, Integer.parseInt(date.substring(4,6))-1 );
            cal.set( Calendar.DAY_OF_MONTH, 1 );

            // 计算偏移
            cal.add( Calendar.MONTH, months );

            // 取月的最大天数
            int day1 = cal.getActualMaximum( Calendar.DAY_OF_MONTH );
            day = Integer.parseInt(date.substring(6,8));
            if( day > day1 ){
                day = day1;
            }
        }
        catch( Throwable e ){

        }

        int val = cal.get(Calendar.YEAR) * 10000 +
                (cal.get(Calendar.MONTH)+1) * 100 +
                day;

        return String.valueOf(val);
    }

    /**
     * Returns the current time in the standard format as 'YYYY-MM-DD HH24:MI:SS'.
     *
     * @return current time as a string
     */
    public static String getStandardFormattedCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = formatter.format(new Date());
        return now;
    }

    /**
     * 当前时间[hhMMss]
     * @return
     */
    public static String currentTime( )
    {
        String result;
        Calendar cal = Calendar.getInstance();

        int val = cal.get( Calendar.HOUR_OF_DAY ) * 10000 +
                cal.get( Calendar.MINUTE ) * 100 +
                cal.get( Calendar.SECOND );

        result = "000000" + String.valueOf(val);
        int len = result.length();
        result = result.substring( len-6 );

        return result;
    }

    /**
     * 日期和时间[yyyymmddhhMMss]
     * @return
     */
    public static String currentDateTime( )
    {
        Calendar cal = Calendar.getInstance();

        // 日期
        int val = cal.get(Calendar.YEAR) * 10000 +
                (cal.get(Calendar.MONTH) + 1) * 100 +
                cal.get(Calendar.DAY_OF_MONTH);

        // 时间
        int val1 = cal.get( Calendar.HOUR_OF_DAY ) * 10000 +
                cal.get( Calendar.MINUTE ) * 100 +
                cal.get( Calendar.SECOND );

        String result;
        if( val1 < 10000 ){
            result = String.valueOf(val) + "00" + String.valueOf(val1);
        }
        else if( val1 < 100000 ){
            result = String.valueOf(val) + "0" + String.valueOf(val1);
        }
        else{
            result = String.valueOf(val) + String.valueOf(val1);
        }

        return result;
    }

    /**
     * 计算时间
     * date 日数量
     * @return
     */
    public static String afterDateTime( int date )
    {
        Calendar cal = Calendar.getInstance();

        // 计算偏移
        cal.add( Calendar.DATE, date );

        // 日期
        int val = cal.get(Calendar.YEAR) * 10000 +
                (cal.get(Calendar.MONTH) + 1) * 100 +
                cal.get(Calendar.DAY_OF_MONTH);

        // 时间
        int val1 = cal.get( Calendar.HOUR_OF_DAY ) * 10000 +
                cal.get( Calendar.MINUTE ) * 100 +
                cal.get( Calendar.SECOND );


        if( val1 < 10000 ){
            return String.valueOf(val) + "00" + String.valueOf(val1);
        }
        else if( val1 < 100000 ){
            return String.valueOf(val) + "0" + String.valueOf(val1);
        }
        else{
            return String.valueOf(val) + String.valueOf(val1);
        }
    }

    /**
     * 取日期[yyyymmdd]
     * @param date 日期
     * @return
     */
    public static String getDate( Date date )
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime( date );

        int val = cal.get(Calendar.YEAR) * 10000 +
                (cal.get(Calendar.MONTH)+1) * 100 +
                cal.get(Calendar.DAY_OF_MONTH);

        return String.valueOf(val);
    }

    /**
     * 当前日期加上天数后的日期
     * @param num 为增加的天数
     * @return
     */
    public static String plusDay(int num){
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        d = ca.getTime();
        String enddate = format.format(d);
        return enddate;
    }

    /**
     * 当前日期加上月数
     * @param num 为增加的月数
     * @return
     */
    public static String plusMonth(int num){
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MONTH, num);// num为增加的天数，可以改变的
        d = ca.getTime();
        String enddate = format.format(d);
        return enddate;
    }

    /**
     * 取日期和时间[yyyymmddhhMMss]
     * @param date 日期
     * @return
     */
    public static String getDateTime( Date date )
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime( date );

        // 日期
        int val = cal.get(Calendar.YEAR) * 10000 +
                (cal.get(Calendar.MONTH) + 1) * 100 +
                cal.get(Calendar.DAY_OF_MONTH);

        // 时间
        int val1 = cal.get( Calendar.HOUR_OF_DAY ) * 10000 +
                cal.get( Calendar.MINUTE ) * 100 +
                cal.get( Calendar.SECOND );

        if( val1 < 10000 ){
            return String.valueOf(val) + "00" + String.valueOf(val1);
        }
        else if( val1 < 100000 ){
            return String.valueOf(val) + "0" + String.valueOf(val1);
        }
        else{
            return String.valueOf(val) + String.valueOf(val1);
        }
    }


    /**
     * 生成日期
     * @param source [yyyymmdd]、[hhMMss]、[yyyymmddhhMMss]转换成日期
     * @return
     */
    public static Date parse( String source )
    {
        try{
            int len = source.length();
            if( len == 8 ){
                Calendar cal = Calendar.getInstance();

                // 设置年月日
                int y = Integer.parseInt( source.substring(0, 4) );
                int m = Integer.parseInt( source.substring(4, 6) );
                int d = Integer.parseInt( source.substring(6, 8) );
                cal.set( y, m-1, d );

                return cal.getTime();
            }
            else if( len == 6 ){
                Calendar cal = Calendar.getInstance();

                // 设置时间
                int h = Integer.parseInt( source.substring(0, 2) );
                int m = Integer.parseInt( source.substring(2, 4) );
                int s = Integer.parseInt( source.substring(4, 6) );
                cal.set( Calendar.HOUR_OF_DAY, h );
                cal.set( Calendar.MINUTE, m );
                cal.set( Calendar.SECOND, s );

                return cal.getTime();
            }
            else if( len == 14 ){
                Calendar cal = Calendar.getInstance();

                // 设置年月日、时分秒
                int y = Integer.parseInt( source.substring(0, 4) );
                int m = Integer.parseInt( source.substring(4, 6) );
                int d = Integer.parseInt( source.substring(6, 8) );
                int hh = Integer.parseInt( source.substring(8, 10) );
                int mm = Integer.parseInt( source.substring(10, 12) );
                int ss = Integer.parseInt( source.substring(12, 14) );
                cal.set( y, m-1, d, hh, mm, ss );

                return cal.getTime();
            }
            else{
                return null;
            }
        }
        catch( Throwable e ){
            return null;
        }
    }

    /**
     * 生成日期[yyyy年mm月dd日]
     * @param source 日期
     * @return
     */
    public static String convertChineseDate( String source ) throws ProjectException
    {
        try{
            String y, d;
            int m;
            int len = source.length();
            if( len == 8 || len == 14 ){
                // 取年月日
                y = source.substring(0, 4);
                m = Integer.parseInt( source.substring(4, 6) );
                d = source.substring(6, 8);

                // 保存结果
                StringBuilder result = new StringBuilder( 64 );

                // 年
                int n1, n2;
                for( int ii=0; ii<4; ii++ ){
                    n1 = y.charAt(ii) - '0';
                    result.append( s1[n1] );
                }

                result.append( "年" );

                // 月
                if( m > 12 || m < 1 ){
                    throw new ProjectErrException( "100405", "日期的格式错误，月只能是[1~12]" );
                }

                result.append( s1[m] );
                result.append( "月" );

                // 日
                n1 = Integer.parseInt( d );
                if( n1 > 31 || n1 < 1 ){
                    throw new ProjectErrException( "100405", "日期的格式错误，日只能是[1~31]" );
                }

                n1 = d.charAt(0) - '0';
                n2 = d.charAt(1) - '0';
                if( n1 == 1 ){
                    result.append( "十" );
                }
                else if( n1 == 2 ){
                    result.append( "二" );
                }
                else if( n1 == 3 ){
                    result.append( "三" );
                }

                if( n2 > 0 ){
                    result.append( s1[n2] );
                }
                else if( n1 > 1 ){
                    result.append( "十" );
                }

                result.append( "日" );

                return result.toString();
            }
            else{
                return null;
            }
        }
        catch( Throwable e ){
            return null;
        }
    }

    /**
     * 当前日期[yyyy-mm-dd hh:MM:ss]
     * @return
     */
    public static String longDateTime( )
    {
        Calendar cal = Calendar.getInstance();
        return substringDate(longDateTime( cal ));
    }

    /**
     *
     * Method description : 2017-12-14 14:02:27 476 截取到2017-12-14 14:02:27
     *
     * Author：        ciki
     * Create Date：   2017年12月14日 下午3:24:07
     * History:  2017年12月14日 下午3:24:07   ciki   Created.
     *
     * @param dateString
     * @return
     *
     */
    public static String substringDate(String dateString){
        if (dateString != null && !dateString.isEmpty() && dateString.length()>16) {
            return dateString.substring(0, 19);
        }else {
            return dateString;
        }
    }

    /**
     * 毫秒转换成日期[yyyy-mm-dd hh:MM:ss]
     * @param millis 毫秒
     * @return
     */
    public static String longDateTime( long millis )
    {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis( millis );
        return longDateTime( cal );
    }

    public static String longDateTime( Calendar cal )
    {
        int	v;
        StringBuilder result = new StringBuilder( 32 );

        // 年
        v = cal.get(Calendar.YEAR);
        result.append( v );
        result.append( "-" );

        // 月
        v = cal.get(Calendar.MONTH) + 1;
        if( v < 10 ){
            result.append( "0" );
        }

        result.append( v );
        result.append( "-" );

        // 日
        v = cal.get(Calendar.DAY_OF_MONTH);
        if( v < 10 ){
            result.append( "0" );
        }

        result.append( v );
        result.append( " " );

        // 时
        v = cal.get(Calendar.HOUR_OF_DAY);
        if( v < 10 ){
            result.append( "0" );
        }

        result.append( v );
        result.append( ":" );

        // 分
        v = cal.get(Calendar.MINUTE);
        if( v < 10 ){
            result.append( "0" );
        }

        result.append( v );
        result.append( ":" );

        // 秒
        v = cal.get(Calendar.SECOND);
        if( v < 10 ){
            result.append( "0" );
        }

        result.append( v );
        result.append( " " );

        // 毫秒
        v = cal.get( Calendar.MILLISECOND );
        if( v < 10 ){
            result.append( "00" );
        }
        else if( v < 100 ){
            result.append( "0" );
        }

        result.append( v );

        return result.toString();
    }

    /**
     * 当临时文件路径[yyyymmdd/hh/]
     * @return
     */
    public static String getTempPath()
    {
        Calendar cal = Calendar.getInstance();
        int val = cal.get(Calendar.YEAR) * 10000 +
                (cal.get(Calendar.MONTH)+1) * 100 +
                cal.get(Calendar.DAY_OF_MONTH);

        int val1 = cal.get(Calendar.MINUTE) * 100000 +
                cal.get(Calendar.SECOND) * 1000 +
                cal.get( Calendar.MILLISECOND );

        return String.valueOf(val) + "/" + cal.get(Calendar.HOUR_OF_DAY) + "/" + val1;
    }

    private static final int[] mdays = {29, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static void isValidDate( int y, int m, int d ) throws ProjectException
    {
        if( y < 1860 ){
            throw new ProjectErrException( "100405", "日期的格式错误，年错误" );
        }

        if( m > 12 || m < 1 ){
            throw new ProjectErrException( "100405", "日期的格式错误，月只能是[1~12]" );
        }

        if( m == 2 ){
            if( !((y%4==0 && y%100!=0) || y%400==0) ){
                m = 0;
            }
        }

        if( d > mdays[m] || d < 1 ){
            throw new ProjectErrException( "100405", "日期的格式错误，日只能是[1~31]" );
        }
    }

    public static void main( String[] args )
    {
        try{
//			System.out.println( "afterDate=" + afterMonth("20080130", 1) );
//			System.out.println( "afterDate=" + afterDate("20081229", 11) );
//			System.out.println( "long time = " + longDateTime() );
//			Thread.sleep( 100 );
//			System.out.println( "long time = " + longDateTime(Calendar.getInstance().getTimeInMillis()) );
            Date date1=DateUtil.currentStringToDate("2017-12-11 14:30:30");
            Date date2=DateUtil.currentStringToDate(DateUtil.longDateTime());
            if(date1.getTime()>date2.getTime()){
                System.out.println("dt1 在dt2后");
            }else{
                System.out.println("dt1在dt2前");
            }
        }
        catch( Throwable e ){
            e.printStackTrace();
        }
    }
}