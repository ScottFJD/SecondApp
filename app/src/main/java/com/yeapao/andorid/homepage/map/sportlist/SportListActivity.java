package com.yeapao.andorid.homepage.map.sportlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/10/13.
 */

public class SportListActivity extends BaseActivity {
    private static final String TAG = "SportListActivity";

    private SportListMessageAdapter sportListMessageAdapter;
    private RecyclerView rvSportList;
    private LinearLayoutManager llm;


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

        showResult();
    }

    private void showResult() {
        sportListMessageAdapter = new SportListMessageAdapter(getContext());
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

}
