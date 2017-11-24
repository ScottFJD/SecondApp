package com.yeapao.andorid.homepage.station.dynamiclesson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseFragment;

/**
 * Created by fujindong on 2017/11/22.
 */

public class DynamicLessonListFragment extends BaseFragment {
    private static final String TAG = "DynamicLessonListFragment";


    private RecyclerView rvDynamicLesson;
    private String bgRes;
    private DynamicLessonAdapter dynamicLessonAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bgRes = getArguments().getString("data");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamic_lesson, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view) {
        rvDynamicLesson = (RecyclerView) view.findViewById(R.id.rv_dynamic_lesson_list);
        rvDynamicLesson.setLayoutManager(new LinearLayoutManager(getContext()));
        showResult();
    }

    private void showResult() {
        dynamicLessonAdapter = new DynamicLessonAdapter(getContext());
        rvDynamicLesson.setAdapter(dynamicLessonAdapter);
        dynamicLessonAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                DynamicLessonDetailActivity.start(getContext());
            }
        });

    }

}
