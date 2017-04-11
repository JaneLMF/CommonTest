package com.common.utlis;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jane on 16/3/31.
 */
public class DateUtils {
    public static String yyyy_MM_dd = "yyyy-MM-dd";//2016-02-25
    public static String yyyy_MM_dd__HH_mm_ss = "yyyy-MM-dd HH:mm:ss";//2016-02-25 00:17:11
    public static String HH_mm_ss = "HH:mm:ss";//00:17:11
    /**
     * 00:17
     */
    public static String HH_mm = "HH:mm";

    /**
     * 根据输入的日期字符串 和 提前天数 ，
     * 获得 当前日期提前几天的日期对象
     * @param beforeDays 倒推的天数
     * @return 指定日期倒推指定天数后的日期对象
     * @throws ParseException
     */
    public Date getDate(int beforeDays){
        Date inputDate = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(inputDate);

        int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
        cal.set(Calendar.DAY_OF_YEAR , inputDayOfYear - beforeDays );

        return cal.getTime();
    }

    /**
     * 根据输入的日期字符串 和 提前天数 ，
     * 获得 指定日期提前几天的日期对象
     * @param dateString 日期对象 ，格式如 1900-1-31
     * @param beforeDays 倒推的天数
     * @return 指定日期倒推指定天数后的日期对象
     * @throws ParseException
     */
    public Date getDate(String dateString , int beforeDays) throws ParseException{
        Date inputDate =parse(dateString, yyyy_MM_dd);

        Calendar cal = Calendar.getInstance();
        cal.setTime(inputDate);

        int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
        cal.set(Calendar.DAY_OF_YEAR , inputDayOfYear - beforeDays );

        return cal.getTime();
    }


    public Date parse(String dateString, String pattern) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(dateString);
    }

    /**
     *
     * @param pattern
     * @return
     */
    public String format(Date date, String pattern){
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public String format(Long mill, String pattern){
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(new Date(mill));
    }
}
