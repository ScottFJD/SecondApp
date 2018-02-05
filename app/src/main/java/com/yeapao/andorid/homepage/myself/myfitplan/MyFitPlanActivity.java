package com.yeapao.andorid.homepage.myself.myfitplan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 02/02/2018.
 */

public class MyFitPlanActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MyFitPlanActivity";

    private String customizeLevel = "";
    private String customizeParts = "";

    private CheckBox firstLevel;
    private CheckBox secondLevel;
    private CheckBox thirdLevel;

    private CheckBox bodyAll;
    private CheckBox chest;
    private CheckBox shoulder;
    private CheckBox center;
    private CheckBox lowerHalf;

    private TextView generateLesson;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyFitPlanActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fit_plan);
        initView();
        initBack();
    }

    private void initView() {
        firstLevel = (CheckBox) findViewById(R.id.cb_first);
        secondLevel = (CheckBox) findViewById(R.id.cb_second);
        thirdLevel = (CheckBox) findViewById(R.id.cb_third);
        firstLevel.setOnClickListener(this);
        secondLevel.setOnClickListener(this);
        thirdLevel.setOnClickListener(this);
        bodyAll = (CheckBox) findViewById(R.id.cb_body_all);
        chest = (CheckBox) findViewById(R.id.cb_body_chest);
        shoulder = (CheckBox) findViewById(R.id.cb_body_shoulder);
        center = (CheckBox) findViewById(R.id.cb_body_center);
        lowerHalf = (CheckBox) findViewById(R.id.cb_body_lower_half);

        generateLesson = (TextView) findViewById(R.id.tv_generate);
        generateLesson.setOnClickListener(this);
    }

    @Override
    protected void initTopBar() {
        initTitle("课程定制");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cb_first:
                if (firstLevel.isChecked()) {
                    secondLevel.setChecked(false);
                    thirdLevel.setChecked(false);
                    customizeLevel = firstLevel.getText().toString();
                }
                break;
            case R.id.cb_second:
                if (secondLevel.isChecked()) {
                    firstLevel.setChecked(false);
                    thirdLevel.setChecked(false);
                    customizeLevel = secondLevel.getText().toString();
                }
                break;
            case R.id.cb_third:
                if (thirdLevel.isChecked()) {
                    firstLevel.setChecked(false);
                    secondLevel.setChecked(false);
                    customizeLevel = thirdLevel.getText().toString();
                }
                break;
            case R.id.tv_generate:
                generateLessonEvent();
                break;
        }
    }


    private void generateLessonEvent() {
        StringBuilder stringBuilder = new StringBuilder();
        if (bodyAll.isChecked()) {
            stringBuilder.append(bodyAll.getText().toString());
        }

        if (chest.isChecked()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("," + chest.getText().toString());
            } else {
                stringBuilder.append(chest.getText().toString());
            }
        }

        if (shoulder.isChecked()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("," + shoulder.getText().toString());
            } else {
                stringBuilder.append(shoulder.getText().toString());
            }
        }

        if (center.isChecked()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("," + center.getText().toString());
            } else {
                stringBuilder.append(center.getText().toString());
            }
        }

        if (lowerHalf.isChecked()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("," + lowerHalf.getText().toString());
            } else {
                stringBuilder.append(lowerHalf.getText().toString());
            }
        }

        if (stringBuilder.length() > 0) {
            customizeParts = stringBuilder.toString();
        } else {
            ToastManager.showToast(getContext(),"请选择健身部位");
            return;
        }
        if (customizeLevel.equals("")) {
            ToastManager.showToast(getContext(), "请选择课程等级");
        } else {
            LogUtil.e(TAG,customizeLevel+"   "+customizeParts);
        }

        MyFitLessonActivity.start(getContext());
    }
}
