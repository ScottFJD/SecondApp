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
import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.homepage.map.repository.ReservationCangPayActivity;
import com.yeapao.andorid.model.CallPaymentModel;
import com.yeapao.andorid.model.DiscountOrderModel;
import com.yeapao.andorid.model.DynamicDiscountCardModel;
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
 * Created by fujindong on 2017/11/30.
 */

public class DynamicPeopleEquityActivity extends BaseActivity {
    private static final String TAG = "DynamicPeopleEquityActivity";
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.iv_discount_card)
    ImageView ivDiscountCard;
    @BindView(R.id.iv_qr_code)
    ImageView ivQrCode;
    @BindView(R.id.tv_discount_time)
    TextView tvDiscountTime;
    @BindView(R.id.iv_dinner)
    ImageView ivDinner;
    @BindView(R.id.iv_explain)
    ImageView ivExplain;
    @BindView(R.id.tv_explain_title)
    TextView tvExplainTitle;
    @BindView(R.id.tv_equity_price)
    TextView tvEquityPrice;
    @BindView(R.id.tv_immediately_pay_equity)
    TextView tvImmediatelyPayEquity;
    @BindView(R.id.iv_discount_status)
    ImageView ivDiscountStatus;


    private String isDiscount;
    private DynamicDiscountCardModel dynamicDiscountCardModel;
    private DiscountOrderModel discountOrderModel;
    private GlideUtil glideUtil = new GlideUtil();
    private String payMentType;
    private MyPayWayDialogFragment myPayWayDialogFragment;


    //    支付
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    private MessageSendReceiver numberReceiver;
    IWXAPI api;



    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, DynamicPeopleEquityActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_equity);
        ButterKnife.bind(this);
        initTopBar();
        getNetWork(GlobalDataYepao.getUser(getContext()).getId());

//        之前的少了这一段不知道有没有问题
        if (numberReceiver == null) {
            numberReceiver = new MessageSendReceiver();
        }
        getContext().registerReceiver(numberReceiver, new IntentFilter("wxPay.action"));
        api = WXAPIFactory.createWXAPI(getContext(), ConstantYeaPao.APP_ID, true);
        api.registerApp(ConstantYeaPao.APP_ID);
    }


    private void showView() {
        glideUtil.glideLoadingImage(getContext(),dynamicDiscountCardModel.getData().getUrl(),R.drawable.placeholder,ivDiscountCard);
        if (dynamicDiscountCardModel.getData().getIsOpen().equals("1")) {
            tvEquityPrice.setVisibility(View.GONE);
            tvImmediatelyPayEquity.setVisibility(View.GONE);
            tvDiscountTime.setVisibility(View.VISIBLE);
            tvDiscountTime.setText("有效期：" + dynamicDiscountCardModel.getData().getStartDate() + "至" + dynamicDiscountCardModel.getData().getEndDate());
            ivQrCode.setVisibility(View.VISIBLE);
            ivDiscountStatus.setImageResource(R.drawable.discount_label_s);
        } else {
            ivQrCode.setVisibility(View.GONE);
            tvDiscountTime.setVisibility(View.GONE);
            tvEquityPrice.setVisibility(View.VISIBLE);
            tvImmediatelyPayEquity.setVisibility(View.VISIBLE);
            tvEquityPrice.setText(String.valueOf(getResources().getString(R.string.RMB) + dynamicDiscountCardModel.getData().getPrice()));
            ivQrCode.setVisibility(View.GONE);
            ivDiscountStatus.setImageResource(R.drawable.discount_card_n);
        }
    }


    @OnClick(R.id.iv_qr_code)
    void setIvQrCode(View view) {
        DialogUtils.showQRCodeV2(getContext(),GlobalDataYepao.getUser(getContext()).getId());
    }


    @Override
    protected void initTopBar() {
        initTitle("跑友权益卡");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }


    private void getNetWork(String id) {
        LogUtil.e(TAG, id);
        subscription = Network.getYeapaoApi()
                .requestDynamicDiscountCard(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<DynamicDiscountCardModel> modelObserver = new Observer<DynamicDiscountCardModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(DynamicDiscountCardModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                dynamicDiscountCardModel = model;
                showView();
            }
        }
    };


            private void getNetWorkOrder(String id) {
                    LogUtil.e(TAG,id);
                    subscription = Network.getYeapaoApi()
                            .requestDiscountOrder(id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe( modelObserverOrder);
                }

                  Observer<DiscountOrderModel> modelObserverOrder = new Observer<DiscountOrderModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());
                        ToastManager.showToast(getContext(),"创建订单失败");

                    }

                    @Override
                    public void onNext(DiscountOrderModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            discountOrderModel = model;
                            initPayUI();
                        }
                    }
                };


    @OnClick(R.id.tv_immediately_pay_equity)
    void setTvImmediatelyPayEquity(View view) {
        getNetWorkOrder(GlobalDataYepao.getUser(getContext()).getId());

    }


    private void initPayUI() {
        myPayWayDialogFragment = MyPayWayDialogFragment.newInstance(String.valueOf(discountOrderModel.getData().getPrice()));
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
                getPayment(String.valueOf(discountOrderModel.getData().getPrice()),discountOrderModel.getData().getDisCardOrderCode(),payMentType);
            }
        });




        if (myPayWayDialogFragment.isVisible()) {
            myPayWayDialogFragment.dismiss();
        } else {
            myPayWayDialogFragment.show(getSupportFragmentManager(), "date");
        }
    }





    private void getPayment(String price, String orderCode, String paymentType) {

        LogUtil.e(TAG, price+"==="+orderCode+"+++"+paymentType);
        subscription = Network.getYeapaoApi()
                .requestPayment(price,orderCode,paymentType)
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
                            PayTask alipay = new PayTask(DynamicPeopleEquityActivity.this);
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
                ((Activity)getContext()).finish();
            }
        }
    }


}
