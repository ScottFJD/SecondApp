package com.yeapao.andorid.homepage.station.traininglesson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/12/8.
 */

public class TrainingLessonOrderActivity extends BaseActivity {

    private static final String TAG = "TrainingLessonOrderActivity";

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TrainingLessonOrderActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_order);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("课程订单");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
