package com.yeapao.andorid.homepage.station.dynamiclesson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.yeapao.andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/11/22.
 */

public class DynamicLessonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "DynamicLessonAdapter";

    private Context mContext;
    private LayoutInflater inflater;

    private OnRecyclerViewClickListener mListener;

    public void setOnRecyclerViewClickListener(OnRecyclerViewClickListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }

    public DynamicLessonAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_dynamic_lesson, parent, false),mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnRecyclerViewClickListener listener;

        @BindView(R.id.iv_dynamic_background)
        ImageView ivDynamicBackground;
        @BindView(R.id.tv_dynamic_lesson_reservation)
        TextView tvDynamicLessonReservation;
        @BindView(R.id.tv_dynamic_lesson_status)
        TextView tvDynamicLessonStatus;
        @BindView(R.id.tv_dynamic_lesson_address)
        TextView tvDynamicLessonAddress;
        @BindView(R.id.tv_dynamic_coach)
        TextView tvDynamicCoach;
        @BindView(R.id.civ_dynamic_coach_portrait)
        CircleImageView civDynamicCoachPortrait;

        ViewHolder(View view,OnRecyclerViewClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            listener.OnItemClick(v,getLayoutPosition());
        }
    }
}
