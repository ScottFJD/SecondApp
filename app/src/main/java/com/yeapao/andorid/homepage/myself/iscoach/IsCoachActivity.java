package com.yeapao.andorid.homepage.myself.iscoach;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.scottfu.sflibrary.util.SystemDateUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.homepage.myself.orders.CangOrderDetailActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import sun.bob.mcalendarview.CellConfig;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.listeners.OnExpDateClickListener;
import sun.bob.mcalendarview.listeners.OnMonthScrollListener;
import sun.bob.mcalendarview.views.ExpCalendarView;
import sun.bob.mcalendarview.vo.DateData;

/**
 * Created by fujindong on 2017/12/25.
 */

public class IsCoachActivity extends BaseActivity{
    private static final String TAG = "IsCoachActivity";


    private int currentDay = 0;
    private String chooseDate = "";

//calendar
    private ExpCalendarView expCalendarView;
    private DateData selectedDate;
    private boolean ifExpand = true;

    private CoachFragment coachFragment;

    //  content coach lesson chat
    private FrameLayout lessonAndChatFrameLayout;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, IsCoachActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iscoach);


        lessonAndChatFrameLayout = (FrameLayout) findViewById(R.id.fl_coach_content);
        coachFragment = new CoachFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data",chooseDate);
        coachFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().add(R.id.fl_coach_content, coachFragment).commit();

//        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);
//        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);
//
//        PagerModelManager manager = new PagerModelManager();
//        manager.addCommonFragment(CoachFragment.class, SystemDateUtil.getCurrentWeekYMD(), getTitles());
//        ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
//        viewPager.setAdapter(adapter);
//        viewPager.fixScrollSpeed();
//        viewPager.setCurrentItem(currentDay);

        // just set viewPager
//        springIndicator.setViewPager(viewPager);

        initTopBar();

        expCalendarView = ((ExpCalendarView) findViewById(R.id.calendar_exp));
        expCalendarView.setMarkedStyle(10, Color.rgb(248, 205, 70));

        //      Set up listeners.
        expCalendarView.setOnDateClickListener(new OnExpDateClickListener()).setOnMonthScrollListener(new OnMonthScrollListener() {
            @Override
            public void onMonthChange(int year, int month) {
//                YearMonthTv.setText(String.format("%d年%d月", year, month));
            }

            @Override
            public void onMonthScroll(float positionOffset) {
//                Log.i("listener", "onMonthScroll:" + positionOffset);
            }
        });

        expCalendarView.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(View view, DateData date) {

                expCalendarView.getMarkedDates().removeAdd();
                expCalendarView.markDate(date);
                selectedDate = date;
            }
        });

        Calendar calendar = Calendar.getInstance();
        selectedDate = new DateData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));
        expCalendarView.markDate(selectedDate);

        CellConfig.Month2WeekPos = CellConfig.middlePosition;
        CellConfig.ifMonth = false;
        expCalendarView.shrink();

    }

    @Override
    protected void initTopBar() {
        initTitle("月份");
        initBack();
        initRightIcon(getResources().getDrawable(R.drawable.kalendar));
        initRightIconWithClick(getResources().getDrawable(R.drawable.kalendar), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                todo 展开日历
                if (ifExpand) {
                    CellConfig.Month2WeekPos = CellConfig.middlePosition;
                    CellConfig.ifMonth = false;
                    expCalendarView.shrink();
                } else {
                    CellConfig.Week2MonthPos = CellConfig.middlePosition;
                    CellConfig.ifMonth = true;
                    expCalendarView.expand();
                }
                ifExpand = !ifExpand;
            }
        });
    }





    @Override
    protected Context getContext() {
        return this;
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

}
