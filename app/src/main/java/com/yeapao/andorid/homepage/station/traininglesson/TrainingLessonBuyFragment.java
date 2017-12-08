package com.yeapao.andorid.homepage.station.traininglesson;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeapao.andorid.R;

/**
 * Created by fujindong on 2017/12/7.
 */

public class TrainingLessonBuyFragment extends DialogFragment implements View.OnClickListener {


    private ImageView cancelView;
    private TextView lessonName;
    private RecyclerView lessonpriceList;
    private ImageView userProtocolStatus;
    private TextView userProtocol;
    private TextView gotoPay;

    private TrainingLessonPriceMessageAdapter trainingLessonPriceMessageAdapter;

    public interface TrainingLessonBuyListener {
        void cancelClick();

        void userProtocol();

        void gotoPay();
    }

    private TrainingLessonBuyListener lessonBuyListener;

    public void setTrainingLessonListener(TrainingLessonBuyListener listener) {
        if (listener != null) {
            lessonBuyListener = listener;
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity(), R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogfragment_training_lesson_buy);
        dialog.setCanceledOnTouchOutside(false);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        cancelView = (ImageView) dialog.findViewById(R.id.iv_cancel);
        lessonName = (TextView) dialog.findViewById(R.id.tv_lesson_name);
        lessonpriceList = (RecyclerView) dialog.findViewById(R.id.rv_lesson_price);
        userProtocolStatus = (ImageView) dialog.findViewById(R.id.iv_agree_status);
        userProtocol = (TextView) dialog.findViewById(R.id.tv_user_protocol);
        gotoPay = (TextView) dialog.findViewById(R.id.tv_pay);

        lessonpriceList.setLayoutManager(new LinearLayoutManager(getContext()));
        trainingLessonPriceMessageAdapter = new TrainingLessonPriceMessageAdapter(getContext());
        lessonpriceList.setAdapter(trainingLessonPriceMessageAdapter);
        trainingLessonPriceMessageAdapter.setChooseStatusListener(new TrainingLessonPriceMessageAdapter.ChooseStatusListener() {
            @Override
            public void chooseStatus(boolean status) {

            }
        });

        cancelView.setOnClickListener(this);
        userProtocolStatus.setOnClickListener(this);
        userProtocol.setOnClickListener(this);
        gotoPay.setOnClickListener(this);
        return dialog;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_cancel:
                lessonBuyListener.cancelClick();
                break;
            case R.id.iv_agree_status:

                break;
            case R.id.tv_user_protocol:
                lessonBuyListener.userProtocol();
                break;
            case R.id.tv_pay:
                    lessonBuyListener.gotoPay();
                break;
            default:
                break;

        }
    }
}
