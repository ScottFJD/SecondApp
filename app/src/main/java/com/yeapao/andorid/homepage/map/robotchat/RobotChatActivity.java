package com.yeapao.andorid.homepage.map.robotchat;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.DiTingDataModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/10/13.
 */

public class RobotChatActivity extends BaseActivity {

    private static final String TAG = "RobotChatActivity";

    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.et_chat_content)
    EditText etChatContent;
    @BindView(R.id.rv_chat_list)
    RecyclerView rvChatList;

    private static ArrayList<DiTingChatBean> diTingChatBeanArrayList = new ArrayList<>();
    private DiTingChatMessageAdapter messageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot_chat);
        ButterKnife.bind(this);
        initTopBar();
        initData();
    }

    private void initData() {
        if (diTingChatBeanArrayList.size() == 0) {
            DiTingChatBean chat = new DiTingChatBean();
            chat.setFrom("1");
            chat.setContent("我是小跑，有什么能为您服务的呢？");
            diTingChatBeanArrayList.add(chat);
        }
        initView();
    }

    private void initView() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvChatList.setLayoutManager(llm);

        showResult();
    }

    private void showResult() {
        messageAdapter = new DiTingChatMessageAdapter(getContext(), diTingChatBeanArrayList);
        rvChatList.setAdapter(messageAdapter);
        rvChatList.scrollToPosition(rvChatList.getAdapter().getItemCount()-1);
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


    @OnClick(R.id.tv_send)
    void sendMessage(View view) {
        String content = etChatContent.getText().toString();
        if (content == null || content.equals("")) {
            ToastManager.showToast(getContext(),"提问内容不能为空");
            return;
        }
        DiTingChatBean chat = new DiTingChatBean();
        chat.setFrom("2");
        chat.setContent(content);
        diTingChatBeanArrayList.add(chat);
        messageAdapter.notifyDataSetChanged();
        rvChatList.scrollToPosition(rvChatList.getAdapter().getItemCount()-1);
        etChatContent.setText("");

        getNetWork(content);
    }


            private void getNetWork(String problem) {
                    LogUtil.e(TAG,problem);
                    subscription = Network.getYeapaoApi()
                            .requestDiTingAnswer(problem, GlobalDataYepao.getUser(getContext()).getId())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe( modelObserver );
                }

                  Observer<DiTingDataModel> modelObserver = new Observer<DiTingDataModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(DiTingDataModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            DiTingChatBean chat = new DiTingChatBean();
                            chat.setFrom("1");
                            chat.setContent(model.getData().getAnswer());
                            diTingChatBeanArrayList.add(chat);
                            messageAdapter.notifyDataSetChanged();
                            rvChatList.scrollToPosition(rvChatList.getAdapter().getItemCount()-1);

                        }
                    }
                };

}
