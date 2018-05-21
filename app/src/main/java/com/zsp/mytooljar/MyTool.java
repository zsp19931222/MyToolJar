package com.zsp.mytooljar;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2018/5/21 0021.
 * 一些常用工具类
 */

public class MyTool {
    /**
     * 获取当前日期--（格式2017-12-06）
     *
     * @param format 返回格式("yyyy-MM-dd")
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurrentTime(String format) {
        SimpleDateFormat df;
        if (format == null || format.equals("")) {
            df = new SimpleDateFormat("yyyy-MM-dd");
        } else {
            df = new SimpleDateFormat(format);
        }
        return df.format(Calendar.getInstance().getTime());
    }

    /**
     * 时间戳转字符串
     * */
    public static String getStrTime(String timeStamp){
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        long  l = Long.valueOf(timeStamp);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }
}
