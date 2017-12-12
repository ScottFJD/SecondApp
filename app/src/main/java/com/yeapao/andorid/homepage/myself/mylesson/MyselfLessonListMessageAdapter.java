package com.yeapao.andorid.homepage.myself.mylesson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.yeapao.andorid.R;
import com.yeapao.andorid.model.MyselfClassModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/12/8.
 */

public class MyselfLessonListMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "MyselfLessonListMessageAdapter";
    private Context mContext;
    private LayoutInflater inflater;
    private OnRecyclerViewClickListener mlistener;
    private ArrayList<MyselfClassModel.DataBean.MyClassListBean> myClassList = new ArrayList<>();

    public void setRecyclerViewClickListener(OnRecyclerViewClickListener listener) {
        if (listener != null) {
            mlistener = listener;
        }
    }

    public MyselfLessonListMessageAdapter(Context context,MyselfClassModel myselfClassModel) {
        myClassList.addAll(myselfClassModel.getData().getTodayIsClassList());
        myClassList.addAll(myselfClassModel.getData().getMyClassList());
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new  ViewHolder(inflater.inflate(R.layout.item_training_lesson_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {



        ((ViewHolder)holder).ivQrCodeLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.OnItemClick(v, position);
            }
        });
        ((ViewHolder) holder).tvDate1.setText(myClassList.get(position).getDate());
        ((ViewHolder) holder).tvDate2.setText(myClassList.get(position).getTime());
        ((ViewHolder) holder).tvLessonAddress.setText(myClassList.get(position).getShopAddress());
        ((ViewHolder) holder).tvLessonName.setText(myClassList.get(position).getSubjectName());
        if (myClassList.get(position).getStatus().equals("0")) {
            ((ViewHolder) holder).tvLessonStatus.setText("还未开课");
            ((ViewHolder) holder).tvLessonStatus.setBackgroundResource(R.drawable.lesson_status_shape_3);
        } else if (myClassList.get(position).getStatus().equals("1")) {
            ((ViewHolder) holder).tvLessonStatus.setText("正在上课");
            ((ViewHolder) holder).tvLessonStatus.setBackgroundResource(R.drawable.lesson_status_shape_1);
        } else if (myClassList.get(position).getStatus().equals("2")) {
            ((ViewHolder) holder).tvLessonStatus.setText("即将开课");
            ((ViewHolder) holder).tvLessonStatus.setBackgroundResource(R.drawable.lesson_status_shape_2);
        } else {
            ((ViewHolder) holder).tvLessonStatus.setText("已结束");
            ((ViewHolder) holder).tvLessonStatus.setBackgroundResource(R.drawable.lesson_status_shape_4);
        }

    }

    @Override
    public int getItemCount() {
        return myClassList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_date_1)
        TextView tvDate1;
        @BindView(R.id.tv_date_2)
        TextView tvDate2;
        @BindView(R.id.tv_lesson_name)
        TextView tvLessonName;
        @BindView(R.id.tv_lesson_address)
        TextView tvLessonAddress;
        @BindView(R.id.tv_lesson_status)
        TextView tvLessonStatus;
        @BindView(R.id.iv_qr_code_lesson)
        ImageView ivQrCodeLesson;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
