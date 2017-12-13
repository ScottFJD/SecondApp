package com.yeapao.andorid.homepage.station.inclusive;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.SystemDateUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.util.PickerPainListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fujindong on 2017/12/13.
 */

public class InclusiveTimePickerDialogFragment extends DialogFragment {
    private static final String TAG = "InclusiveTimePickerDialogFragment";
    private TextView mCancel;
    private TextView mDetermine;
    private TextView todayDate;
    private WheelPicker mWeelPicker;
    private WheelPicker mWeelPicker2;
    private TextView title;
    private List<String> pickerList = new ArrayList<>();


    private PickerPainListener mListener;


    public void setPickerPainListener(PickerPainListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity(), R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.dialog_inclusive_paicker);
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

        mWeelPicker.setData(getFutureDays());
        mWeelPicker2.setData(getDateHour());
        mWeelPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
//                mListener.getPainValue(String.valueOf(data));
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mListener.cancel();
            }
        });
        mDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mListener.determine();
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
