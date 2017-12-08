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
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.util.GlobalDataYepao;

/**
 * Created by fujindong on 2017/12/8.
 */

public class MyselfLessonActivity extends BaseActivity {

    private static final String TAG = "MyselfLessonActivity";

    private RecyclerView myselfLessonList;

    private MyselfLessonListMessageAdapter lessonListMessageAdapter;


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
    }

    private void initView() {
        myselfLessonList = (RecyclerView) findViewById(R.id.rv_training_lesson_list);
        myselfLessonList.setLayoutManager(new LinearLayoutManager(getContext()));
        showResult();
    }

    private void showResult() {
        lessonListMessageAdapter = new MyselfLessonListMessageAdapter(getContext());
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
}
