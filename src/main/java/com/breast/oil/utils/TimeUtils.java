package com.breast.oil.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * Created by B04e on 2017/11/28.
 */
public class TimeUtils {

    /**
     * 转化成时间戳
     * @param localDateTime
     * @return
     */
    public static long localDateTimeToTimes(String localDateTime){
        return LocalDateTime.parse(localDateTime).toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 时间戳转换成时间格式
     * @param time
     * @return
     */
    public static String timesToDate(long time){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(time);
    }
}
