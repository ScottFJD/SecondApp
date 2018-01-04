package com.scottfu.sflibrary.dialogFragment;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.scottfu.sflibrary.R;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.SystemDateUtil;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fujindong on 2018/1/4.
 */

public class LessonPlanTimePickerDialogFragment extends DialogFragment {
    private static final String TAG = "LessonPlanTimePickerDialogFragment";
    private TextView mCancel;
    private TextView mDetermine;
    private TextView todayDate;
    private WheelPicker mWeelPicker;
    private WheelPicker mWeelPicker2;
    private TextView title;
    private List<String> pickerList = new ArrayList<>();

    private String time1 = "";
    private String time2 = "";
    private int day1 = 0;

    private CoachLessonPlanTimeListener mListener;


    public void setPickerPainListener(CoachLessonPlanTimeListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity(), R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.dialog_coach_lesson_plan_time);
        dialog.setCanceledOnTouchOutside(false);

        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

//
//        for (int i = 5; i <= 60; i+=5) {
//            pickerList.add(String.valueOf(i));
//        }

        title = (TextView) dialog.findViewById(R.id.tv_age_title);
        mCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        mDetermine = (TextView) dialog.findViewById(R.id.tv_determine);
        todayDate = (TextView) dialog.findViewById(R.id.tv_today_time);
        mWeelPicker = (WheelPicker) dialog.findViewById(R.id.pain_wheel_center);
        mWeelPicker2 = (WheelPicker) dialog.findViewById(R.id.pain_wheel_center_2);
        todayDate.setText("今天是"+ SystemDateUtil.getFetureDateMMDD(0)+SystemDateUtil.getWeekOfDateV2(SystemDateUtil.getDate(0)));
        mWeelPicker.setData(getFutureDays());
        mWeelPicker2.setData(getDateHour());
        mWeelPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
//                mListener.getPainValue(String.valueOf(data));
                time1 = String.valueOf(data);
                day1 = position;

            }
        });
        mWeelPicker2.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                time2 = String.valueOf(data);
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.timePickerCancel();
            }
        });
        mDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mListener.determine();
                if (time1.equals("") || time1 == null) {
                    time1 = getFutureDays().get(0);
                }
                if (time2.equals("") || time2 == null) {
                    time2 = getDateHour().get(0);
                }
                mListener.success(time1,time2,day1);
            }
        });
        return dialog;
    }

    private ArrayList<String> getFutureDays() {
        ArrayList<String> daysList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            String date = SystemDateUtil.getFetureDateMMDD(i) + " " + SystemDateUtil.getWeekOfDateV2(SystemDateUtil.getDate(i));
            LogUtil.e(TAG,date);
            daysList.add(date);
        }
        return daysList;
    }

    private ArrayList<String> getDateHour() {
        ArrayList<String> hours = new ArrayList<>();
        hours.add("8:00-9:00");
        hours.add("9:00-10:00");
        hours.add("21:00-22:00");
        hours.add("22:00-23:00");
        return hours;
    }
}
