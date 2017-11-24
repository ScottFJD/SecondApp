package com.yeapao.andorid.homepage.station.dynamiclesson;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/11/24.
 */

public class DynamicLessonReservationActivity extends BaseActivity {
    private static final String TAG = "DynamicLessonReservationActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("课程预约");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
