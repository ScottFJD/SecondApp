package com.yeapao.andorid.homepage.sport_plan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.MainActivity;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseFragment;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.model.SportPlanDetailModel;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/10/24.
 */

public class SportPlanDetailFragment extends BaseFragment {
    private static final String TAG = "SportPlanDetailFragment";

    private RecyclerView sportPlanRecycleView;
    private SprotDetailMessageAdapter sportDetailMessageAdapter;
    private SportPlanDetailModel sportDetailModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTransitiveData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport_plan_detail, container, false);
        initView(view);
        return view;
    }

    private void getTransitiveData() {
        sportDetailModel = (SportPlanDetailModel) getArguments().getSerializable("sportDetail");
    }

    private void initView(View view) {
        sportPlanRecycleView = (RecyclerView) view.findViewById(R.id.rv_sport_plan_detail_list);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        sportPlanRecycleView.setLayoutManager(llm);
        sportDetailMessageAdapter = new SprotDetailMessageAdapter(getContext(),sportDetailModel);
        sportPlanRecycleView.setAdapter(sportDetailMessageAdapter);
        sportDetailMessageAdapter.setSportPlanVideoClickListener(new SportPlanVideoListener() {
            @Override
            public void sportPlanVideoClickListener(String videoId, String programmeId) {
                requestVideoClick(videoId,programmeId);
            }

            @Override
            public void refreshSportPlanStatus(int dayId, int videoType) {

                sportDetailMessageAdapter.notifyItemChanged(0);
            }
        });

        sportDetailMessageAdapter.setSportClickDayRefreshListener(new SportClickDayRefreshListener() {
            @Override
            public void refreshSportPlanList() {
                sportDetailMessageAdapter.notifyItemChanged(3);
                sportDetailMessageAdapter.notifyItemChanged(4);
                sportDetailMessageAdapter.notifyItemChanged(5);
            }
        });

        sportDetailMessageAdapter.setCheckCurrentItemListener(new CheckCurrentItemListener() {
            @Override
            public void checkItem(int ppsition) {
                MainActivity.getMainActivity().checkItem();
            }
        });
    }



            private void requestVideoClick(String videoId,String programmeId) {
                    LogUtil.e(TAG,videoId+programmeId);
                    subscription = Network.getYeapaoApi()
                            .requestVideoClick(videoId,programmeId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(modelObserver );
                }

                  Observer<NormalDataModel> modelObserver = new Observer<NormalDataModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(NormalDataModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {

                        }
                    }
                };


    @Override
    public void onPause() {
        super.onPause();
        LogUtil.e(TAG,"onPause");
    }
}
