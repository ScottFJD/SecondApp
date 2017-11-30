package com.yeapao.andorid.homepage.station.dynamiclesson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.DynamicLessonOrderModel;
import com.yeapao.andorid.util.GlobalDataYepao;
import com.yeapao.andorid.util.MyPayWayDialogFragment;
import com.yeapao.andorid.util.PayWayOnClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/11/24.
 */

public class DynamicLessonReservationActivity extends BaseActivity {
    private static final String TAG = "DynamicLessonReservationActivity";
    @BindView(R.id.tv_order_title)
    TextView tvOrderTitle;
    @BindView(R.id.ll_top_bar)
    LinearLayout llTopBar;
    @BindView(R.id.tv_immediately_pay)
    TextView tvImmediatelyPay;
    @BindView(R.id.tv_lesson_name)
    TextView tvLessonName;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.tv_dynamic_order_time)
    TextView tvDynamicOrderTime;
    @BindView(R.id.tv_dynamiv_lesson_time)
    TextView tvDynamivLessonTime;
    @BindView(R.id.tv_dynamoc_address)
    TextView tvDynamocAddress;
    @BindView(R.id.tv_dynamic_lesson_price)
    TextView tvDynamicLessonPrice;
    @BindView(R.id.iv_user_protocol_status)
    ImageView ivUserProtocolStatus;
    @BindView(R.id.tv_user_protocol)
    TextView tvUserProtocol;
    @BindView(R.id.iv_reduce_people)
    ImageView ivReducePeople;
    @BindView(R.id.tv_dynamic_reservation_people)
    TextView tvDynamicReservationPeople;
    @BindView(R.id.iv_add_people)
    ImageView ivAddPeople;


    private boolean userProtocolStatus = false;
    private MyPayWayDialogFragment myPayWayDialogFragment;

    private DynamicLessonOrderModel dynamicLessonOrderModel;
    private String lessonId = "";
    private int peopleSum = 0;

    public static void start(Context context, String calendarId) {
        Intent intent = new Intent();
        intent.putExtra("calendarId", calendarId);
        intent.setClass(context, DynamicLessonReservationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_lesson_reservation);
        ButterKnife.bind(this);
        initTopBar();
        lessonId = getIntent().getStringExtra("calendarId");
        getNetWork(lessonId);
        initView();
    }

    private void initView() {
        myPayWayDialogFragment = MyPayWayDialogFragment.newInstance("100");
        myPayWayDialogFragment.setPayWayOnClickListener(new PayWayOnClickListener() {
            @Override
            public void onPayWay(int status) {
                LogUtil.e(TAG, "payway wechat=1  " + String.valueOf(status));
            }

            @Override
            public void onCloseWindow() {
                myPayWayDialogFragment.dismiss();
            }

            @Override
            public void gotoPay() {
                LogUtil.e(TAG, "gotoPay");
                myPayWayDialogFragment.dismiss();
            }
        });
    }

    @Override
    protected void initTopBar() {
        initTitle("课程预约");
        initBack();
    }

    @OnClick(R.id.tv_immediately_pay)
    void setTvImmediatelyPayOnClick(View view) {
        LogUtil.e(TAG, "onclick");
        if (userProtocolStatus) {
            showPayWay();
        } else {
            ToastManager.showToast(getContext(), "请阅读用户协议");
        }

    }

    @OnClick(R.id.iv_user_protocol_status)
    void setIvUserProtocolStatus(View view) {
        if (userProtocolStatus) {
            userProtocolStatus = false;
            ivUserProtocolStatus.setImageResource(R.drawable.agreement_no_selected);
        } else {
            userProtocolStatus = true;
            ivUserProtocolStatus.setImageResource(R.drawable.agreement_selected);
        }
    }

    private void showPayWay() {
        if (myPayWayDialogFragment.isVisible()) {
            myPayWayDialogFragment.dismiss();
        } else {
            myPayWayDialogFragment.show(getSupportFragmentManager(), "date");
        }
    }

    private void showView() {
        tvOrderNumber.setText(dynamicLessonOrderModel.getData().getCalendarOrderCode());
        tvDynamicOrderTime.setText(dynamicLessonOrderModel.getData().getStartDate());
        tvDynamivLessonTime.setText(dynamicLessonOrderModel.getData().getDate());
        tvDynamocAddress.setText(dynamicLessonOrderModel.getData().getShopAddress());
        ivReducePeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (peopleSum > 0) {
                    peopleSum--;
                    tvDynamicReservationPeople.setText(String.valueOf(peopleSum));
                } else {
                    peopleSum = 0;
                }
            }
        });
        ivAddPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (peopleSum < dynamicLessonOrderModel.getData().getRecoveryMax()) {
                    peopleSum++;
                    tvDynamicReservationPeople.setText(String.valueOf(peopleSum));
                } else {
                    tvDynamicReservationPeople.setText(dynamicLessonOrderModel.getData().getRecoveryMax());
                    ToastManager.showToast(getContext(),"已达到健身人数上限");
                }
            }
        });
    }

    @OnClick(R.id.iv_dynamic_card)
    void setDiscountCard(View view) {

    }

    @Override
    protected Context getContext() {
        return this;
    }


    private void getNetWork(String lessonId) {
        LogUtil.e(TAG, lessonId);
        subscription = Network.getYeapaoApi()
                .requestDynamicLessonOrder(GlobalDataYepao.getUser(getContext()).getId(), lessonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<DynamicLessonOrderModel> modelObserver = new Observer<DynamicLessonOrderModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(DynamicLessonOrderModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                dynamicLessonOrderModel = model;
                showView();
            }
        }
    };
}
