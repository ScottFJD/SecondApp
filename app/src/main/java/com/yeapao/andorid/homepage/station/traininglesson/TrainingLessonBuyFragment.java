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

import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.model.EmployeeDetailModel;
import com.yeapao.andorid.util.MyPayWayDialogFragment;

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

    private boolean mProtocolStatus = false;

    private EmployeeDetailModel employeeDetailModel;



    public static TrainingLessonBuyFragment newInstance(EmployeeDetailModel employeeDetailModel) {
        TrainingLessonBuyFragment mTrainingLessonBuyFragment = new TrainingLessonBuyFragment();
        Bundle args = new Bundle();
        args.putSerializable("employeeDetail",employeeDetailModel);
        mTrainingLessonBuyFragment.setArguments(args);
        return mTrainingLessonBuyFragment;
    }

    private TrainingLessonPriceMessageAdapter trainingLessonPriceMessageAdapter;

    public interface TrainingLessonBuyListener {
        void cancelClick();

        void userProtocol();

        void gotoPay();

        void chooseLesson(int position);

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

        employeeDetailModel = (EmployeeDetailModel) getArguments().getSerializable("employeeDetail");

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
        trainingLessonPriceMessageAdapter = new TrainingLessonPriceMessageAdapter(getContext(),employeeDetailModel);
        lessonpriceList.setAdapter(trainingLessonPriceMessageAdapter);
        trainingLessonPriceMessageAdapter.setChooseStatusListener(new TrainingLessonPriceMessageAdapter.ChooseStatusListener() {
            @Override
            public void chooseStatus(boolean status,int position) {
                trainingLessonPriceMessageAdapter.notifyDataSetChanged();
                if (status) {
                    lessonBuyListener.chooseLesson(position);
                } else {

                }


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
                if (mProtocolStatus) {
                    mProtocolStatus = false;
                    userProtocolStatus.setImageResource(R.drawable.agreement_no_selected);
                } else {
                    mProtocolStatus = true;
                    userProtocolStatus.setImageResource(R.drawable.agreement_selected);
                }
                break;
            case R.id.tv_user_protocol:
                lessonBuyListener.userProtocol();
                break;
            case R.id.tv_pay:
                if (mProtocolStatus) {

                    lessonBuyListener.gotoPay();
                } else {
                    ToastManager.showToast(getContext(),"请阅读并勾选用户协议");
                }
                break;
            default:
                break;

        }
    }
}
