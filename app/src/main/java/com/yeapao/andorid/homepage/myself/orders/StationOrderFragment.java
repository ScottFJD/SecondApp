package com.yeapao.andorid.homepage.myself.orders;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseFragment;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.homepage.station.traininglesson.TrainingLessonDetailActivity;
import com.yeapao.andorid.model.CangReservationOrderListModel;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.model.StationOrderList;
import com.yeapao.andorid.util.GlobalDataYepao;

import java.security.Key;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/12/1.
 */

public class StationOrderFragment extends BaseFragment {
    private static final String TAG = "StationOrderFragment";


    private RecyclerView mCangReservationRecyclerView;
    private RelativeLayout mNoDataRelativeLayout;
    private StationOrderMessageAdapter mMessageAdapter;
    private CangReservationOrderListModel cangReservationOrderListModel = new CangReservationOrderListModel();

    private StationOrderList stationOrderList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cang_reservation, container, false);
        getNetWorkStationOrderList(GlobalDataYepao.getUser(getContext()).getId());
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mCangReservationRecyclerView = (RecyclerView) view.findViewById(R.id.rv_cang_reservation_list);
        mCangReservationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mNoDataRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl_no_date);
        mCangReservationRecyclerView.setVisibility(View.GONE);
        mNoDataRelativeLayout.setVisibility(View.VISIBLE);

    }

    private void getNetWork(String id) {
        LogUtil.e(TAG,id);
        subscription = Network.getYeapaoApi()
                .requestCangReservationOrderList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver );
    }

    Observer<CangReservationOrderListModel> modelObserver = new Observer<CangReservationOrderListModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG,e.toString());

        }

        @Override
        public void onNext(CangReservationOrderListModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                cangReservationOrderListModel = model;
                showResult();
            }
        }
    };

    private void showResult() {
        if (stationOrderList.getData().size() == 0) {
            mCangReservationRecyclerView.setVisibility(View.GONE);
            mNoDataRelativeLayout.setVisibility(View.VISIBLE);
        } else {
            mCangReservationRecyclerView.setVisibility(View.VISIBLE);
            mNoDataRelativeLayout.setVisibility(View.GONE);
        }

        mMessageAdapter = new StationOrderMessageAdapter(getContext(), stationOrderList);
        mCangReservationRecyclerView.setAdapter(mMessageAdapter);
        mMessageAdapter.setOnItemClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                if (stationOrderList.getData().get(position).getTypes().equals("1")) {
                    StationOrderDetailActivity.start(getContext(),String.valueOf(stationOrderList.getData().get(position).getId()));
                } else if (stationOrderList.getData().get(position).getTypes().equals("2")||
                        stationOrderList.getData().get(position).getTypes().equals("3")) {
                    TrainLessonDetailActivity.start(getContext(),stationOrderList.getData().get(position).getTypes(),String.valueOf(stationOrderList.getData().get(position).getId()));
                }

//                CangOrderDetailActivity.start(getContext(),String.valueOf(cangReservationOrderListModel.getData().get(position).getReservaOrdersId()),CangOrderDetailActivity.CangReservation);
            }
        });
        mMessageAdapter.setCangOrderStatusListener(new CangOrderStatusListener() {
            @Override
            public void cangOrderDo(int flag) {
                DialogUtils.showProgressDialog(getContext(),true);
                getNetWorkDeleteOrders(String.valueOf(stationOrderList.getData().get(flag).getId()), stationOrderList.getData().get(flag).getTypes());
//                getNetWorkDeleteReservationOrders( String.valueOf(cangReservationOrderListModel.getData().get(flag).getReservaOrdersId()));
            }
        });

    }

    private void getNetWorkDeleteOrders(String id,String type) {
        LogUtil.e(TAG,id);
        subscription = Network.getYeapaoApi()
                .requestDeleteStationOrder(id,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( modelObserverDeleteOrders);
    }

    Observer<NormalDataModel> modelObserverDeleteOrders = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {
            DialogUtils.cancelProgressDialog();
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG,e.toString());
            DialogUtils.cancelProgressDialog();
        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                DialogUtils.cancelProgressDialog();
                getNetWorkStationOrderList(GlobalDataYepao.getUser(getContext()).getId());
            }
        }
    };


            private void getNetWorkStationOrderList(String id) {
                    LogUtil.e(TAG,id);
                    subscription = Network.getYeapaoApi()
                            .requestStationOrder(id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(modelObserverStationOrder );
                }

                  Observer<StationOrderList> modelObserverStationOrder = new Observer<StationOrderList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(StationOrderList model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            stationOrderList = model;
                            showResult();
                        }
                    }
                };

}
