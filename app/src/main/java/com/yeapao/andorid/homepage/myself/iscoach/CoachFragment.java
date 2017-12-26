package com.yeapao.andorid.homepage.myself.iscoach;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by fujindong on 2017/12/25.
 */

public class CoachFragment extends BaseFragment {
    @BindView(R.id.rv_lesson_plan_list)
    RecyclerView rvLessonPlanList;
    @BindView(R.id.rv_vip_account_list)
    RecyclerView rvVipAccountList;
    Unbinder unbinder;
    private String bgRes;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bgRes = getArguments().getString("data");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coach, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
