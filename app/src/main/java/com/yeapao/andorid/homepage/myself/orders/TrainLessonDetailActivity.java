package com.yeapao.andorid.homepage.myself.orders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.homepage.station.traininglesson.TrainingLessonActivity;
import com.yeapao.andorid.model.HighLessonModel;
import com.yeapao.andorid.model.InclusiveOrderDetailModel;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.model.RecoveryDetailModel;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/12/19.
 */

public class TrainLessonDetailActivity extends BaseActivity {
    private static final String TAG = "TrainLessonDetailActivity";
    @BindView(R.id.tv_cang_order_title)
    TextView tvCangOrderTitle;
    @BindView(R.id.tv_station_order_1)
    TextView tvStationOrder1;
    @BindView(R.id.tv_station_order_2)
    TextView tvStationOrder2;
    @BindView(R.id.tv_cang_order_time)
    TextView tvCangOrderTime;
    @BindView(R.id.tv_station_title_2)
    TextView tvStationTitle2;
    @BindView(R.id.tv_station_order_3)
    TextView tvStationOrder3;
    @BindView(R.id.tv_station_order_4)
    TextView tvStationOrder4;
    @BindView(R.id.tv_station_people)
    TextView tvStationPeople;
    @BindView(R.id.tv_station_people_sum)
    TextView tvStationPeopleSum;
    @BindView(R.id.rl_lesson_time)
    RelativeLayout rlLessonTime;
    @BindView(R.id.iv_line_pay_way)
    ImageView ivLinePayWay;
    @BindView(R.id.tv_station_delete)
    TextView tvStationDelete;
    @BindView(R.id.iv_cang_order_6)
    ImageView ivCangOrder6;
    @BindView(R.id.tv_real_pay)
    TextView tvRealPay;
    @BindView(R.id.cl_order_detail)
    ConstraintLayout clOrderDetail;

    private String type;
    private String id;

    private HighLessonModel highLessonModel;
    private RecoveryDetailModel recoveryDetailModel;
    private InclusiveOrderDetailModel inclusiveOrderDetailModel;


    public static void start(Context context, String types, String id) {
        Intent intent = new Intent();
        intent.putExtra("type", types);
        intent.putExtra("id", id);
        intent.setClass(context, TrainLessonDetailActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_order_detail_v2);
        ButterKnife.bind(this);
        type = getIntent().getStringExtra("type");
        id = getIntent().getStringExtra("id");
        initTopBar();
        if (type.equals("2")) {
            getNetWork(id);
        } else if (type.equals("3")) {
            getNetWorkRecovery(id);
        } else {
            getNetWorkInclusive(id);
        }
    }

    @OnClick(R.id.tv_station_delete)
    void setTvStationDelete(View view) {
        getNetWorkDelete(id);
    }

    private void showResult() {
        tvCangOrderTime.setText("课程服务地址");
        tvStationTitle2.setText("课程服务类型");
        tvStationPeople.setText("课程有效期至");
        if (type.equals("2")) {
            rlLessonTime.setVisibility(View.GONE);
            tvCangOrderTitle.setText("高阶课程服务");
            tvStationOrder1.setText(highLessonModel.getData().getEmHighOrderCode());
            tvStationOrder2.setText(highLessonModel.getData().getStartDate());
            tvStationOrder3.setText(highLessonModel.getData().getShopAddress());
            tvStationOrder4.setText(highLessonModel.getData().getSubjectFeeType());
            if (highLessonModel.getData().getTypes().equals("1")) {
                ivCangOrder6.setImageResource(R.drawable.buy_alipay);
            } else {
                ivCangOrder6.setImageResource(R.drawable.buy_wechat);
            }
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            tvRealPay.setText(getResources().getString(R.string.RMB) + decimalFormat.format(highLessonModel.getData().getPrice()));
        } else {
            tvCangOrderTitle.setText("康复训练服务");
            tvStationOrder1.setText(recoveryDetailModel.getData().getRecoveryOrderCode());
            tvStationOrder2.setText(recoveryDetailModel.getData().getStartDate());
            tvStationOrder3.setText(recoveryDetailModel.getData().getShopAddress());
            tvStationOrder4.setText(recoveryDetailModel.getData().getSubjectFeeType());
            if (recoveryDetailModel.getData().getTypes().equals("1")) {
                ivCangOrder6.setImageResource(R.drawable.buy_alipay);
            } else {
                ivCangOrder6.setImageResource(R.drawable.buy_wechat);
            }
            tvStationPeopleSum.setText(recoveryDetailModel.getData().getEndDate());
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            tvRealPay.setText(getResources().getString(R.string.RMB) + decimalFormat.format(highLessonModel.getData().getPrice()));
        }

    }

    private void showResult2() {
        tvCangOrderTime.setText("包场时间");
        tvStationTitle2.setText("是否有教练");
        tvStationPeople.setText("场地");
        tvCangOrderTitle.setText("土豪包场");
        tvStationOrder1.setText(inclusiveOrderDetailModel.getData().getPrUseOrderCode());
        tvStationOrder2.setText(inclusiveOrderDetailModel.getData().getStartDate());
        tvStationOrder3.setText(inclusiveOrderDetailModel.getData().getDate() + " " + inclusiveOrderDetailModel.getData().getTime());
        if (inclusiveOrderDetailModel.getData().getSelectEmployee().equals("1")) {
            tvStationOrder4.setText("是");
        } else {
            tvStationOrder4.setText("否");
        }
        tvStationPeopleSum.setText(inclusiveOrderDetailModel.getData().getField());
        if (inclusiveOrderDetailModel.getData().getTypes().equals("1")) {
            ivCangOrder6.setImageResource(R.drawable.buy_alipay);
        } else {
            ivCangOrder6.setImageResource(R.drawable.buy_wechat);
        }

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        tvRealPay.setText(getResources().getString(R.string.RMB) + decimalFormat.format(inclusiveOrderDetailModel.getData().getPrice()));

    }

    @Override
    protected void initTopBar() {
        initTitle("订单详情");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    private void getNetWork(String id) {
        LogUtil.e(TAG, id);
        subscription = Network.getYeapaoApi()
                .requestHighLessonDetailActivity(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<HighLessonModel> modelObserver = new Observer<HighLessonModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(HighLessonModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                highLessonModel = model;
                showResult();
            }
        }
    };


    private void getNetWorkRecovery(String id) {
        LogUtil.e(TAG, id);
        subscription = Network.getYeapaoApi()
                .requestRecoveryDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserverRecover);
    }

    Observer<RecoveryDetailModel> modelObserverRecover = new Observer<RecoveryDetailModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(RecoveryDetailModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                recoveryDetailModel = model;
                showResult();
            }
        }
    };


    private void getNetWorkInclusive(String id) {
        LogUtil.e(TAG, id);
        subscription = Network.getYeapaoApi()
                .requestInclusiveOrderDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserverInclusive);
    }

    Observer<InclusiveOrderDetailModel> modelObserverInclusive = new Observer<InclusiveOrderDetailModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(InclusiveOrderDetailModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                inclusiveOrderDetailModel = model;
                showResult2();
            }
        }
    };

    private void getNetWorkDelete(String id) {
        LogUtil.e(TAG, id);
        subscription = Network.getYeapaoApi()
                .requestDeleteStationOrder(id, type)
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
