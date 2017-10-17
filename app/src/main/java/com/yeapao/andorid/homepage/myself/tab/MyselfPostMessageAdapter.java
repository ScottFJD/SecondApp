package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.recyclerview.SpaceItemDecoration;
import com.scottfu.sflibrary.util.ScreenUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.homepage.circle.CircleMessageAdapter;
import com.yeapao.andorid.homepage.circle.ImageRecyclerAdapter;
import com.yeapao.andorid.model.MyselfPostModel;
import com.yeapao.andorid.util.CircleDateUtils;
import com.yeapao.andorid.util.SpannableTextUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/23.
 */

public class MyselfPostMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static Context mContext;
    private LayoutInflater inflater;
    private OnRecyclerViewClickListener mListener;
    private MyselfPostModel myselfPostModel = new MyselfPostModel();
    private boolean footerFlag = false;
    private static final int POST_TYPE = 1;
    private static final int FOOTER_TYPE = 2;

    public MyselfPostMessageAdapter(Context context, MyselfPostModel model) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        myselfPostModel = model;
        if (model.getData().getCommunityList().size() < 10) {
            footerFlag = true;
        }
    }

    public void loadNothing() {
//        TODO 取消尾部加载tab
        footerFlag = true;
        notifyDataSetChanged();
    }

    public void loadMore(MyselfPostModel model) {
        myselfPostModel.getData().getCommunityList().addAll(model.getData().getCommunityList());
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == POST_TYPE) {
            return new ViewHolder(inflater.inflate(R.layout.item_my_post_card, parent, false), mListener);
        } else {
            return new FooterViewHolder(inflater.inflate(R.layout.list_footer, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder) {

            if (position == 0) {
                ((ViewHolder) holder).ivTop.setVisibility(View.GONE);
            } else {
                ((ViewHolder) holder).ivTop.setVisibility(View.VISIBLE);
            }
            ((ViewHolder)holder).tvTine.setText(CircleDateUtils.getCircleDate(myselfPostModel.getData().getCommunityList().get(position).getCreateTime()));

            if (myselfPostModel.getData().getCommunityList().get(position).getContent() == null || myselfPostModel.getData().getCommunityList().get(position).getContent().equals("")) {
                ((ViewHolder) holder).tvPostContent.setVisibility(View.GONE);

            } else {
                if (myselfPostModel.getData().getCommunityList().get(position).getType().equals("community")) {
                    ((ViewHolder) holder).tvPostContent.setText(myselfPostModel.getData().getCommunityList().get(position).getContent());
                } else if (myselfPostModel.getData().getCommunityList().get(position).getType().equals("breakfast")) {
                    String breakfast = "#早餐打卡#";
                    ((ViewHolder) holder).tvPostContent.setText(SpannableTextUtils.setTextTwoColor(breakfast,
                            myselfPostModel.getData().getCommunityList().get(position).getContent()));
                } else if (myselfPostModel.getData().getCommunityList().get(position).getType().equals("lunch")) {
                    String lunch = "#午餐打卡#";
                    ((ViewHolder) holder).tvPostContent.setText(SpannableTextUtils.setTextTwoColor(lunch,
                            myselfPostModel.getData().getCommunityList().get(position).getContent()));
                } else {
                    String dinner = "#晚餐打卡#";
                    ((ViewHolder) holder).tvPostContent.setText(SpannableTextUtils.setTextTwoColor(dinner,
                            myselfPostModel.getData().getCommunityList().get(position).getContent()));
                }
            }

            if (myselfPostModel.getData().getCommunityList().get(position).getImages().size() == 0) {

                ((ViewHolder) holder).gvPostImages.setVisibility(View.GONE);

            } else {

                if (myselfPostModel.getData().getCommunityList().get(position).getImages().size() == 4) {

                    ((ViewHolder) holder).gvPostImages.setLayoutManager(new GridLayoutManager(mContext,2));

                } else {

                    ((ViewHolder) holder).gvPostImages.setLayoutManager(new GridLayoutManager(mContext,3));
                }

                ((ViewHolder) holder).gvPostImages.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).gvPostImages.setAdapter(new ImageRecyclerAdapter(mContext,myselfPostModel.getData().getCommunityList().get(position).getImages()));
            }
        } else {
            if (footerFlag) {
                ((FooterViewHolder) holder).llFooter.setVisibility(View.GONE);
            } else {
                ((FooterViewHolder) holder).llFooter.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == myselfPostModel.getData().getCommunityList().size()) {
            return FOOTER_TYPE;
        } else {
            return POST_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return myselfPostModel.getData().getCommunityList().size()+1;
    }

    public void setItemOnClickListener(OnRecyclerViewClickListener listener) {
        mListener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnRecyclerViewClickListener listener;
        @BindView(R.id.iv_top)
        ImageView ivTop;
        @BindView(R.id.tv_tine)
        TextView tvTine;
        @BindView(R.id.tv_post_content)
        TextView tvPostContent;
        @BindView(R.id.gv_post_images)
        RecyclerView gvPostImages;

        ViewHolder(View view , OnRecyclerViewClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            view.setOnClickListener(this);
            gvPostImages.addItemDecoration(new SpaceItemDecoration(ScreenUtil.dpToPxInt(mContext,6)));
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.OnItemClick(v, getLayoutPosition());
            }
        }
    }


    public class FooterViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout llFooter;



        public FooterViewHolder(View itemView) {
            super(itemView);
            llFooter = (LinearLayout) itemView.findViewById(R.id.ll_circle_footer);
        }

    }
}
