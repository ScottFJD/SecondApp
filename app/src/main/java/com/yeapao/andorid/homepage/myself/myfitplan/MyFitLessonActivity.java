package com.yeapao.andorid.homepage.myself.myfitplan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.iv_fit_step_1)
    ImageView ivFitStep1;
    @BindView(R.id.iv_fit_step_2)
    ImageView ivFitStep2;
    @BindView(R.id.iv_fit_step_3)
    ImageView ivFitStep3;


    private FitLessonMessageAdapter firstStepMessageAdapter;
    private FitLessonMessageAdapter secondStepMessageAdapter;
    private FitLessonMessageAdapter thirdStepMessageAdapter;
    private FitTitlePartsMessageAdapter fitTitlePartsMessageAdapter;

    private boolean fitStep1 = true;
    private boolean fitStep2 = true;
    private boolean fitStep3 = true;

    private ArrayList<String> fitTitles = new ArrayList<>();

    public static void start(Context context,ArrayList<String> fitTitles) {
        Intent intent = new Intent();
        intent.setClass(context, MyFitLessonActivity.class);
        intent.putStringArrayListExtra("titles", fitTitles);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fit_lesson);
        ButterKnife.bind(this);
        fitTitles = getIntent().getStringArrayListExtra("titles");
        initTopBar();
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvFitTitles.setLayoutManager(linearLayoutManager);
        fitTitlePartsMessageAdapter = new FitTitlePartsMessageAdapter(getContext(), fitTitles);
        rvFitTitles.setAdapter(fitTitlePartsMessageAdapter);
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

    @OnClick({R.id.rl_fit_step_1, R.id.rl_fit_step_2, R.id.rl_fit_step_3})
    public void onClickImageView(View view) {
        switch (view.getId()) {
            case R.id.rl_fit_step_1:
                if (fitStep1) {
                    rvFitStep1.setVisibility(View.VISIBLE);
                    ivFitStep1.setImageResource(R.drawable.down);
                    fitStep1 = false;
                } else {
                    rvFitStep1.setVisibility(View.GONE);
                    ivFitStep1.setImageResource(R.drawable.right);
                    fitStep1 = true;
                }
                break;
            case R.id.rl_fit_step_2:
                if (fitStep2) {
                    rvFitStep2.setVisibility(View.VISIBLE);
                    ivFitStep2.setImageResource(R.drawable.down);
                    fitStep2 = false;
                } else {
                    rvFitStep2.setVisibility(View.GONE);
                    ivFitStep2.setImageResource(R.drawable.right);
                    fitStep2 = true;
                }
                break;
            case R.id.rl_fit_step_3:
                if (fitStep3) {
                    rvFitStep3.setVisibility(View.VISIBLE);
                    ivFitStep3.setImageResource(R.drawable.down);
                    fitStep3 = false;
                } else {
                    rvFitStep3.setVisibility(View.GONE);
                    ivFitStep3.setImageResource(R.drawable.right);
                    fitStep3 = true;
                }
                break;
                default:
                    break;
        }
    }

}
