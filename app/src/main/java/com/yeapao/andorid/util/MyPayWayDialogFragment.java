package com.yeapao.andorid.util;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeapao.andorid.R;

/**
 * Created by fujindong on 2017/11/24.
 */

public class MyPayWayDialogFragment extends DialogFragment {

    private ImageView weChatPayWay;
    private ImageView aliPayWay;
    private ImageView closeWindow;
    private TextView gotoPay;
    private TextView price;

    private int payWayStatus = 0;

    private PayWayOnClickListener mListener;

    public void setPayWayOnClickListener(PayWayOnClickListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }

    public static MyPayWayDialogFragment newInstance(String price) {
        MyPayWayDialogFragment myPayWayDialogFragment = new MyPayWayDialogFragment();
        Bundle args = new Bundle();
        args.putString("price", price);
        myPayWayDialogFragment.setArguments(args);
        return myPayWayDialogFragment;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String lessonPrice = getArguments().getString("price");

        Dialog dialog = new Dialog(getActivity(), R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_pay_way);
        dialog.setCanceledOnTouchOutside(false);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        weChatPayWay = (ImageView) dialog.findViewById(R.id.iv_wechat_pay);
        aliPayWay = (ImageView) dialog.findViewById(R.id.iv_ali_pay);
        closeWindow = (ImageView) dialog.findViewById(R.id.iv_close_window);
        gotoPay = (TextView) dialog.findViewById(R.id.tv_goto_pay);
        price = (TextView) dialog.findViewById(R.id.tv_price);

        price.setText(getResources().getString(R.string.RMB) + lessonPrice);

        weChatPayWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (payWayStatus == 0) {
                    payWayStatus = 1;
                    mListener.onPayWay(1);
                    weChatPayWay.setImageResource(R.drawable.buy_way_s);
                    aliPayWay.setImageResource(R.drawable.buy_way_n);
                } else if (payWayStatus == 1) {
                    payWayStatus = 0;
                    weChatPayWay.setImageResource(R.drawable.buy_way_n);
                } else if (payWayStatus == 2) {
                    payWayStatus = 1;
                    mListener.onPayWay(1);
                    weChatPayWay.setImageResource(R.drawable.buy_way_s);
                    aliPayWay.setImageResource(R.drawable.buy_way_n);
                }
            }
        });
        aliPayWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (payWayStatus == 0) {
                    payWayStatus = 2;
                    mListener.onPayWay(2);
                    weChatPayWay.setImageResource(R.drawable.buy_way_n);
                    aliPayWay.setImageResource(R.drawable.buy_way_s);
                } else if (payWayStatus == 2) {
                    payWayStatus = 0;
                    aliPayWay.setImageResource(R.drawable.buy_way_n);
                } else if (payWayStatus == 1) {
                    payWayStatus = 2;
                    mListener.onPayWay(2);
                    weChatPayWay.setImageResource(R.drawable.buy_way_n);
                    aliPayWay.setImageResource(R.drawable.buy_way_s);
                }
            }
        });

        closeWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCloseWindow();
            }
        });
        gotoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoPay();
            }
        });

        return dialog;


    }
}
