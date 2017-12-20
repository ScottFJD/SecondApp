package com.yeapao.andorid.homepage.myself.orders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.model.StationDynamicOrderModel;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/12/2.
 */

public class StationOrderDetailActivity extends BaseActivity {
    private static final String TAG = "StationOrderDetailActivity";
    @BindView(R.id.tv_station_order_1)
    TextView tvStationOrder1;
    @BindView(R.id.tv_station_order_2)
    TextView tvStationOrder2;
    @BindView(R.id.tv_cang_order_time)
    TextView tvCangOrderTime;
    @BindView(R.id.tv_station_order_3)
    TextView tvStationOrder3;
    @BindView(R.id.tv_station_order_4)
    TextView tvStationOrder4;
    @BindView(R.id.iv_dinner_11)
    ImageView ivDinner11;
    @BindView(R.id.tv_station_people)
    TextView tvStationPeople;
    @BindView(R.id.tv_station_people_sum)
    TextView tvStationPeopleSum;
    @BindView(R.id.iv_line_pay_way)
    ImageView ivLinePayWay;
    @BindView(R.id.tv_station_order_price)
    TextView tvStationOrderPrice;
    @BindView(R.id.iv_line_1)
    ImageView ivLine1;
    @BindView(R.id.tv_station_delete)
    TextView tvStationDelete;
    @BindView(R.id.tv_pay_way)
    TextView tvPayWay;
    @BindView(R.id.iv_cang_order_6)
    ImageView ivCangOrder6;
    @BindView(R.id.tv_station_coupon)
    TextView tvStationCoupon;
    @BindView(R.id.tv_discount)
    TextView tvDiscount;
    @BindView(R.id.iv_line_7)
    ImageView ivLine7;
    @BindView(R.id.iv_line_6)
    ImageView ivLine6;
    @BindView(R.id.tv_real_pay)
    TextView tvRealPay;
    @BindView(R.id.cl_order_detail)
    ConstraintLayout clOrderDetail;


    private StationDynamicOrderModel stationDynamicOrderModel;
    private String calOrderId;

    public static void start(Context context, String calOrderId) {
        Intent intent = new Intent();
        intent.putExtra("calOrderId", calOrderId);
        intent.setClass(context, StationOrderDetailActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_order_detail);
        ButterKnife.bind(this);
        initTopBar();
        calOrderId = getIntent().getStringExtra("calOrderId");
        getNetWork(calOrderId);
    }


    @Override
    protected void initTopBar() {
        initTitle("订单详情");
        initBack();
    }

    private void showView() {
        tvStationOrder1.setText(stationDynamicOrderModel.getData().getCalendarOrderCode());
        tvStationOrder2.setText(stationDynamicOrderModel.getData().getStartDate());
        tvStationOrder3.setText(stationDynamicOrderModel.getData().getDate()+" "+stationDynamicOrderModel.getData().getTime());
        tvStationOrder4.setText(stationDynamicOrderModel.getData().getShopAddress());
        tvStationPeopleSum.setText(String.valueOf(stationDynamicOrderModel.getData().getNum())+"人");
        if (stationDynamicOrderModel.getData().getTypes().equals("1")) {
            ivCangOrder6.setImageResource(R.drawable.buy_alipay);
        } else {
            ivCangOrder6.setImageResource(R.drawable.buy_wechat);
        }
        tvStationOrderPrice.setText(getResources().getString(R.string.RMB) + String.valueOf(stationDynamicOrderModel.getData().getActualPrice()));
        tvStationCoupon.setText("无");
        tvDiscount.setText(stationDynamicOrderModel.getData().getDiscountName());
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        tvRealPay.setText(getResources().getString(R.string.RMB)+decimalFormat.format(stationDynamicOrderModel.getData().getDiscountPrice()));

    }


    @OnClick(R.id.tv_station_delete)
    void setStationDelete(View view) {
            getNetWorkDelete(calOrderId);
    }

    @Override
    protected Context getContext() {
        return this;
    }

    private void getNetWork(String id) {
        LogUtil.e(TAG, id);
        subscription = Network.getYeapaoApi()
                .requestStationDynamicOrderDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<StationDynamicOrderModel> modelObserver = new Observer<StationDynamicOrderModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(StationDynamicOrderModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                stationDynamicOrderModel = model;
                showView();
            }
        }
    };



    private void getNetWorkDelete(String id) {
        LogUtil.e(TAG, id);
        subscription = Network.getYeapaoApi()
                .requestDeleteStationOrder(id, "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserverDelete);
    }

    Observer<NormalDataModel> modelObserverDelete = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {
            finish();
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());
            ToastManager.showToast(getContext(), "删除订单失败");
        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                finish();
            }
        }
    };

}
