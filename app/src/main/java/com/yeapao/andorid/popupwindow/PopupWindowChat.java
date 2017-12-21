package com.yeapao.andorid.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.scottfu.sflibrary.util.ScreenUtil;
import com.yeapao.andorid.R;

/**
 * Created by fujindong on 2017/12/21.
 */

public class PopupWindowChat extends PopupWindow {
    private static final String TAG = "PopupWindowChat";

    private Context mContext;
    private TextView coachNameTextView;
    private ImageView cancelImageView;
    private RecyclerView chatContentList;
    private TextView reservationLessonTextView;
    private TextView lessonStatusTextView;
    private TextView sendTextView;
    private EditText chatEditText;
    private ChatClickListener chatClickListener;

    public interface ChatClickListener {
        void reservationLessonClick();

        void sendClick();
    }

    public void setChatClickListener(ChatClickListener listener) {
        if (listener != null) {
            chatClickListener = listener;
        }
    }

    public void initPopChat(Context context) {
        mContext = context;

        View view = LayoutInflater.from(context).inflate(R.layout.popup_chat, null);
        coachNameTextView = (TextView) view.findViewById(R.id.tv_coach_name);
        cancelImageView = (ImageView) view.findViewById(R.id.iv_chat_cancel);
        chatContentList = (RecyclerView) view.findViewById(R.id.rv_chat);
        reservationLessonTextView = (TextView) view.findViewById(R.id.tv_reservation_lesson);
        lessonStatusTextView = (TextView) view.findViewById(R.id.tv_lesson_status);
        sendTextView = (TextView) view.findViewById(R.id.tv_chat_send);
        chatEditText = (EditText) view.findViewById(R.id.et_chat_content);
        cancelImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        sendTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatClickListener.sendClick();
            }
        });
        reservationLessonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatClickListener.reservationLessonClick();
            }
        });


//        this.setAnimationStyle(R.style.popupWindowAnimation);
        this.setContentView(view);
        this.setWidth(ScreenUtil.dpToPxInt(mContext, 270));
        this.setHeight(ScreenUtil.dpToPxInt(mContext, 300));
        this.setFocusable(true);
        this.setOutsideTouchable(false);
//        软键盘不会挡着popupwindow
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


    }



}
