package com.yeapao.andorid.homepage.map.robotchat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.util.GlideUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.util.GlobalDataYepao;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/10/16.
 */

public class DiTingChatMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "DiTingChatMessageAdapter";
    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<DiTingChatBean> diTingChatBean = new ArrayList<>();
    GlideUtil glideUtil = new GlideUtil();

    private static final int TOP_TYPE = 0;
    private static final int RECEIVER_TYPE = 1;
    private static final int SEND_TYPE = 2;


    public DiTingChatMessageAdapter(Context context, ArrayList<DiTingChatBean> chatBeen) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        diTingChatBean = chatBeen;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TOP_TYPE;
        } else {
            if (diTingChatBean.get(position-1).getFrom().equals("1")) {
                return RECEIVER_TYPE;
            } else {
                return SEND_TYPE;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case TOP_TYPE:
                return new TopViewHolder(inflater.inflate(R.layout.item_chat_top, parent, false));
            case RECEIVER_TYPE:
                return new ReceiverViewHolder(inflater.inflate(R.layout.item_chat_receiver, parent, false));
            case SEND_TYPE:
                return new SendViewHolder(inflater.inflate(R.layout.item_chat_send, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TopViewHolder) {

        } else if (holder instanceof ReceiverViewHolder) {
            ((ReceiverViewHolder) holder).tvReceiverContent.setText(diTingChatBean.get(position-1).getContent());
        } else {
            ((SendViewHolder)holder).tvSendContent.setText(diTingChatBean.get(position-1).getContent());
            glideUtil.glideLoadingImage(mContext, ConstantYeaPao.HOST+GlobalDataYepao.getUser(mContext).getHead(),R.drawable.y_you,((SendViewHolder) holder).civXiaopaoHead);
        }
    }

    @Override
    public int getItemCount() {
        return diTingChatBean.size()+1;
    }


    class ReceiverViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_receiver_content)
        TextView tvReceiverContent;

        ReceiverViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    class TopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_xiaopao_hint)
        TextView tvXiaopaoHint;

        TopViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    static class SendViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_send_head)
        CircleImageView civXiaopaoHead;
        @BindView(R.id.tv_send_content)
        TextView tvSendContent;

        SendViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
