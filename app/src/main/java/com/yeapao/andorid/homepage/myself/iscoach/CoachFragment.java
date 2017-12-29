package com.yeapao.andorid.homepage.myself.iscoach;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
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
//    @BindView(R.id.rv_vip_account_list)
//    RecyclerView rvVipAccountList;
    Unbinder unbinder;
    private String bgRes;
    private StationLessonMessgeAdapter stationLessonMessgeAdapter;

    private EaseConversationListFragment conversationListFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bgRes = getArguments().getString("data");
        conversationListFragment = new EaseConversationListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coach, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        conversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                startActivity(new Intent(getActivity(),ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID,conversation.conversationId()));
            }
        });
        getChildFragmentManager().beginTransaction().add(R.id.fragment_container, conversationListFragment).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
