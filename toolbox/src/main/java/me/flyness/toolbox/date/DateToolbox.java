package me.flyness.toolbox.date;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by bjlizhitao on 2017/1/4.
 * 日期操作工具类
 */
public class DateToolbox {

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
}
