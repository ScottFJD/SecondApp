package com.hyphenate.easeui.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hyphenate.easeui.R;
import com.scottfu.sflibrary.dialogFragment.CoachLessonPlanTimeListener;
import com.scottfu.sflibrary.dialogFragment.LessonPlanTimePickerDialogFragment;
import com.scottfu.sflibrary.util.LogUtil;

/**
 * Created by fujindong on 2018/1/4.
 */

public class AddLessonDialogFragment extends DialogFragment {
    private static final String TAG = "AddLessonDialogFragment";
    private TextView lessonNameTextView;
    private TextView lessonTimeTextView;
    private TextView cancelTextView;
    private TextView acceptTextView;

    private LessonPlanTimePickerDialogFragment lessonPlanTimePickerDialogFragment;

    public interface LessonPlanListener {

        void cancel();

        void accept(String time);
    }

    private LessonPlanListener lessonPlanListener;


    public void setLessonPlanListener(LessonPlanListener listener) {
        if (listener != null) {
            lessonPlanListener = listener;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), com.scottfu.sflibrary.R.style.custom_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_lesson);
        dialog.setCanceledOnTouchOutside(true);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        wlp.width = (int) ( display.getWidth()* 0.8);
        window.setAttributes(wlp);

        lessonNameTextView = (TextView) dialog.findViewById(R.id.tv_lesson_name);
        lessonTimeTextView = (TextView) dialog.findViewById(R.id.tv_lesson_time);
        cancelTextView = (TextView) dialog.findViewById(R.id.tv_cancel);
        acceptTextView = (TextView) dialog.findViewById(R.id.tv_sure);

        lessonPlanTimePickerDialogFragment = new LessonPlanTimePickerDialogFragment();
        lessonPlanTimePickerDialogFragment.setPickerPainListener(new CoachLessonPlanTimeListener() {
            @Override
            public void timePickerCancel() {
                lessonPlanTimePickerDialogFragment.dismiss();
            }

            @Override
            public void success(String time1, String time2, int day1) {
                LogUtil.e(TAG,time1+time2);
                String chooseTime = time1.substring(0, time1.length() - 2) + " " + time2;
                LogUtil.e(TAG,chooseTime);
                lessonTimeTextView.setText(chooseTime);
                lessonPlanTimePickerDialogFragment.dismiss();
            }
        });



        lessonTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lessonPlanTimePickerDialogFragment.isVisible()) {
                    lessonPlanTimePickerDialogFragment.dismiss();
                } else {
                    lessonPlanTimePickerDialogFragment.show(getChildFragmentManager(),"time");
                }
            }
        });

        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessonPlanListener.cancel();
            }
        });

        acceptTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lessonPlanListener.accept(lessonTimeTextView.getText().toString());
            }
        });


        return dialog;
    }
}
