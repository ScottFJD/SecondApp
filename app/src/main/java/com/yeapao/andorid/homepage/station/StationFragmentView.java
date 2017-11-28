package com.yeapao.andorid.homepage.station;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseFragment;
import com.yeapao.andorid.homepage.station.dynamiclesson.DynamicLessonActivity;
import com.yeapao.andorid.homepage.station.traininglesson.TrainingLessonActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by fujindong on 2017/11/21.
 */

public class StationFragmentView extends BaseFragment implements StationConstract.View {
    private static final String TAG = "StationFragmentView";
    @BindView(R.id.tv_right_btn)
    TextView tvRightBtn;
    @BindView(R.id.rv_station_list)
    RecyclerView rvStationList;
    @BindView(R.id.srl_station)
    SwipeRefreshLayout srlStation;
    Unbinder unbinder;

    private StationConstract.Presenter mPresenter;
    private LinearLayoutManager linearLayoutManager;
    private StationMessageAdapter stationMessageAdapter;


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
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvStationList.setLayoutManager(linearLayoutManager);
        showResult();
    }

    private void showResult() {
        stationMessageAdapter = new StationMessageAdapter(getContext());
        rvStationList.setAdapter(stationMessageAdapter);
        stationMessageAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                LogUtil.e(TAG,String.valueOf(position));
                if (position == 0) {
                    DynamicLessonActivity.start(getContext());
                } else if (position == 1) {
                    TrainingLessonActivity.start(getContext());
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
