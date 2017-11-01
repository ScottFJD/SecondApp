package com.yeapao.andorid.homepage.sport_plan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseFragment;
import com.yeapao.andorid.model.SportPlanDetailModel;
import com.yeapao.andorid.util.ViewPagerScroller;

import me.relex.circleindicator.CircleIndicator;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/10/24.
 */

public class SportCollectFragment extends BaseFragment {
    private static final String TAG = "SportCollectFragment";

    private ViewPager sportCollectViewPager;
    private TextView sportHintTextView;
    private CircleIndicator circleIndicator;

    private SportPlanViewPager sportPlanViewPager;

    private SportCollectFinishListener sportCollectFinishListener;

    public void setSportCollectFinishListener(SportCollectFinishListener listener) {
        sportCollectFinishListener = listener;
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
        sportPlanViewPager.setCollectListener(new SportCollectClickListener() {
            @Override
            public void itemOnClickListener(String careerType, String sportsCondition, String workForm) {
                LogUtil.e(TAG,careerType+"  "+sportsCondition+"  "+workForm);
                sportCollectFinishListener.sportCollectfinish(careerType,sportsCondition,workForm);
            }
        });
        sportCollectViewPager.setAdapter(sportPlanViewPager);
        circleIndicator.setViewPager(sportCollectViewPager);
        sportCollectViewPager.setCurrentItem(0);
        sportPlanViewPager.setSportCollectPageListener(new SportCollectPageListener() {
            @Override
            public void refreshCurrentPager(int pageNum) {
                sportCollectViewPager.setCurrentItem(pageNum);
            }
        });
        ViewPagerScroller scroller = new ViewPagerScroller(getContext());
        scroller.setScrollDuration(1500);
        scroller.initViewPagerScroll(sportCollectViewPager);
    }



}
