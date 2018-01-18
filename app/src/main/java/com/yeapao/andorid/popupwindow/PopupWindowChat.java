package com.yeapao.andorid.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ScreenUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.homepage.station.lessonchat.LessonChatMessageAdapter;

import java.util.List;

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
    private ConstraintLayout constraintLayout;

    private LessonChatMessageAdapter lessonChatMessageAdapter;

    private PopupWindowChat popupWindowChat = this;




    public interface ChatClickListener {
        void reservationLessonClick();

        void sendClick(String content);
    }

    public void setChatClickListener(ChatClickListener listener) {
        if (listener != null) {
            chatClickListener = listener;
        }
    }

    public void initPopChat(Context context) {
        mContext = context;

        View view = LayoutInflater.from(context).inflate(R.layout.popup_chat, null);
        constraintLayout = (ConstraintLayout) view.findViewById(R.id.cl_popup_pack);
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
                if (sendTextView.getText().toString().equals("发送")) {
                    LogUtil.e(TAG,"send onclick");
                    if (chatEditText.getText().toString().equals("") || chatEditText.getText() == null) {
                        return;
                    }
                    chatClickListener.sendClick(chatEditText.getText().toString());
                    chatEditText.setText("");
                    chatEditText.setVisibility(View.GONE);
                    sendTextView.setText("回复");
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ScreenUtil.dpToPxInt(mContext, 300),
                            ScreenUtil.dpToPxInt(mContext, 230));
                    constraintLayout.setLayoutParams(layoutParams);
                } else {
                    LogUtil.e(TAG,"commient onclick");
                    chatEditText.setVisibility(View.VISIBLE);
                    sendTextView.setText("发送");
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ScreenUtil.dpToPxInt(mContext, 300),
                            ScreenUtil.dpToPxInt(mContext, 300));
                    constraintLayout.setLayoutParams(layoutParams);
                }

            }
        });
        reservationLessonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatClickListener.reservationLessonClick();
            }
        });
        //TODO 先暂时用 admin 账号
        lessonChatMessageAdapter = new LessonChatMessageAdapter(mContext, "28");
        chatContentList.setLayoutManager(new LinearLayoutManager(context));
        chatContentList.setAdapter(lessonChatMessageAdapter);


//        this.setAnimationStyle(R.style.popupWindowAnimation);
        this.setContentView(view);
//        this.setWidth(ScreenUtil.dpToPxInt(mContext, 270));
        this.setWidth(FrameLayout.LayoutParams.WRAP_CONTENT);
        this.setHeight(FrameLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(false);
//        软键盘不会挡着popupwindow
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


    }

}
