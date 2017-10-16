package com.yeapao.andorid.homepage.map.sportlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.util.GlideUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.model.SportListModel;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/10/13.
 */

public class SportListMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "SportListMessageAdapter";
    private Context mContext;
    private LayoutInflater inflater;

    private GlideUtil glideUtil = new GlideUtil();
    private SportListModel sportListModel = new SportListModel();

    private static final int TOP_TYPE = 1;
    private static final int LIST_TYPE = 2;
    private static final int MINE_TYPE = 3;

    public SportListMessageAdapter(Context context,SportListModel model) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        sportListModel = model;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TOP_TYPE;
        } else if (position == 1) {
            return MINE_TYPE;
        } else {
            return LIST_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TOP_TYPE) {
            return new TopViewHolder(inflater.inflate(R.layout.item_sport_top, parent, false));
        } else if (viewType == LIST_TYPE) {
            return new ListViewHolder(inflater.inflate(R.layout.item_sport_list, parent, false));
        } else {
            return new MineViewHolder(inflater.inflate(R.layout.item_sport_mine, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TopViewHolder) {
            ((TopViewHolder)holder).tvFirstName.setText(sportListModel.getData().getRankingList().get(0).getName());
            ((TopViewHolder)holder).tvFirstHour.setText(String.valueOf(sportListModel.getData().getRankingList().get(0).getDuration()));
            ((TopViewHolder)holder).tvSecondName.setText(sportListModel.getData().getRankingList().get(1).getName());
            ((TopViewHolder)holder).tvSecondHour.setText(String.valueOf(sportListModel.getData().getRankingList().get(1).getDuration()));
            ((TopViewHolder)holder).tvThirdName.setText(sportListModel.getData().getRankingList().get(2).getName());
            ((TopViewHolder)holder).tvThirdHour.setText(String.valueOf(sportListModel.getData().getRankingList().get(2).getDuration()));
            glideUtil.glideLoadingImage(mContext,sportListModel.getData().getRankingList().get(0).getHead(),R.drawable.y_you,((TopViewHolder) holder).civFirstHeader);
            glideUtil.glideLoadingImage(mContext,sportListModel.getData().getRankingList().get(1).getHead(),R.drawable.y_you,((TopViewHolder) holder).civSecondHeader);
            glideUtil.glideLoadingImage(mContext,sportListModel.getData().getRankingList().get(2).getHead(),R.drawable.y_you,((TopViewHolder) holder).civThirdHeader);
        } else if (holder instanceof MineViewHolder) {
            ((MineViewHolder) holder).tvList.setText(String.valueOf(sportListModel.getData().getRanking()));
            ((MineViewHolder) holder).tvHourNum.setText(String.valueOf(sportListModel.getData().getDuration()));
            ((MineViewHolder) holder).tvAccountName.setText(sportListModel.getData().getName());
        } else {
            if (position + 1 >= sportListModel.getData().getRankingList().size()) {

            } else {
                ((ListViewHolder)holder).tvAccountName.setText(sportListModel.getData().getRankingList().get(position+1).getName());
                ((ListViewHolder)holder).tvList.setText(String.valueOf(sportListModel.getData().getRankingList().get(position+1).getRanking()));
                ((ListViewHolder)holder).tvHourNum.setText(String.valueOf(sportListModel.getData().getRankingList().get(position+1).getDuration()));
                glideUtil.glideLoadingImage(mContext,sportListModel.getData().getRankingList().get(position+1).getHead(),R.drawable.y_you,((ListViewHolder) holder).ivHeader);
            }

        }
    }


    private String getHours(long date) {
        String hour = "";
        double hour1 = 0;
        DecimalFormat df = new DecimalFormat("0.0");
        hour1 = (double) date / 60;
        hour = df.format(hour1);
        return hour;
    }

    @Override
    public int getItemCount() {
        return sportListModel.getData().getRankingList().size()-1;
    }

    static class TopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_first_header)
        ImageView civFirstHeader;
        @BindView(R.id.tv_first_name)
        TextView tvFirstName;
        @BindView(R.id.tv_first_hour)
        TextView tvFirstHour;
        @BindView(R.id.civ_second_header)
        ImageView civSecondHeader;
        @BindView(R.id.civ_third_header)
        ImageView civThirdHeader;
        @BindView(R.id.tv_second_name)
        TextView tvSecondName;
        @BindView(R.id.tv_second_hour)
        TextView tvSecondHour;
        @BindView(R.id.tv_third_name)
        TextView tvThirdName;
        @BindView(R.id.tv_third_hour)
        TextView tvThirdHour;

        TopViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_list)
        TextView tvList;
        @BindView(R.id.iv_header)
        CircleImageView ivHeader;
        @BindView(R.id.tv_account_name)
        TextView tvAccountName;
        @BindView(R.id.tv_hour_num)
        TextView tvHourNum;

        ListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class MineViewHolder  extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_list)
        TextView tvList;
        @BindView(R.id.iv_header)
        CircleImageView ivHeader;
        @BindView(R.id.tv_account_name)
        TextView tvAccountName;
        @BindView(R.id.tv_hour_num)
        TextView tvHourNum;

        MineViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
