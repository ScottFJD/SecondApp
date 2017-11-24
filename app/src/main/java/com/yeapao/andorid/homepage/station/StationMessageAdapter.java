package com.yeapao.andorid.homepage.station;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.yeapao.andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/11/21.
 */

public class StationMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "StationMessageAdapter";

    private Context mContext;
    private LayoutInflater mInflater;
    private OnRecyclerViewClickListener mListener;

    public void setOnRecyclerViewClickListener(OnRecyclerViewClickListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }


    public StationMessageAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StationViewHolder(mInflater.inflate(R.layout.item_station, parent, false),mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ((StationViewHolder) holder).ivItemBg.setImageResource(R.drawable.dynamic_bg);
            ((StationViewHolder) holder).civUserHeader.setVisibility(View.GONE);
        } else if (position == 1) {
            ((StationViewHolder)holder).ivItemBg.setImageResource(R.drawable.high_bg);
            ((StationViewHolder) holder).civUserHeader.setVisibility(View.VISIBLE);
        } else if (position == 2) {
            ((StationViewHolder) holder).ivItemBg.setImageResource(R.drawable.rehabilitate_bg);
            ((StationViewHolder) holder).civUserHeader.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class StationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private OnRecyclerViewClickListener listener;

        @BindView(R.id.iv_item_bg)
        ImageView ivItemBg;
        @BindView(R.id.civ_user_header)
        CircleImageView civUserHeader;

        StationViewHolder(View view,OnRecyclerViewClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.OnItemClick(v,getLayoutPosition());
        }
    }
}
