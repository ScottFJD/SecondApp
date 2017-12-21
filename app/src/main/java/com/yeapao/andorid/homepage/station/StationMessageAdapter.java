package com.yeapao.andorid.homepage.station;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.GlideUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.model.StationMainBannerModel;

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
    private StationMainBannerModel stationMainBannerModel;
    private GlideUtil glideUtil = new GlideUtil();
    private StationHeaderClickListener headerClickListener;

    public interface StationHeaderClickListener {
        void onHeaderClick(View view);
    }

    public void setStationHeaderClickListener(StationHeaderClickListener listener) {
        if (listener != null) {
            headerClickListener = listener;
        }
    }

    public void setOnRecyclerViewClickListener(OnRecyclerViewClickListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }


    public StationMessageAdapter(Context context, StationMainBannerModel model) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        stationMainBannerModel = model;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StationViewHolder(mInflater.inflate(R.layout.item_station, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        glideUtil.glideLoadingImage(mContext, stationMainBannerModel.getData().get(position).getUrl(),
                R.drawable.lesson_placeholder, ((StationViewHolder) holder).ivItemBg);
        if (position == 0) {
            ((StationViewHolder) holder).civUserHeader.setVisibility(View.GONE);
        } else if (position == 1) {

            if (stationMainBannerModel.getData().get(position).getHeadImage() != null) {
                ((StationViewHolder) holder).civUserHeader.setVisibility(View.VISIBLE);
                glideUtil.glideLoadingImage(mContext, stationMainBannerModel.getData().get(position).getHeadImage(),
                        R.drawable.y_you, ((StationViewHolder) holder).civUserHeader);
                ((StationViewHolder) holder).civUserHeader.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        headerClickListener.onHeaderClick(((StationViewHolder) holder).civUserHeader);
                    }
                });
            } else {
                ((StationViewHolder) holder).civUserHeader.setVisibility(View.GONE);
            }

        } else if (position == 2) {

            if (stationMainBannerModel.getData().get(position).getHeadImage() != null) {
                ((StationViewHolder) holder).civUserHeader.setVisibility(View.VISIBLE);
                glideUtil.glideLoadingImage(mContext, stationMainBannerModel.getData().get(position).getHeadImage(),
                        R.drawable.y_you, ((StationViewHolder) holder).civUserHeader);
                ((StationViewHolder) holder).civUserHeader.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        headerClickListener.onHeaderClick(((StationViewHolder) holder).civUserHeader);
                    }
                });
            } else {
                ((StationViewHolder) holder).civUserHeader.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public int getItemCount() {
        return stationMainBannerModel.getData().size();
    }

    static class StationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnRecyclerViewClickListener listener;

        @BindView(R.id.iv_item_bg)
        ImageView ivItemBg;
        @BindView(R.id.civ_user_header)
        CircleImageView civUserHeader;

        StationViewHolder(View view, OnRecyclerViewClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.OnItemClick(v, getLayoutPosition());
        }
    }
}
