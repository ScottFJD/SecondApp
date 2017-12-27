package com.yeapao.andorid.homepage.myself.iscoach;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeapao.andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/12/25.
 */

public class StationLessonMessgeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "StationLessonMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;

    public StationLessonMessgeAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_station_lesson, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_lesson_time)
        TextView tvLessonTime;
        @BindView(R.id.tv_lesson_name)
        TextView tvLessonName;
        @BindView(R.id.tv_lesson_people)
        TextView tvLessonPeople;
        @BindView(R.id.tv_lesson_address)
        TextView tvLessonAddress;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
