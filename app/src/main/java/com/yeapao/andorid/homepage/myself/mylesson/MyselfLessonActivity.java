package com.yeapao.andorid.homepage.myself.mylesson;

import android.app.DialogFragment;
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
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.model.MyselfClassModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/12/8.
 */

public class MyselfLessonActivity extends BaseActivity {

    private static final String TAG = "MyselfLessonActivity";

    private RecyclerView myselfLessonList;

    private MyselfLessonListMessageAdapter lessonListMessageAdapter;

    private MyselfClassModel myselfClassModel;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfLessonActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_training_lesson);
        initTopBar();
        initView();
        getNetWork(GlobalDataYepao.getUser(getContext()).getId());
    }

    private void initView() {
        myselfLessonList = (RecyclerView) findViewById(R.id.rv_training_lesson_list);
        myselfLessonList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void showResult() {
        lessonListMessageAdapter = new MyselfLessonListMessageAdapter(getContext(),myselfClassModel);
        myselfLessonList.setAdapter(lessonListMessageAdapter);
        lessonListMessageAdapter.setRecyclerViewClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                DialogUtils.showQRCodeV2(getContext(), GlobalDataYepao.getUser(getContext()).getId());
            }
        });

    }


    @Override
    protected void initTopBar() {
        initTitle("我的课程");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }


    private void getNetWork(String id) {
        LogUtil.e(TAG, id);
        subscription = Network.getYeapaoApi()
                .requestMyselfClass(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<MyselfClassModel> modelObserver = new Observer<MyselfClassModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(MyselfClassModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                myselfClassModel = model;
                showResult();
            }
        }
    };


}
