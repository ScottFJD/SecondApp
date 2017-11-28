package com.yeapao.andorid.homepage.station.traininglesson;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/11/27.
 */

public class TrainingLessonDetailActivity extends BaseActivity {
    private static final String TAG = "TrainingLessonDetailActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initTopBar() {
        initTitle("教练服务");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
