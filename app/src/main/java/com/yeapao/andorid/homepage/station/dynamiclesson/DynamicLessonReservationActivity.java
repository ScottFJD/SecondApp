package com.yeapao.andorid.homepage.station.dynamiclesson;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.scottfu.sflibrary.alipay.AuthResult;
import com.scottfu.sflibrary.alipay.PayResult;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yeapao.andorid.R;
import com.yeapao.andorid.YeaPaoWebActivity;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.homepage.map.repository.ReservationCangPayActivity;
import com.yeapao.andorid.model.CallPaymentModel;
import com.yeapao.andorid.model.DynamicLessonOrderModel;
import com.yeapao.andorid.util.GlobalDataYepao;
import com.yeapao.andorid.util.MyPayWayDialogFragment;
import com.yeapao.andorid.util.PayWayOnClickListener;

import java.util.Map;

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
    @BindView(R.id.iv_dynamic_card)
    ImageView ivDynamicCard;
    @BindView(R.id.tv_dynamic_order_price)
    TextView tvDynamicOrderPrice;
    @BindView(R.id.tv_discount_status)
    TextView tvDiscountStatus;


    private boolean userProtocolStatus = false;
    private MyPayWayDialogFragment myPayWayDialogFragment;

    private DynamicLessonOrderModel dynamicLessonOrderModel;
    private String lessonId = "";
    private String lessonName = "";
    private int peopleSum = 1;
    private String payMentType = "";





    //    支付
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    private MessageSendReceiver numberReceiver;
    IWXAPI api;

    public static void start(Context context, String calendarId, String lessonName) {

        Intent intent = new Intent();
        intent.putExtra("calendarId", calendarId);
        intent.putExtra("lessonName", lessonName);
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
        lessonName = getIntent().getStringExtra("lessonName");
        getNetWork(lessonId);
//        initView();

        if (numberReceiver == null) {
            numberReceiver = new MessageSendReceiver();
        }
        getContext().registerReceiver(numberReceiver, new IntentFilter("wxPay.action"));
        api = WXAPIFactory.createWXAPI(getContext(), ConstantYeaPao.APP_ID, true);
        api.registerApp(ConstantYeaPao.APP_ID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getNetWork(lessonId);
    }

    private void initView() {
        myPayWayDialogFragment = MyPayWayDialogFragment.newInstance(realpayPrice());
        myPayWayDialogFragment.setPayWayOnClickListener(new PayWayOnClickListener() {
            @Override
            public void onPayWay(int status) {
                LogUtil.e(TAG, "payway wechat=1  " + String.valueOf(status));
                if (status == 1) {
                    payMentType = "2";
                } else {
                    payMentType = "1";
                }
            }

            @Override
            public void onCloseWindow() {
                myPayWayDialogFragment.dismiss();
            }

            @Override
            public void gotoPay() {
                LogUtil.e(TAG, "gotoPay");
                myPayWayDialogFragment.dismiss();
                getPayment(realpayPrice(),dynamicLessonOrderModel.getData().getCalendarOrderCode(),payMentType);
            }
        });
        showPayWay();
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
            initView();
//            showPayWay();
        } else {
            ToastManager.showToast(getContext(), "请阅读并勾选用户协议");
        }

    }

    @OnClick(R.id.iv_dynamic_card)
    void setIvDynamicCard(View view) {
        DynamicPeopleEquityActivity.start(getContext());
    }

    @OnClick(R.id.tv_user_protocol)
    void setTvUserProtocol(View view) {
        YeaPaoWebActivity.start(getContext(), "用户协议", "http://47.92.113.97:8008/agreement/agreement.html");
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
        tvLessonName.setText(lessonName);
        tvOrderNumber.setText(dynamicLessonOrderModel.getData().getCalendarOrderCode());
        tvDynamicOrderTime.setText(dynamicLessonOrderModel.getData().getStartDate());
        tvDynamivLessonTime.setText(dynamicLessonOrderModel.getData().getDate());
        tvDynamocAddress.setText(dynamicLessonOrderModel.getData().getShopAddress());

        if (dynamicLessonOrderModel.getData().getDiscountName() == null || ("").equals(dynamicLessonOrderModel.getData().getDiscountName())) {
            tvDiscountStatus.setText("尚未开通");
        } else {
            tvDiscountStatus.setText(dynamicLessonOrderModel.getData().getDiscountName());
        }
        tvDynamicOrderPrice.setText(getResources().getString(R.string.RMB) + String.valueOf(dynamicLessonOrderModel.getData().getPrice()));
        tvDynamicLessonPrice.setText(getResources().getString(R.string.RMB)+realpayPrice());
        ivReducePeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (peopleSum > 1) {
                    peopleSum--;
                    tvDynamicReservationPeople.setText(String.valueOf(peopleSum));
                } else {
                    peopleSum = 1;
                }
                tvDynamicOrderPrice.setText(getResources().getString(R.string.RMB) + payablePrice());
                tvDynamicLessonPrice.setText(getResources().getString(R.string.RMB)+realpayPrice());
            }
        });
        ivAddPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (peopleSum < dynamicLessonOrderModel.getData().getRecoveryMax()) {
                    peopleSum++;
                    tvDynamicReservationPeople.setText(String.valueOf(peopleSum));
                } else {
                    tvDynamicReservationPeople.setText(String.valueOf(peopleSum));
                    ToastManager.showToast(getContext(),"已达到健身人数上限");
                }
                tvDynamicOrderPrice.setText(getResources().getString(R.string.RMB) + payablePrice());
                tvDynamicLessonPrice.setText(getResources().getString(R.string.RMB)+realpayPrice());
            }
        });
    }


    private String payablePrice() {
        String payablePrice = "";
        int price = dynamicLessonOrderModel.getData().getPrice();
        int sumPrice = price * peopleSum;
        payablePrice = String.valueOf(sumPrice);
        return payablePrice;
    }

    private String realpayPrice() {
        String realpayPrice = "";
        int price = dynamicLessonOrderModel.getData().getPrice();
        int sumPrice = price * peopleSum;
        float realpay;
        if (dynamicLessonOrderModel.getData().getIsOpen().equals("1")) {
            realpay = sumPrice * Float.valueOf(dynamicLessonOrderModel.getData().getDiscount());
            realpayPrice = String.valueOf(realpay);
        } else {
            realpayPrice = String.valueOf(sumPrice);
        }
        return realpayPrice;
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
            ToastManager.showToast(getContext(),"创建订单失败，请重试");
            finish();

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




    private void getPayment(String price, String orderCode, String paymentType) {

        LogUtil.e(TAG, price+"==="+orderCode+"+++"+paymentType);
        subscription = Network.getYeapaoApi()
                .requestDynamicPayment(price,orderCode,paymentType,String.valueOf(peopleSum))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserverPayment);
    }

    Observer<CallPaymentModel> modelObserverPayment = new Observer<CallPaymentModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG,e.toString());

        }

        @Override
        public void onNext(CallPaymentModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {

                if (payMentType.equals("1")) {


//                            final String orderInfo = model.getData().getAliPayInfo();   // 订单信息
//                            LogUtil.e("orderInfo",orderInfo);

//
//                            OrderCommitted result = new OrderCommitted();
//                            result.setOrderID(model.getData().getOrderCode());
////                            result.setOrderName("课程订单");
////                            String mmm = String.format("%.2f", content.getAmount());
//                            result.setFinalPrice(model.getData().getPrice());
////                            result.setNameList("111");
//
//                            Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(ConstantYeaPao.APPID, true,result);
//                            String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
//                            LogUtil.e("+++++++", orderParam);
//                            String privateKey = ConstantYeaPao.RSA2_PRIVATE;
//                            String sign = OrderInfoUtil2_0.getSign(params, privateKey, true);
//                            final String orderInfo = orderParam + "&" + sign;
                    final String orderInfo = model.getData().getAliPayInfo();

                    LogUtil.e("+++++++", orderInfo);

                    Runnable payRunnable = new Runnable() {

                        @Override
                        public void run() {
                            PayTask alipay = new PayTask(DynamicLessonReservationActivity.this);
                            Map<String, String> result = alipay.payV2(orderInfo,true);
                            Log.e("msp", result.toString());
                            Message msg = new Message();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;
                            mHandler.sendMessage(msg);
                        }
                    };
                    // 必须异步调用
                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
                }else{

                    PayReq request = new PayReq();
                    request.appId = ConstantYeaPao.APP_ID;
                    request.partnerId = "1486707182";
                    request.prepayId = model.getData().getPrepayid();
                    request.nonceStr = model.getData().getNoncestr();
                    request.timeStamp = model.getData().getTimeStamp();
                    request.packageValue = "Sign=WXPay";
                    request.sign = model.getData().getWxPayReq();
                    api.sendReq(request);

                }



            }
        }
    };



    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
//                        ReservationCangPayActivity.this.setResult(1);
                        ((Activity)getContext()).finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(getContext(), "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(getContext(),
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(getContext(),
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        };
    };



    class MessageSendReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("wxPay.action")) {
//                ReservationCangPayActivity.this.setResult(1);
                ((Activity)getContext()).finish();
            }
        }
    }

}
