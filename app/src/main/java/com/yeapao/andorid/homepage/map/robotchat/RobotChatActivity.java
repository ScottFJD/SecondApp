package com.yeapao.andorid.homepage.map.robotchat;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/10/13.
 */

public class RobotChatActivity extends BaseActivity {


    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.et_chat_content)
    EditText etChatContent;
    @BindView(R.id.rv_chat_list)
    RecyclerView rvChatList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot_chat);
        ButterKnife.bind(this);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("小跑百科");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
