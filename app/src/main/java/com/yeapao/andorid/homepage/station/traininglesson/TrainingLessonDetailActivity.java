package com.yeapao.andorid.homepage.station.traininglesson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/11/27.
 */

public class TrainingLessonDetailActivity extends BaseActivity {
    private static final String TAG = "TrainingLessonDetailActivity";

    private TrainingLessonBuyFragment trainingLessonBuyFragment;

    private TextView gotoPay;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TrainingLessonDetailActivity.class);
        context.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_lesson_detail);
        trainingLessonBuyFragment = new TrainingLessonBuyFragment();
        trainingLessonBuyFragment.setTrainingLessonListener(new TrainingLessonBuyFragment.TrainingLessonBuyListener() {
            @Override
            public void cancelClick() {
                trainingLessonBuyFragment.dismiss();
            }

            @Override
            public void userProtocol() {

            }

            @Override
            public void gotoPay() {
                LogUtil.e(TAG,"gotoPay");
                TrainingLessonOrderActivity.start(getContext());

            }
        });
        gotoPay = (TextView) findViewById(R.id.tv_buy_training_lesson);
        gotoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPayWay();
            }
        });

    }

    @Override
    protected void initTopBar() {
        initTitle("教练服务");
        initBack();
    }


    private void showPayWay() {
        if (trainingLessonBuyFragment.isVisible()) {
            trainingLessonBuyFragment.dismiss();
        } else {
            trainingLessonBuyFragment.show(getSupportFragmentManager(), "trainingLessin");
        }
    }


    @Override
    protected Context getContext() {
        return this;
    }
}
