package com.yeapao.andorid.homepage.myself;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.homepage.sport_plan.SportPlanFragment;

/**
 * Created by fujindong on 2017/12/19.
 */

public class MyselfSportPlanActivity extends BaseActivity {
    private static final String TAG = "MyselfSportPlanActivity";
    private SportPlanFragment sportPlanFragment;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfSportPlanActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_sport_plan);
        sportPlanFragment = SportPlanFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_sport_plan, sportPlanFragment);
        fragmentTransaction.commit();

    }

    @Override
    protected void initTopBar() {

    }

    @Override
    protected Context getContext() {
        return this;
    }
}
