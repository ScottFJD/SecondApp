package com.yeapao.andorid.util;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yeapao.andorid.R;

/**
 * Created by fujindong on 2017/12/6.
 */

public class ComplaintDialogFragment extends DialogFragment implements View.OnClickListener {
    private static final String TAG  = "ComplaintDialogFragment";

    private TextView complaintTextView1;
    private TextView complaintTextView2;
    private TextView complaintTextView3;
    private TextView complaintTextView4;
    private TextView complaintTextView5;
    private TextView complaintTextView6;


    public interface ComplaintDialogClickListener {
        void complaint1();
        void complaint2();
        void complaint3();
        void complaint4();
        void complaint5();
        void complaintCancel();
    }

    private ComplaintDialogClickListener mListener;

    public void setComplaintOnClickListener(ComplaintDialogClickListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_complaint);
        dialog.setCanceledOnTouchOutside(false);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        complaintTextView1 = (TextView) dialog.findViewById(R.id.tv_complaint_1);
        complaintTextView2 = (TextView) dialog.findViewById(R.id.tv_complaint_2);
        complaintTextView3 = (TextView) dialog.findViewById(R.id.tv_complaint_3);
        complaintTextView4 = (TextView) dialog.findViewById(R.id.tv_complaint_4);
        complaintTextView5 = (TextView) dialog.findViewById(R.id.tv_complaint_5);
        complaintTextView6 = (TextView) dialog.findViewById(R.id.tv_complaint_cancel);

        complaintTextView1.setOnClickListener(this);
        complaintTextView2.setOnClickListener(this);
        complaintTextView3.setOnClickListener(this);
        complaintTextView4.setOnClickListener(this);
        complaintTextView5.setOnClickListener(this);
        complaintTextView6.setOnClickListener(this);


        return dialog;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_complaint_1:
                mListener.complaint1();
                break;
            case R.id.tv_complaint_2:
                mListener.complaint2();
                break;
            case R.id.tv_complaint_3:
                mListener.complaint3();
                break;
            case R.id.tv_complaint_4:
                mListener.complaint4();
                break;
            case R.id.tv_complaint_5:
                mListener.complaint5();
                break;
            case R.id.tv_complaint_cancel:
                mListener.complaintCancel();
                break;
            default:
                break;

        }
    }
}
