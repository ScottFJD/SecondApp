package com.yeapao.andorid.homepage.myself.myfitplan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 02/02/2018.
 */

public class MyFitLessonActivity extends BaseActivity {
    private static final String TAG = "MyFitLessonActivity";
    @BindView(R.id.rv_fit_titles)
    RecyclerView rvFitTitles;
    @BindView(R.id.tv_fit_level)
    TextView tvFitLevel;
    @BindView(R.id.tv_fit_rest_time)
    TextView tvFitRestTime;
    @BindView(R.id.rl_fit_step_1)
    RelativeLayout rlFitStep1;
    @BindView(R.id.rv_fit_step_1)
    RecyclerView rvFitStep1;
    @BindView(R.id.rl_fit_step_2)
    RelativeLayout rlFitStep2;
    @BindView(R.id.rv_fit_step_2)
    RecyclerView rvFitStep2;
    @BindView(R.id.rl_fit_step_3)
    RelativeLayout rlFitStep3;
    @BindView(R.id.rv_fit_step_3)
    RecyclerView rvFitStep3;

    private FitLessonMessageAdapter firstStepMessageAdapter;
    private FitLessonMessageAdapter secondStepMessageAdapter;
    private FitLessonMessageAdapter thirdStepMessageAdapter;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyFitLessonActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fit_lesson);
        ButterKnife.bind(this);
        initBack();
        initView();


    }

    private void initView() {
        firstStepMessageAdapter = new FitLessonMessageAdapter(getContext());
        rvFitStep1.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFitStep1.setAdapter(firstStepMessageAdapter);
        secondStepMessageAdapter = new FitLessonMessageAdapter(getContext());
        rvFitStep2.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFitStep2.setAdapter(secondStepMessageAdapter);
        thirdStepMessageAdapter = new FitLessonMessageAdapter(getContext());
        rvFitStep3.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFitStep3.setAdapter(thirdStepMessageAdapter);

    }

    @Override
    protected void initTopBar() {
        initTitle("课程训练");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
