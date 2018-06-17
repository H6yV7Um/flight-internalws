package com.ctrip.ibu.flight.internalws.common.utils;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期相关工具
 * Created by kyxie on 2017/8/16.
 */
public final class DateUtil {

    private final static String ELAPSEDTIME_DESC_PATTERN = "${minute}minutes ${second}seconds ${millis}millis";

    /**
     * Long数据转化为Date
     * @param millis 毫秒数
     * */
    public static Date longToDate(Long millis){
        return new Date(millis - TimeZone.getDefault().getRawOffset());
    }

    public static Date gregorianCalendarToDate(XMLGregorianCalendar gregorianCalendar){
        return gregorianCalendar.toGregorianCalendar().getTime();
    }

    public static Calendar gregorianCalendarToCalendar(XMLGregorianCalendar gregorianCalendar){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(gregorianCalendarToDate(gregorianCalendar));
        return calendar;
    }

    public static Calendar longToCalendar(Long mills){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(longToDate(mills));
        return calendar;
    }

    public static String getDateDesc(Date date, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(date);
    }

    public static String getDateDesc(Long curMills, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(new Date(curMills));
    }

    /**
     * 新增日期
     * @param baseDate 基准日期
     * @param addedDays 需要新增的天数
     * */
    public static Date addDays(Date baseDate,Integer addedDays){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(baseDate);
        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR) + addedDays);
        return calendar.getTime();
    }

    public static String getElapsedTimeDesc(Long elapsedTimeMillis){
        if (elapsedTimeMillis == null){
            return "";
        }

        return ELAPSEDTIME_DESC_PATTERN.replace("${minute}",String.valueOf(elapsedTimeMillis/(60*1000)))
                .replace("${second}",String.valueOf((elapsedTimeMillis%(60*1000))/1000))
                .replace("${millis}",String.valueOf(elapsedTimeMillis%1000));

    }
}
