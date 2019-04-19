package org.springblade.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author hanbin
 */
public class CalendarUtils {


    /**
     * 获取今天零点的日期
     * @return
     */
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
}
