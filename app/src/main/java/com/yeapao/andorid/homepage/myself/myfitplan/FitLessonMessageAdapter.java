package com.yeapao.andorid.homepage.myself.myfitplan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.GlideUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.model.FitPlanDetailModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 02/02/2018.
 */

public class FitLessonMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "FitLessonMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;
    GlideUtil glideUtil = new GlideUtil();
    private List<FitPlanDetailModel.ResultMapBean.WarmUpMediaListBean> detailList;
    private OnRecyclerViewClickListener onRecyclerViewClickListener;

    public void setOnRecyclerViewClickListener(OnRecyclerViewClickListener listener) {
        if (listener != null) {
            onRecyclerViewClickListener = listener;
        }
    }

    public FitLessonMessageAdapter(Context context,List<FitPlanDetailModel.ResultMapBean.WarmUpMediaListBean> list) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        detailList = list;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_fit_lesson, parent, false),onRecyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        glideUtil.glideLoadingImage(mContext,detailList.get(position).getImgString(),R.drawable.placeholder,
                ((ViewHolder)holder).ivVideo);
        ((ViewHolder)holder).tvFitLessonName.setText(detailList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private OnRecyclerViewClickListener listener;
        @BindView(R.id.iv_video)
        ImageView ivVideo;
        @BindView(R.id.tv_fit_lesson_name)
        TextView tvFitLessonName;
        @BindView(R.id.imageView152)
        ImageView imageView152;

        ViewHolder(View view, OnRecyclerViewClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            this.listener.OnItemClick(v,getLayoutPosition());
        }
    }
}
