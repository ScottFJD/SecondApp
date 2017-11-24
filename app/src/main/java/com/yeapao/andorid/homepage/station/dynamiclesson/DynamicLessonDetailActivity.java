package com.yeapao.andorid.homepage.station.dynamiclesson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scottfu.sflibrary.customview.CircleImageView;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/11/22.
 */

public class DynamicLessonDetailActivity extends BaseActivity {
    private static final String TAG = "DynamicLessonDetailActivity";
    @BindView(R.id.tv_order_title)
    TextView tvOrderTitle;
    @BindView(R.id.iv_dynamic_lesson)
    ImageView ivDynamicLesson;
    @BindView(R.id.tv_dynamic_lesson_time)
    TextView tvDynamicLessonTime;
    @BindView(R.id.tv_dynamic_lesson_address)
    TextView tvDynamicLessonAddress;
    @BindView(R.id.imageView108)
    ImageView imageView108;
    @BindView(R.id.tv_dynamic_coach_name)
    TextView tvDynamicCoachName;
    @BindView(R.id.tv_dynamic_coach_job)
    TextView tvDynamicCoachJob;
    @BindView(R.id.ll_coach)
    LinearLayout llCoach;
    @BindView(R.id.crl_dynamic_coach_header)
    CircleImageView crlDynamicCoachHeader;
    @BindView(R.id.tv_dynamic_coach_title_1)
    TextView tvDynamicCoachTitle1;
    @BindView(R.id.tv_dynamic_coach_title_2)
    TextView tvDynamicCoachTitle2;
    @BindView(R.id.ll_coach_title)
    LinearLayout llCoachTitle;
    @BindView(R.id.iv_dinner)
    ImageView ivDinner;
    @BindView(R.id.iv_content_more)
    ImageView ivContentMore;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, DynamicLessonDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_lesson_detail);
        ButterKnife.bind(this);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("动感光影跑");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
