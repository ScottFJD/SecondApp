package com.scottfu.sflibrary.util;

import android.provider.ContactsContract;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by fujindong on 2017/7/31.
 */

public class SystemDateUtil {

    public static String getCurrentYYYYMMDD() {
        String date =null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
         date=sdf.format(new java.util.Date());
        return date;
    }

    public static ArrayList<String> getCurrentWeekYMD() {
        ArrayList<String> dateList = new ArrayList<>();
        String[] weeks = new String[7];
        try {
            weeks = WeekToDay.getStringDate(getCurrentYYYYMMDD());
            for (int i = 0; i < weeks.length; i++) {
                dateList.add(weeks[i]);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateList;
    }

    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd");
        String result = format.format(today);
        LogUtil.e("getFetureDate",result);
        Log.e(null, result);
        return result;
    }

    public static String getFetureDateMMDD(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        String result = format.format(today);
        LogUtil.e("getFetureDate",result);
        Log.e(null, result);
        return result;
    }

    public static String getFetureDateYYYYMMDD(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        LogUtil.e("getFetureDate",result);
        Log.e(null, result);
        return result;
    }


    public static String getWeekOfDateV2(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static String getFetureDateYMD(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        LogUtil.e("getFetureDate",result);
        Log.e(null, result);
        return result;
    }

    public static ArrayList<String> getFetureSevenDayYMD() {
        ArrayList<String> dateList = new ArrayList<>();
        dateList.add(getFetureDateYMD(0));
        dateList.add(getFetureDateYMD(1));
        dateList.add(getFetureDateYMD(2));
        dateList.add(getFetureDateYMD(3));
        dateList.add(getFetureDateYMD(4));
        dateList.add(getFetureDateYMD(5));
        dateList.add(getFetureDateYMD(6));
        return dateList;
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static Date getDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        return today;
    }

    public static String[] getCurrentWeek() {
        String[] weekDays = new String[7];
        String[] weeks = new String[7];
        try {
            weeks = WeekToDay.getStringDate(getCurrentYYYYMMDD());
            for (int i = 0; i < weeks.length; i++) {
                String[] a = weeks[i].split("-");
                weekDays[i] = a[2];
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return weekDays;
    }






}
