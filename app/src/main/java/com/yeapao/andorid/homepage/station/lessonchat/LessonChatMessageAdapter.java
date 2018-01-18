package com.yeapao.andorid.homepage.station.lessonchat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.util.GlobalDataYepao;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 11/01/2018.
 */

public class LessonChatMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "LessonChatMessageAdapter";
    private static final String FROM = "from";
    private static final String TO = "to";
    private Context mContext;
    private LayoutInflater inflater;

    private EMConversation conversation;

    EMMessage[] messages = null;
    /**
     *
     * @param context
     * @param username
     */
    public LessonChatMessageAdapter(Context context,String username) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        this.conversation = EMClient.getInstance().chatManager().
                getConversation(username, EaseCommonUtils.getConversationType(1), true);
        refresh();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 2) {
            return new LeftChatViewHolder(inflater.inflate(R.layout.item_chat_text, parent, false));
        } else {
            return new RightChatViewHolder(inflater.inflate(R.layout.item_chat_text_right, parent, false));
        }
    }

    public EMMessage getItem(int position) {
        if (messages != null && position < messages.length) {
            return messages[position];
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        EMMessage message = getItem(position);
        if (message.getFrom().equals(GlobalDataYepao.getUser(mContext).getId())) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EMMessage message = getItem(position);

        LogUtil.e(TAG, "from  "+message.getFrom());
        LogUtil.e(TAG,"to   "+message.getTo());
        EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
        Spannable span = EaseSmileUtils.getSmiledText(mContext, txtBody.getMessage());

        if (message.getFrom().equals(GlobalDataYepao.getUser(mContext).getId())) {
            ((RightChatViewHolder)holder).tvChatContentRight.setText(span,TextView.BufferType.SPANNABLE);
            ((RightChatViewHolder) holder).tvChatTimeRight.setText(com.hyphenate.util.DateUtils.getTimestampString(new Date(message.getMsgTime())));
        } else {
            ((LeftChatViewHolder)holder).tvChatContent.setText(span, TextView.BufferType.SPANNABLE);
            ((LeftChatViewHolder) holder).tvChatTime.setText(com.hyphenate.util.DateUtils.getTimestampString(new Date(message.getMsgTime())));
        }



    }

    public void refresh() {
        java.util.List<EMMessage> var = conversation.getAllMessages();
        messages = var.toArray(new EMMessage[var.size()]);
        conversation.markAllMessagesAsRead();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return messages.length;
    }

    static class LeftChatViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_chat_content)
        TextView tvChatContent;
        @BindView(R.id.tv_chat_time)
        TextView tvChatTime;

        LeftChatViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class RightChatViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_chat_content_right)
        TextView tvChatContentRight;
        @BindView(R.id.tv_chat_time_right)
        TextView tvChatTimeRight;

        RightChatViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
