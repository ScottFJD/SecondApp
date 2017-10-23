package com.yeapao.andorid.homepage.sport_plan;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.scottfu.sflibrary.util.ScreenUtil;
import com.yeapao.andorid.R;

/**
 * Created by fujindong on 2017/10/23.
 */

public class SportPlanViewPager extends PagerAdapter {
    private static final String TAG = "SportPlanViewPager";
    private Context mContext;



    public SportPlanViewPager(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pager_sport_plan, null);
        container.addView(view);
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.cl_sport_collect);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        TextView finish = (TextView) view.findViewById(R.id.tv_finish);
        TextView title = (TextView) view.findViewById(R.id.tv_collect_title);
        TextView chooseOne = (TextView) view.findViewById(R.id.tv_choose_one);
        TextView chooseTwo = (TextView) view.findViewById(R.id.tv_choose_two);
        TextView chooseThree = (TextView) view.findViewById(R.id.tv_choose_three);
        if (position == 0) {
            title.setText(mContext.getResources().getString(R.string.sport_plan_1));
            chooseOne.setText(mContext.getResources().getString(R.string.sport_plan_1_1));
            chooseTwo.setText(mContext.getResources().getString(R.string.sport_plan_1_2));
            chooseThree.setText(mContext.getResources().getString(R.string.sport_plan_1_3));
            finish.setVisibility(View.GONE);
        } else if (position == 1) {
            constraintSet.constrainWidth(R.id.tv_choose_one, ScreenUtil.dpToPxInt(mContext,220));
            constraintSet.constrainWidth(R.id.tv_choose_two, ScreenUtil.dpToPxInt(mContext,220));
            constraintSet.constrainWidth(R.id.tv_choose_three, ScreenUtil.dpToPxInt(mContext,220));
            constraintSet.applyTo(constraintLayout);
            title.setText(mContext.getResources().getString(R.string.sport_plan_2));
            chooseOne.setText(mContext.getResources().getString(R.string.sport_plan_2_1));
            chooseTwo.setText(mContext.getResources().getString(R.string.sport_plan_2_2));
            chooseThree.setText(mContext.getResources().getString(R.string.sport_plan_2_3));
            finish.setVisibility(View.GONE);
        } else if (position == 2) {
            title.setText(mContext.getResources().getString(R.string.sport_plan_3));
            chooseOne.setText(mContext.getResources().getString(R.string.sport_plan_3_1));
            chooseTwo.setText(mContext.getResources().getString(R.string.sport_plan_3_2));
            chooseThree.setText(mContext.getResources().getString(R.string.sport_plan_3_3));
            finish.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
