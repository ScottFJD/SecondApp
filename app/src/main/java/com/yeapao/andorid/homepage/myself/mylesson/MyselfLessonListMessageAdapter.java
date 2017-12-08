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


    public void setRecyclerViewClickListener(OnRecyclerViewClickListener listener) {
        if (listener != null) {
            mlistener = listener;
        }
    }

    public MyselfLessonListMessageAdapter(Context context) {
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
    }

    @Override
    public int getItemCount() {
        return 10;
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
