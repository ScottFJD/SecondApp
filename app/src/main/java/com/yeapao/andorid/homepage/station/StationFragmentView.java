package com.yeapao.andorid.homepage.station;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseFragment;
import com.yeapao.andorid.homepage.station.dynamiclesson.DynamicLessonActivity;
import com.yeapao.andorid.homepage.station.inclusive.InclusiveActivity;
import com.yeapao.andorid.homepage.station.traininglesson.TrainingLessonActivity;
import com.yeapao.andorid.homepage.station.traininglesson.TrainingLessonOrderActivity;
import com.yeapao.andorid.model.StationMainBannerModel;
import com.yeapao.andorid.popupwindow.PopupWindowChat;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/11/21.
 */

public class StationFragmentView extends BaseFragment implements StationConstract.View {
    private static final String TAG = "StationFragmentView";
    @BindView(R.id.tv_right_btn)
    TextView tvRightBtn;
    @BindView(R.id.rv_station_list)
    RecyclerView rvStationList;
//    @BindView(R.id.srl_station)
//    SwipeRefreshLayout srlStation;
    Unbinder unbinder;

    private StationConstract.Presenter mPresenter;
    private LinearLayoutManager linearLayoutManager;
    private StationMessageAdapter stationMessageAdapter;
    private StationMainBannerModel stationMainBannerModel;

    private PopupWindowChat popupWindowChat;

    public static StationFragmentView newInstance() {
        return new StationFragmentView();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_run_station, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (GlobalDataYepao.isLogin()) {
            getNetWorkId(GlobalDataYepao.getUser(getContext()).getId());
        } else {
            getNetWork();
        }

        initViews(view);
        return view;
    }

    @Override
    public void setPresenter(StationConstract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        popupWindowChat = new PopupWindowChat();
        popupWindowChat.initPopChat(getContext());

        tvRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InclusiveActivity.start(getContext());
            }
        });
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvStationList.setLayoutManager(linearLayoutManager);
    }

    private void showResult() {
        stationMessageAdapter = new StationMessageAdapter(getContext(),stationMainBannerModel);
        rvStationList.setAdapter(stationMessageAdapter);
        stationMessageAdapter.setStationHeaderClickListener(new StationMessageAdapter.StationHeaderClickListener() {
            @Override
            public void onHeaderClick(View view) {
                popupWindowChat.showAtLocation(view, Gravity.LEFT,20,-200);
                popupWindowChat.setChatClickListener(new PopupWindowChat.ChatClickListener() {
                    @Override
                    public void reservationLessonClick() {
                        LogUtil.e(TAG,"reservationLessonClick");
                    }

                    @Override
                    public void sendClick() {
                        LogUtil.e(TAG,"sendClick");
                    }
                });
            }
        });

        stationMessageAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                LogUtil.e(TAG, String.valueOf(position));
                if (position == 0) {
                    DynamicLessonActivity.start(getContext());
                } else if (position == 1) {
                    TrainingLessonActivity.start(getContext(), TrainingLessonActivity.HIGH);
                } else {
                    TrainingLessonActivity.start(getContext(),TrainingLessonActivity.HEALTH);
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void getNetWork() {
        subscription = Network.getYeapaoApi()
                .getStationMainModel()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }
    private void getNetWorkId(String id) {
        subscription = Network.getYeapaoApi()
                .getStationMainModelId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<StationMainBannerModel> modelObserver = new Observer<StationMainBannerModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(StationMainBannerModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                stationMainBannerModel = model;
                showResult();
            }
        }
    };

}
