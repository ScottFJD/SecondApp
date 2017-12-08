package com.yeapao.andorid.homepage.station.traininglesson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeapao.andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/12/8.
 */

public class TrainingLessonPriceMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;

    public TrainingLessonPriceMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public interface ChooseStatusListener {
        void chooseStatus(boolean status);
    }


    private ChooseStatusListener statusListener;

    public void setChooseStatusListener(ChooseStatusListener listener) {
        if (listener != null) {
            statusListener = listener;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_lesson_price, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (((ViewHolder) holder).ivChooseStatus.isChecked()) {
            statusListener.chooseStatus(true);
        } else {
            statusListener.chooseStatus(false);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_lesson_name)
        TextView tvLessonName;
        @BindView(R.id.tv_lesson_num)
        TextView tvLessonNum;
        @BindView(R.id.iv_choose_status)
        CheckBox ivChooseStatus;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
