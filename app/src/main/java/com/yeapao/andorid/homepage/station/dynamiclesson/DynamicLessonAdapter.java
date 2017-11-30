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
import com.scottfu.sflibrary.util.GlideUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.model.DynamicLessonListModel;

import javax.microedition.khronos.opengles.GL;

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
    private DynamicLessonListModel dynamicLessonListModel;
    private GlideUtil glideUtil = new GlideUtil();

    public void setOnRecyclerViewClickListener(OnRecyclerViewClickListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }

    public DynamicLessonAdapter(Context context, DynamicLessonListModel model) {
        dynamicLessonListModel = model;
        mContext = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_dynamic_lesson, parent, false),mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        glideUtil.glideLoadingImage(mContext, dynamicLessonListModel.getData().get(position).getUrl(),
                R.drawable.lesson_one, ((ViewHolder) holder).ivDynamicBackground);
        glideUtil.glideLoadingImage(mContext, dynamicLessonListModel.getData().get(position).getHeadImage(),
                R.drawable.y_you, ((ViewHolder) holder).civDynamicCoachPortrait);
        ((ViewHolder) holder).tvDynamicCoach.setText(dynamicLessonListModel.getData().get(position).getEmployeeName()
                + "·" + dynamicLessonListModel.getData().get(position).getSubjectName());
        ((ViewHolder) holder).tvDynamicLessonAddress.setText(dynamicLessonListModel.getData().get(position).getStartTime()
                + "-" + dynamicLessonListModel.getData().get(position).getEndTime() + "/"
                + dynamicLessonListModel.getData().get(position).getShopAddress());
        if (dynamicLessonListModel.getData().get(position).getMyCalendar() == 0) {
            ((ViewHolder) holder).tvDynamicLessonReservation.setText("预约");
        } else {
            ((ViewHolder) holder).tvDynamicLessonReservation.setText("已预约");
        }
        switch (dynamicLessonListModel.getData().get(position).getStatus()) {
            case 1:
                ((ViewHolder) holder).tvDynamicLessonStatus.setText("可预约");
                ((ViewHolder) holder).tvDynamicLessonReservation.setBackground(mContext.getResources().getDrawable(R.drawable.yellow_2_s_shape));
                break;
            case 2:
                ((ViewHolder) holder).tvDynamicLessonStatus.setText("已满");
                ((ViewHolder) holder).tvDynamicLessonReservation.setBackground(mContext.getResources().getDrawable(R.drawable.yellow_2_n_shape));
                break;
            case 3:
                ((ViewHolder) holder).tvDynamicLessonStatus.setText("结束");
                ((ViewHolder) holder).tvDynamicLessonReservation.setText("结束");
                ((ViewHolder) holder).tvDynamicLessonReservation.setBackground(mContext.getResources().getDrawable(R.drawable.yellow_2_n_shape));
                break;
            case 4:
                ((ViewHolder) holder).tvDynamicLessonStatus.setText("紧张");
                ((ViewHolder) holder).tvDynamicLessonReservation.setBackground(mContext.getResources().getDrawable(R.drawable.yellow_2_s_shape));
                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return dynamicLessonListModel.getData().size();
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
