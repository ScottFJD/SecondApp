package com.yeapao.andorid.homepage.myself.myfitplan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeapao.andorid.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 05/02/2018.
 */

public class FitTitlePartsMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "FitTitlePartsMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<String> fitTitles = new ArrayList<>();

    public FitTitlePartsMessageAdapter(Context context, ArrayList<String> titleList) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        fitTitles = titleList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_fit_title, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).tvItemFitParts.setText(fitTitles.get(position));
    }

    @Override
    public int getItemCount() {
        return fitTitles.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_fit_parts)
        TextView tvItemFitParts;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }



}
