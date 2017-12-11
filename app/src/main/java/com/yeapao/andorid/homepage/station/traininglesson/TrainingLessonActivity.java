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
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.EmployeeListModel;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    private EmployeeListModel listModel;

    private String type;

    public static void start(Context context,String type) {
        Intent intent = new Intent();
        intent.putExtra("type", type);
        intent.setClass(context, TrainingLessonActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_lesson);
        trainingLessonList = (RecyclerView) findViewById(R.id.rv_training_lesson_list);
        type = getIntent().getStringExtra("type");
        initTopBar();
        initView();
        getData();
    }

    private void getData() {
        if (type.equals(HIGH)) {
            getNetWork();
        } else {
            getNetWorkRecoveryList();
        }
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        trainingLessonList.setLayoutManager(linearLayoutManager);
        trainingLessonList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void showResult() {
        trainingLessonMessageAdapter = new TrainingLessonMessageAdapter(getContext(),listModel);
        trainingLessonList.setAdapter(trainingLessonMessageAdapter);
        trainingLessonMessageAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                LogUtil.e(TAG,String.valueOf(position));
                TrainingLessonDetailActivity.start(getContext(),String.valueOf(listModel.getData().get(position).getEmployeeId()),type);
            }
        });
    }

    @Override
    protected void initTopBar() {
        if (type.equals(HIGH)) {
            initTitle("高阶训练");
        } else {
            initTitle("康复训练");
        }

        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

            private void getNetWork() {
                    subscription = Network.getYeapaoApi()
                            .requestEmployeeList()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe( modelObserver);
                }


    private void getNetWorkRecoveryList() {
        subscription = Network.getYeapaoApi()
                .requestRecoveryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( modelObserver);
    }


                  Observer<EmployeeListModel> modelObserver = new Observer<EmployeeListModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(EmployeeListModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            listModel = model;
                            showResult();
                        }
                    }
                };
}
