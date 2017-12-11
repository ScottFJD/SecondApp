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
import com.scottfu.sflibrary.util.GlideUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.model.EmployeeListModel;

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
    private EmployeeListModel employeeListModel;
    private GlideUtil glideUtil = new GlideUtil();


    public void setOnRecyclerViewClickListener(OnRecyclerViewClickListener listener) {
        if (listener != null) {
            onRecyclerViewClickListener = listener;
        }
    }


    public TrainingLessonMessageAdapter(Context context, EmployeeListModel employeeListModel) {
        this.employeeListModel = employeeListModel;
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new ViewHolder(inflater.inflate(R.layout.item_training_lesson, parent,
                false),onRecyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        glideUtil.glideLoadingImage(mContext,employeeListModel.getData().get(position).getHeadImage(),
                R.drawable.y_you,((ViewHolder)holder).civTeachIcon);
        ((ViewHolder) holder).tvTeachName.setText(employeeListModel.getData().get(position).getEmployeeName()+
        "/"+employeeListModel.getData().get(position).getJobTitle());
        ((ViewHolder) holder).tvTeachTitleOne.setText(employeeListModel.getData().get(position).getInformation().get(0));
        ((ViewHolder) holder).tvTeachTitleTwo.setText(employeeListModel.getData().get(position).getInformation().get(1));
        ((ViewHolder) holder).tvTeachPeopleNum.setText("已服务" + String.valueOf(employeeListModel.getData().get(position).getHighServerTime() )+ "人");

        if (position == 0) {
            ((ViewHolder) holder).rlItemTrainingLesson.setBackgroundColor(mContext.getResources().getColor(R.color.coach_one));
        } else if (position == 1) {
            ((ViewHolder) holder).rlItemTrainingLesson.setBackgroundColor(mContext.getResources().getColor(R.color.coach_two));
        } else if (position == 2) {
            ((ViewHolder) holder).rlItemTrainingLesson.setBackgroundColor(mContext.getResources().getColor(R.color.coach_three));
        } else {
            ((ViewHolder) holder).rlItemTrainingLesson.setBackgroundColor(mContext.getResources().getColor(R.color.bg_white));
        }
    }

    @Override
    public int getItemCount() {
        return employeeListModel.getData().size();
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
