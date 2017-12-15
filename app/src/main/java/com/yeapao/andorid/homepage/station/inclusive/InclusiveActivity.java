package com.yeapao.andorid.homepage.station.inclusive;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.SystemDateUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.CreateInclusiveModel;
import com.yeapao.andorid.model.PrivateUseDetailModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/12/12.
 */

public class InclusiveActivity extends BaseActivity implements View.OnClickListener {
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

    private boolean status = false;
    private boolean status1 = false;
    private boolean status2 = false;
    private String inclusiveAddress = "";
    private int inclusiveDate = 0;
    private String inclusiveTime = "";

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
        ivRunningStatus.setOnClickListener(this);
        ivKickStatus2.setOnClickListener(this);
        ivKickStatus3.setOnClickListener(this);
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
            public void success(String time1, String time2, int day1) {
                LogUtil.e(TAG, time1);
                inclusiveTimePickerDialogFragment.dismiss();
                chooseTime = time1.substring(0, time1.length() - 2) + " " + time2;
                LogUtil.e(TAG, chooseTime);
                tvInclusiveTimeStatus.setText(chooseTime);
                inclusiveTime = time2;

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
            tvInclusivePrice.setText(getResources().getString(R.string.RMB) + String.valueOf(inclusiveModel.getData().getFliedPrice()));
        } else {
            isNeedCoach = true;
            ivNeedCoach.setImageResource(R.drawable.coach_yes);
            String price = String.valueOf(inclusiveModel.getData().getFliedPrice() + inclusiveModel.getData().getEmployeePrice());
            tvInclusivePrice.setText(getResources().getString(R.string.RMB) + price);

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
        if (!isAgreeProtocol) {
            ToastManager.showToast(getContext(), "请阅读并勾选用户协议");
            return;
        } else {
            String selectEmployee = "0";
            if (isNeedCoach) {
                selectEmployee = "1";
            }

            if (inclusiveAddress.equals("")) {
                ToastManager.showToast(getContext(),"请选择场地");
                return;
            }

            if (inclusiveTime.equals("") || inclusiveTime == null) {
                ToastManager.showToast(getContext(),"请选择预约时间");
                return;
            }


            getNetWorkCreateInclusive(inclusiveAddress,String.valueOf(tvInclusiveName.getText()),selectEmployee, SystemDateUtil.getFetureDateYYYYMMDD(inclusiveDate),
                    inclusiveTime);
        }

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
        try {
            if (isNeedCoach) {
                String price = String.valueOf(inclusiveModel.getData().getFliedPrice() + inclusiveModel.getData().getEmployeePrice());
                tvInclusivePrice.setText(getResources().getString(R.string.RMB) + price);
            } else {
                tvInclusivePrice.setText(getResources().getString(R.string.RMB) + String.valueOf(inclusiveModel.getData().getFliedPrice()));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
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
                showResult();
            }
        }
    };



            private void getNetWorkCreateInclusive(String fieldType,String fieldAddress,String selectEmployee,String date,String time) {
                    subscription = Network.getYeapaoApi()
                            .requestInclusiveModel(GlobalDataYepao.getUser(getContext()).getId(),fieldType,
                                    fieldAddress,selectEmployee,date,time)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(modelObserverCreateInclusive);
                }

                  Observer<CreateInclusiveModel> modelObserverCreateInclusive = new Observer<CreateInclusiveModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(CreateInclusiveModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        ToastManager.showToast(getContext(),model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            InclusiveOrderActivity.start(getContext(),model);
                        }
                    }
                };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_running_status:
                if (status) {
                    status = false;
                    inclusiveAddress = "";
                    ivRunningStatus.setImageResource(R.drawable.sport_choose_n);
                    ivKickStatus2.setImageResource(R.drawable.sport_choose_n);
                    ivKickStatus3.setImageResource(R.drawable.sport_choose_n);
                } else {
                    status = true;
                    status1 = false;
                    status2 = false;
                    inclusiveAddress = "1";
                    ivRunningStatus.setImageResource(R.drawable.sport_choose_s);
                    ivKickStatus2.setImageResource(R.drawable.sport_choose_n);
                    ivKickStatus3.setImageResource(R.drawable.sport_choose_n);
                }
                break;
            case R.id.iv_kick_status2:
                if (status1) {
                    status1 = false;
                    inclusiveAddress = "";
                    ivRunningStatus.setImageResource(R.drawable.sport_choose_n);
                    ivKickStatus2.setImageResource(R.drawable.sport_choose_n);
                    ivKickStatus3.setImageResource(R.drawable.sport_choose_n);
                } else {
                    status1 = true;
                    status = false;
                    status2 = false;
                    inclusiveAddress = "2";
                    ivRunningStatus.setImageResource(R.drawable.sport_choose_n);
                    ivKickStatus2.setImageResource(R.drawable.sport_choose_s);
                    ivKickStatus3.setImageResource(R.drawable.sport_choose_n);
                }
                break;
            case R.id.iv_kick_status3:
                if (status2) {
                    status2 = false;
                    inclusiveAddress = "";
                    ivRunningStatus.setImageResource(R.drawable.sport_choose_n);
                    ivKickStatus2.setImageResource(R.drawable.sport_choose_n);
                    ivKickStatus3.setImageResource(R.drawable.sport_choose_n);
                } else {
                    status2 = true;
                    status1 = false;
                    status = false;
                    inclusiveAddress = "3";
                    ivRunningStatus.setImageResource(R.drawable.sport_choose_n);
                    ivKickStatus2.setImageResource(R.drawable.sport_choose_n);
                    ivKickStatus3.setImageResource(R.drawable.sport_choose_s);
                }
                break;
            default:
                break;
        }
    }
}
