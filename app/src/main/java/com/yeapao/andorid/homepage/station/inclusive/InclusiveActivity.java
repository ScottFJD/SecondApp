package com.yeapao.andorid.homepage.station.inclusive;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    }

    @Override
    protected void initTopBar() {
        initTitle("土豪包场");
        initBack();
        inclusiveTimePickerDialogFragment = new InclusiveTimePickerDialogFragment();

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

}
