package com.yeapao.andorid.homepage.myself.iscoach;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scottfu.sflibrary.customview.CircleImageView;
import com.yeapao.andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/12/26.
 */

public class StationPeopleChatListMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "StationPeopleChatListMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;

    public StationPeopleChatListMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_chat_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cir_header)
        CircleImageView cirHeader;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_chat_content)
        TextView tvChatContent;
        @BindView(R.id.tv_people_title)
        TextView tvPeopleTitle;
        @BindView(R.id.tv_people_time)
        TextView tvPeopleTime;
        @BindView(R.id.tv_chat_time)
        TextView tvChatTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
