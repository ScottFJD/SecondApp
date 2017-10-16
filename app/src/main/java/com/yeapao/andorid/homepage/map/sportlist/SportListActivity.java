package com.yeapao.andorid.homepage.map.sportlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.SportListModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/10/13.
 */

public class SportListActivity extends BaseActivity {
    private static final String TAG = "SportListActivity";

    private SportListMessageAdapter sportListMessageAdapter;
    private RecyclerView rvSportList;
    private LinearLayoutManager llm;

    private SportListModel sportListModel = new SportListModel();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_list);
        initTopBar();
        initView();
    }

    private void initView() {
        rvSportList = (RecyclerView) findViewById(R.id.rv_sport_list);
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvSportList.setLayoutManager(llm);
        getNetWork();
    }

    private void showResult(SportListModel model) {
        sportListMessageAdapter = new SportListMessageAdapter(getContext(),model);
        rvSportList.setAdapter(sportListMessageAdapter);
    }

    @Override
    protected void initTopBar() {
        initTitle("排行榜");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }



            private void getNetWork() {
                    subscription = Network.getYeapaoApi()
                            .requestRankingList(GlobalDataYepao.getUser(getContext()).getId())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(modelObserver );
                }

                  Observer<SportListModel> modelObserver = new Observer<SportListModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(SportListModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            sportListModel = model;
                            showResult(model);
                        }
                    }
                };


}
