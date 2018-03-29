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

import com.android.volley.VolleyError;
import com.scottfu.sflibrary.net.CloudClient;
import com.scottfu.sflibrary.net.JSONResultHandler;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.api.NetImpl;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.FitPlanDetailModel;
import com.yeapao.andorid.model.ReservationLessonModel;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    @BindView(R.id.tv_lesson_start)
    TextView tvLessonStart;


    private FitLessonMessageAdapter firstStepMessageAdapter;
    private FitLessonMessageAdapter secondStepMessageAdapter;
    private FitLessonMessageAdapter thirdStepMessageAdapter;
    private FitTitlePartsMessageAdapter fitTitlePartsMessageAdapter;

    private boolean fitStep1 = false;
    private boolean fitStep2 = false;
    private boolean fitStep3 = false;

    private ArrayList<String> fitTitles = new ArrayList<>();
    private String customizeLevel, customizeParts;
    private FitPlanDetailModel detailModel;

    public static void start(Context context,String customizeLevel,String customizeParts ,ArrayList<String> fitTitles) {
        Intent intent = new Intent();
        intent.setClass(context, MyFitLessonActivity.class);
        intent.putExtra("level", customizeLevel);
        intent.putExtra("parts", customizeParts);
        intent.putStringArrayListExtra("titles", fitTitles);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fit_lesson);
        ButterKnife.bind(this);
        fitTitles = getIntent().getStringArrayListExtra("titles");
        this.customizeLevel = getIntent().getStringExtra("level");
        this.customizeParts = getIntent().getStringExtra("parts");
        getNetWork(customizeLevel, customizeParts);
        initTopBar();
        initView();


    }

    private void initView() {
        tvFitLevel.setText(customizeLevel);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvFitTitles.setLayoutManager(linearLayoutManager);
        fitTitlePartsMessageAdapter = new FitTitlePartsMessageAdapter(getContext(), fitTitles);
        rvFitTitles.setAdapter(fitTitlePartsMessageAdapter);
    }


    private void showResult() {
        final int warmnlistSize = detailModel.getResultMap().getWarmUpMediaList().size();
        final int officialListSize = detailModel.getResultMap().getOfficialMediaList().size();
        int stretchListSize = detailModel.getResultMap().getStretchMediaList().size();
        firstStepMessageAdapter = new FitLessonMessageAdapter(getContext(),detailModel.getResultMap().getWarmUpMediaList());
        firstStepMessageAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(View v, int position) {

                FitLessonPlayActivity.start(getContext(),position,detailModel,"1");
            }
        });
        rvFitStep1.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFitStep1.setAdapter(firstStepMessageAdapter);
        secondStepMessageAdapter = new FitLessonMessageAdapter(getContext(),detailModel.getResultMap().getOfficialMediaList());
        secondStepMessageAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                FitLessonPlayActivity.start(getContext(), warmnlistSize - 1 + position, detailModel, "1");
            }
        });
        rvFitStep2.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFitStep2.setAdapter(secondStepMessageAdapter);
        thirdStepMessageAdapter = new FitLessonMessageAdapter(getContext(),detailModel.getResultMap().getStretchMediaList());
        thirdStepMessageAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                FitLessonPlayActivity.start(getContext(),warmnlistSize+officialListSize-1+position,detailModel,"1");
            }
        });
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



    @OnClick(R.id.tv_lesson_start)
    public void lessonStart(View view) {
//        FitLessonPlayActivity.start(getContext());
        if (detailModel.getResultMap() != null) {
            FitLessonPlayActivity.start(getContext(),detailModel,"2");
        }


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

    private void getNetWork(String customizeLevel,String customizeParts) {
        LogUtil.e(TAG,customizeLevel+customizeParts);
        subscription = Network.getYeapaoFitPlanApi()
                .getFitPlan(customizeLevel,customizeParts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver );
    }

    Observer<FitPlanDetailModel> modelObserver = new Observer<FitPlanDetailModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG,e.toString());

        }

        @Override
        public void onNext(FitPlanDetailModel model) {
            LogUtil.e(TAG,"+++++++++"+String.valueOf(model.getResultMap().getWarmUpMediaList().get(0).getImg()));
            try {
                if (model.getResultMap() != null) {
                    detailModel = model;
                    showResult();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        }
    };




}
