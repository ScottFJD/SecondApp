package com.yeapao.andorid.homepage.myself.myfitplan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 02/02/2018.
 */

public class MyFitPlanActivity extends BaseActivity {
    private static final String TAG = "MyFitPlanActivity";



    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyFitPlanActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fit_plan);
        initBack();
    }

    @Override
    protected void initTopBar() {
        initTitle("课程定制");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
