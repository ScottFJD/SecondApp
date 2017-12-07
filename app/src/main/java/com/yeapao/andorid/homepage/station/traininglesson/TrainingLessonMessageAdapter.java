package com.yeapao.andorid.homepage.station.traininglesson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.yeapao.andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/11/27.
 */

public class TrainingLessonMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "TrainingLessonMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;
    private OnRecyclerViewClickListener onRecyclerViewClickListener;


    public void setOnRecyclerViewClickListener(OnRecyclerViewClickListener listener) {
        if (listener != null) {
            onRecyclerViewClickListener = listener;
        }
    }


    public TrainingLessonMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new ViewHolder(inflater.inflate(R.layout.item_training_lesson, parent, false),onRecyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private OnRecyclerViewClickListener listener;

        @BindView(R.id.civ_teach_icon)
        CircleImageView civTeachIcon;
        @BindView(R.id.tv_teach_name)
        TextView tvTeachName;
        @BindView(R.id.tv_teach_title_one)
        TextView tvTeachTitleOne;
        @BindView(R.id.tv_teach_title_two)
        TextView tvTeachTitleTwo;
        @BindView(R.id.tv_teach_people_num)
        TextView tvTeachPeopleNum;
        @BindView(R.id.rl_item_training_lesson)
        RelativeLayout rlItemTrainingLesson;

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
