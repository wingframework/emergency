package com.example.demo.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /*
     * 输入日期格式字符串 返回当前指定格式的字符串
     */
    public static String format(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    public static String parse(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static Date parse(String dateStr) {
        try {
            dateStr = dateStr.replace("Z", " UTC");
            SimpleDateFormat UTCsdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            return UTCsdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseStrToStr(String dateStr) {
        try {
            dateStr = dateStr.replace("Z", " UTC");
            SimpleDateFormat UTCsdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(UTCsdf.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
