package com.breast.oil.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * Created by B04e on 2017/11/28.
 */
public class TimeUtils {
    static String ad_startTimes = null;
    static String ad_stopTimes = null;

    /**
     * 转化成时间戳
     *
     * @param localDateTime
     * @return
     */
    public static long localDateTimeToTimes(String localDateTime) {
        return LocalDateTime.parse(localDateTime).toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 时间戳转换成时间格式
     *
     * @param time
     * @return
     */
    public static String timesToDate(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(time);
    }

    public static long DateTimeParse(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = sdf.parse(time);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static boolean isAdTimes() {
        if (ad_startTimes == null || ad_stopTimes == null) {
            Properties properties = new Properties();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("/www/adtime.properties"));
                properties.load(bufferedReader);
                ad_startTimes = properties.getProperty("adstart");
                ad_stopTimes = properties.getProperty("adsop");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
        Date now = null;
        Date beginTime = null;
        Date endTime = null;
        try {
            now = df.parse(df.format(new Date()));
            beginTime = df.parse(ad_startTimes);
            endTime = df.parse(ad_stopTimes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return !belongCalendar(now, beginTime, endTime);
    }


    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}
