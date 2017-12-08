package com.yeapao.andorid.homepage.station.traininglesson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/11/27.
 */

public class TrainingLessonActivity extends BaseActivity {
    private static final String TAG  = "TrainingLessonActivity";
    public static final String HIGH = "high_lesson";
    public static final String HEALTH = "health";

    private RecyclerView trainingLessonList;
    private LinearLayoutManager linearLayoutManager;
    private TrainingLessonMessageAdapter trainingLessonMessageAdapter;


    private String type;

    public static void start(Context context,String type) {
        Intent intent = new Intent();
        intent.setClass(context, TrainingLessonActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_lesson);
        trainingLessonList = (RecyclerView) findViewById(R.id.rv_training_lesson_list);
        initTopBar();
        initView();
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        trainingLessonList.setLayoutManager(linearLayoutManager);
        trainingLessonList.setLayoutManager(new LinearLayoutManager(getContext()));
        showResult();
    }

    private void showResult() {
        trainingLessonMessageAdapter = new TrainingLessonMessageAdapter(getContext());
        trainingLessonList.setAdapter(trainingLessonMessageAdapter);
        trainingLessonMessageAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                LogUtil.e(TAG,String.valueOf(position));
                TrainingLessonDetailActivity.start(getContext());
            }
        });
    }

    @Override
    protected void initTopBar() {
        initTitle("高阶训练");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
