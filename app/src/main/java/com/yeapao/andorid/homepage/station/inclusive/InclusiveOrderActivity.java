package com.yeapao.andorid.homepage.station.inclusive;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/12/13.
 */

public class InclusiveOrderActivity extends BaseActivity{
    private static final String TAG = "InclusiveOrderActivity";


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, InclusiveOrderActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inclusive_order);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("土豪包场");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
