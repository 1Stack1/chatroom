package com.example.common.constant;

public class TimeInterval {
    private static final Long beginTime = 1682870400000L;//2023-05-01 00:00:00

    public static long getDifTime(){
        long difMillis = System.currentTimeMillis() - beginTime;
        long difSec = difMillis / 1000;
        long diftime = difSec / 3;//redis每3s进行一次数据刷入数据库
        return diftime;
    }

}
