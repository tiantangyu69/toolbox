package me.flyness.toolbox.date;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by bjlizhitao on 2017/1/4.
 * 日期操作工具类
 */
public class DateToolbox {
    private DateToolbox() {
    }

    /**
     * 获取本月最后一天23点59分59秒的时间戳
     *
     * @return
     */
    public static Timestamp getLastTimestampOfMonth() {
        return new Timestamp(getLastDateOfMonth().getTime());
    }

    /**
     * 获取本月最后一天23点59分59秒的日期
     *
     * @return
     */
    public static Date getLastDateOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取本月第一天0点0分0秒的时间戳
     *
     * @return
     */
    public static Timestamp getFirstTimestampOfMonth() {
        return new Timestamp(getFirstDateOfMonth().getTime());
    }

    /**
     * 获取本月第一天0点0分0秒的日期
     *
     * @return
     */
    public static Date getFirstDateOfMonth() {
        Calendar monthStart = Calendar.getInstance();
        monthStart.set(Calendar.DAY_OF_MONTH, 1);
        monthStart.set(Calendar.HOUR_OF_DAY, 0);
        monthStart.set(Calendar.MINUTE, 0);
        monthStart.set(Calendar.SECOND, 0);
        monthStart.set(Calendar.MILLISECOND, 0);

        return monthStart.getTime();
    }

    /**
     * 计算两个时间戳之间的差值
     *
     * @param date1
     * @param date2
     * @param unit  单位
     * @return
     */
    public static long dateDiff(Timestamp date1, Timestamp date2, TimeUnit unit) {
        long diffMillSeconds = date1.getTime() - date2.getTime();
        return unit.convert(diffMillSeconds, TimeUnit.MILLISECONDS);
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static Timestamp getNowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取当天0时0分0秒的时间戳
     *
     * @return
     */
    public static Timestamp getTodayStartTimestamp() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);

        return new Timestamp(todayStart.getTime().getTime());
    }

    /**
     * 获取昨天0时0分0秒的时间戳
     *
     * @return
     */
    public static Timestamp getYesdayStartTimestamp() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.add(Calendar.DATE, -1);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);

        return new Timestamp(todayStart.getTime().getTime());
    }

    /**
     * 获取明天0时0分0秒的时间戳
     *
     * @return
     */
    public static Timestamp getTomorrowStartTimestamp() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.add(Calendar.DATE, 1);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);

        return new Timestamp(todayStart.getTime().getTime());
    }

    /**
     * 获取今日还剩余的秒数
     *
     * @return
     */
    public static int getTodayRemainSeconds() {
        Calendar curDate = Calendar.getInstance();
        Calendar tommorowDate = new GregorianCalendar(curDate.get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate.get(Calendar.DATE) + 1, 0, 0, 0);
        return (int) (tommorowDate.getTimeInMillis() - curDate.getTimeInMillis()) / 1000;
    }

    /**
     * 获取本月剩余的秒数
     *
     * @return
     */
    public static int getMonthReaminSeconds() {
        return (int) dateDiff(getLastTimestampOfMonth(), getNowTimestamp(), TimeUnit.SECONDS);
    }
}
