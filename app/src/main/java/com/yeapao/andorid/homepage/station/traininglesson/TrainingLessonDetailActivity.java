package com.yeapao.andorid.homepage.station.traininglesson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ScreenUtil;
import com.yeapao.andorid.LoginActivity;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.EmployeeDetailModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/11/27.
 */

public class TrainingLessonDetailActivity extends BaseActivity {
    private static final String TAG = "TrainingLessonDetailActivity";
    @BindView(R.id.civ_coach_head)
    CircleImageView civCoachHead;
    @BindView(R.id.tv_coach_title)
    TextView tvCoachTitle;
    @BindView(R.id.tv_teach_name)
    TextView tvTeachName;
    @BindView(R.id.tv_coach_title_1)
    TextView tvCoachTitle1;
    @BindView(R.id.tv_coach_title_2)
    TextView tvCoachTitle2;
    @BindView(R.id.tv_coach_title_3)
    TextView tvCoachTitle3;
    @BindView(R.id.tv_coach_title_4)
    TextView tvCoachTitle4;
    @BindView(R.id.tv_coach_title_5)
    TextView tvCoachTitle5;
    @BindView(R.id.tv_training_lesson_address)
    TextView tvTrainingAddress;
    @BindView(R.id.iv_lesson_content)
    ImageView ivLessonContent;

    private TrainingLessonBuyFragment trainingLessonBuyFragment;

    private TextView gotoPay;

    private String type;

    private int mPosition = 111;

    private String employeeDetail;
    private GlideUtil glideUtil = new GlideUtil();
    private EmployeeDetailModel detailModel = new EmployeeDetailModel();

    public static void start(Context context, String employeeDetail, String type) {
        Intent intent = new Intent();
        intent.putExtra("type", type);
        intent.putExtra("employeeDetail", employeeDetail);
        intent.setClass(context, TrainingLessonDetailActivity.class);
        context.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_lesson_detail);
        ButterKnife.bind(this);
        employeeDetail = getIntent().getStringExtra("employeeDetail");
        type = getIntent().getStringExtra("type");
        gotoPay = (TextView) findViewById(R.id.tv_buy_training_lesson);
        gotoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPayWay();
            }
        });

        initTopBar();

        if (type.equals(TrainingLessonActivity.HIGH)) {
            getNetWork(employeeDetail);
        } else {
            getNetWorkRecovery(employeeDetail);
        }


    }

    @Override
    protected void initTopBar() {
        initTitle("教练服务");
        initBack();
    }


    private void showResult() {
        glideUtil.glideLoadingImage(getContext(), detailModel.getData().getEmployeeDetailOut().getHeadImage(),
                R.drawable.y_you, civCoachHead);
        tvTeachName.setText(detailModel.getData().getEmployeeDetailOut().getCoachName() + "/");
        tvCoachTitle.setText(detailModel.getData().getEmployeeDetailOut().getJobTitle());
        for (int i = 0; i < detailModel.getData().getEmployeeDetailOut().getInformation().size(); i++) {
            switch (i) {
                case 0:
                    tvCoachTitle1.setText(detailModel.getData().getEmployeeDetailOut().getInformation().get(i));
                    break;
                case 1:
                    tvCoachTitle2.setText(detailModel.getData().getEmployeeDetailOut().getInformation().get(i));
                    break;
                case 2:
                    tvCoachTitle3.setText(detailModel.getData().getEmployeeDetailOut().getInformation().get(i));
                    break;
                case 3:
                    tvCoachTitle4.setText(detailModel.getData().getEmployeeDetailOut().getInformation().get(i));
                    break;
                case 4:
                    tvCoachTitle5.setText(detailModel.getData().getEmployeeDetailOut().getInformation().get(i));
                    break;
                default:
                    break;
            }
        }

        glideUtil.glideLoadingImage(getContext(), detailModel.getData().getEmployeeDetailOut().getUrl(), R.color.bg_white, ivLessonContent);
        trainingLessonBuyFragment = TrainingLessonBuyFragment.newInstance(detailModel,type);
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
                LogUtil.e(TAG, "gotoPay");
                if (GlobalDataYepao.isLogin()) {
                    TrainingLessonOrderActivity.start(getContext(), String.valueOf(detailModel.getData().getEmployeeDetailOut().getEmployeeId()),
                            String.valueOf(detailModel.getData().getEmployeeFeeOut().get(mPosition).getSubjectFeeId()), type);
                } else {
                    LoginActivity.start(getContext());
                }


            }

            @Override
            public void chooseLesson(int position) {
                mPosition = position;
            }
        });
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


    private void getNetWork(String id) {
        LogUtil.e(TAG, id);
        subscription = Network.getYeapaoApi()
                .requsetEmployeeDetail(id, GlobalDataYepao.getUser(getContext()).getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }


    private void getNetWorkRecovery(String id) {
        LogUtil.e(TAG, id);
        subscription = Network.getYeapaoApi()
                .requestRecoverDetail(id, GlobalDataYepao.getUser(getContext()).getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }


    Observer<EmployeeDetailModel> modelObserver = new Observer<EmployeeDetailModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(EmployeeDetailModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                detailModel = model;
                showResult();
            }
        }
    };

}
