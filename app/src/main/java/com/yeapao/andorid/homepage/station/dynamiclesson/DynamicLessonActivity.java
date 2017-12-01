package com.yeapao.andorid.homepage.station.dynamiclesson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.scottfu.sflibrary.springindicator.ScrollerViewPager;
import com.scottfu.sflibrary.springindicator.SpringIndicator;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.SystemDateUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;

/**
 * Created by fujindong on 2017/11/22.
 */

public class DynamicLessonActivity extends BaseActivity {

    private static final String TAG = "DynamicLessonActivity";

    ScrollerViewPager viewPager;

    private int currentDay = 0;

    private TextView oneDay;
    private TextView twoDay;
    private TextView threeDay;
    private TextView fourDay;
    private TextView fiveDay;
    private TextView sixDay;
    private TextView sevenDay;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, DynamicLessonActivity.class);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_lesson);
        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);
        oneDay = (TextView) findViewById(R.id.tv_one_day);
        twoDay = (TextView) findViewById(R.id.tv_two_day);
        threeDay = (TextView) findViewById(R.id.tv_three_day);
        fourDay = (TextView) findViewById(R.id.tv_four_day);
        fiveDay = (TextView) findViewById(R.id.tv_five_day);
        sixDay = (TextView) findViewById(R.id.tv_six_day);
        sevenDay = (TextView) findViewById(R.id.tv_seven_day);

        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(DynamicLessonListFragment.class, SystemDateUtil.getCurrentWeekYMD(), getTitlesV2());
        ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed();
        viewPager.setCurrentItem(currentDay);
        springIndicator.setViewPager(viewPager);
        initTopBar();
        setDateTitle();
    }

    @Override
    protected void initTopBar() {
        initTitle("动感课程");
        initBack();
    }


    private List<String> getTitlesV2() {
        ArrayList<String> days = new ArrayList<>();
        String date = SystemDateUtil.getCurrentYYYYMMDD();
        days.add("今");
        for (int i = 1; i < 7; i++) {
            days.add(SystemDateUtil.getFetureDate(i));
        }
        return days;
    }

    private void setDateTitle() {
        oneDay.setText(SystemDateUtil.getWeekOfDate(SystemDateUtil.getDate(0)));
        twoDay.setText(SystemDateUtil.getWeekOfDate(SystemDateUtil.getDate(1)));
        threeDay.setText(SystemDateUtil.getWeekOfDate(SystemDateUtil.getDate(2)));
        fourDay.setText(SystemDateUtil.getWeekOfDate(SystemDateUtil.getDate(3)));
        fiveDay.setText(SystemDateUtil.getWeekOfDate(SystemDateUtil.getDate(4)));
        sixDay.setText(SystemDateUtil.getWeekOfDate(SystemDateUtil.getDate(5)));
        sevenDay.setText(SystemDateUtil.getWeekOfDate(SystemDateUtil.getDate(6)));
        LogUtil.e("星期几", SystemDateUtil.getWeekOfDate(SystemDateUtil.getDate(0)));
        LogUtil.e("星期几", SystemDateUtil.getWeekOfDate(SystemDateUtil.getDate(1)));
        LogUtil.e("星期几", SystemDateUtil.getWeekOfDate(SystemDateUtil.getDate(2)));
        LogUtil.e("星期几", SystemDateUtil.getWeekOfDate(SystemDateUtil.getDate(3)));
        LogUtil.e("星期几", SystemDateUtil.getWeekOfDate(SystemDateUtil.getDate(4)));
        LogUtil.e("星期几", SystemDateUtil.getWeekOfDate(SystemDateUtil.getDate(5)));
        LogUtil.e("星期几", SystemDateUtil.getWeekOfDate(SystemDateUtil.getDate(6)));
    }

    private List<String> getTitles(){
        ArrayList<String> days = new ArrayList<>();

//        TODO 计算时间

        String date = SystemDateUtil.getCurrentYYYYMMDD();
        String[] day = date.split("-");
        String[] w = SystemDateUtil.getCurrentWeek();
        for (int i = 0; i < w.length; i++) {

            if (w[i].equals(day[2])) {
                currentDay = i;
                days.add("今");
            } else {
                days.add(w[i]);
            }
        }
        return days;
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
