package com.mou.election.utils;

import java.util.Calendar;
import java.util.Date;

public class EDateUtils {

    /**
     * 获取之后的时间
     * @param date
     * @param field 单位
     * @param time
     * @return
     */
    public static Date getAfterField(Date date,int field, int time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, time);
        return calendar.getTime();
    }

}
