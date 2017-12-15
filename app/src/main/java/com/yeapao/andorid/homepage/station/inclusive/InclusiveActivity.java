package com.yeapao.andorid.homepage.station.inclusive;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.PrivateUseDetailModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/12/12.
 */

public class InclusiveActivity extends BaseActivity {
    private static final String TAG = "InclusiveActivity";
    @BindView(R.id.iv_inclusive_image)
    ImageView ivInclusiveImage;
    @BindView(R.id.iv_running)
    ImageView ivRunning;
    @BindView(R.id.iv_kick)
    ImageView ivKick;
    @BindView(R.id.iv_hiit)
    ImageView ivHiit;
    @BindView(R.id.iv_running_status)
    ImageView ivRunningStatus;
    @BindView(R.id.iv_kick_status2)
    ImageView ivKickStatus2;
    @BindView(R.id.iv_kick_status3)
    ImageView ivKickStatus3;
    @BindView(R.id.tv_inclusive_people)
    TextView tvInclusivePeople;
    @BindView(R.id.tv_inclusive_name)
    TextView tvInclusiveName;
    @BindView(R.id.iv_need_coach)
    ImageView ivNeedCoach;
    @BindView(R.id.iv_choose_inclusive)
    ImageView ivChooseInclusive;
    @BindView(R.id.tv_inclusive_time_status)
    TextView tvInclusiveTimeStatus;
    @BindView(R.id.tv_inclusive_price)
    TextView tvInclusivePrice;
    @BindView(R.id.tv_user_protocol)
    TextView tvUserProtocol;
    @BindView(R.id.tv_inclusive_sure)
    TextView tvInclusiveSure;
    @BindView(R.id.iv_agreement_protocol)
    ImageView ivAgreementProtocol;

    private boolean isNeedCoach = false;
    private boolean isAgreeProtocol = false;
    private InclusiveTimePickerDialogFragment inclusiveTimePickerDialogFragment;

    private String chooseTime;
    private PrivateUseDetailModel inclusiveModel;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, InclusiveActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inclusive);
        ButterKnife.bind(this);
        initTopBar();
        getNetWork();
    }

    @Override
    protected void initTopBar() {
        initTitle("土豪包场");
        initBack();
        inclusiveTimePickerDialogFragment = new InclusiveTimePickerDialogFragment();
        inclusiveTimePickerDialogFragment.setPickerPainListener(new InclusiveDateChooseListener() {
            @Override
            public void inclusiveCancel() {
                inclusiveTimePickerDialogFragment.dismiss();
            }

            @Override
            public void success(String time1, String time2) {
                LogUtil.e(TAG, time1);
                inclusiveTimePickerDialogFragment.dismiss();
                chooseTime = time1.substring(0, time1.length() - 2) + " " + time2;
                LogUtil.e(TAG, chooseTime);
                tvInclusiveTimeStatus.setText(chooseTime);
            }
        });

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick(R.id.iv_need_coach)
    public void onIvNeedCoachClicked() {
        if (isNeedCoach) {
            isNeedCoach = false;
            ivNeedCoach.setImageResource(R.drawable.coach_no);
        } else {
            isNeedCoach = true;
            ivNeedCoach.setImageResource(R.drawable.coach_yes);
        }
    }

    @OnClick(R.id.iv_choose_inclusive)
    public void onIvChooseInclusiveClicked() {
        if (inclusiveTimePickerDialogFragment.isVisible()) {
            inclusiveTimePickerDialogFragment.dismiss();
        } else {
            inclusiveTimePickerDialogFragment.show(getSupportFragmentManager(), "time");
        }

    }

    @OnClick(R.id.tv_inclusive_time_status)
    void setTvInclusiveTimeStatusClick() {
        if (inclusiveTimePickerDialogFragment.isVisible()) {
            inclusiveTimePickerDialogFragment.dismiss();
        } else {
            inclusiveTimePickerDialogFragment.show(getSupportFragmentManager(), "time");
        }
    }


    @OnClick(R.id.tv_inclusive_sure)
    public void onTvInclusiveSureClicked() {
        InclusiveOrderActivity.start(getContext());
    }

    @OnClick(R.id.iv_agreement_protocol)
    void onIvAgreementProtocol() {
        if (isAgreeProtocol) {
            isAgreeProtocol = false;
            ivAgreementProtocol.setImageResource(R.drawable.agreement_no_selected);
        } else {
            isAgreeProtocol = true;
            ivAgreementProtocol.setImageResource(R.drawable.agreement_selected);
        }
    }

    private void showResult() {

        GlideUtil glideUtil = new GlideUtil();
        glideUtil.glideLoadingImage(getContext(),inclusiveModel.getData().getUrl(),R.drawable.local_tyrant_bg,ivInclusiveImage);
        if (isNeedCoach) {

        }

    }


    private void getNetWork() {
        subscription = Network.getYeapaoApi()
                .requestInclusive()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<PrivateUseDetailModel> modelObserver = new Observer<PrivateUseDetailModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(PrivateUseDetailModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                inclusiveModel = model;

            }
        }
    };

}
