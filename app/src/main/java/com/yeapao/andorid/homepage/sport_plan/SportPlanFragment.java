package com.yeapao.andorid.homepage.sport_plan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseFragment;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by fujindong on 2017/10/23.
 */

public class SportPlanFragment extends BaseFragment {
    private static final String TAG = "SportPlanFragment";

    private ViewPager sportCollectViewPager;
    private TextView sportHintTextView;
    private CircleIndicator circleIndicator;

    private SportPlanViewPager sportPlanViewPager;


    public static SportPlanFragment newInstance() {
        return new SportPlanFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collect_message, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        sportHintTextView = (TextView) view.findViewById(R.id.tv_sport_intro);
        sportCollectViewPager = (ViewPager) view.findViewById(R.id.vp_collect);
        circleIndicator = (CircleIndicator) view.findViewById(R.id.ci_circle_indicator);
        sportHintTextView.setText("        "+getContext().getResources().getString(R.string.sport_plan));
        sportPlanViewPager = new SportPlanViewPager(getContext());
        sportCollectViewPager.setAdapter(sportPlanViewPager);
        circleIndicator.setViewPager(sportCollectViewPager);
        sportCollectViewPager.setCurrentItem(0);
    }


}
