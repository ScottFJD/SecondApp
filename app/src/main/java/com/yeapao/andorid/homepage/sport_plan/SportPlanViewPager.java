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
import com.lidroid.xutils.db.annotation.Id;
import com.scottfu.sflibrary.util.ScreenUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.LoginActivity;
import com.yeapao.andorid.R;
import com.yeapao.andorid.util.GlobalDataYepao;

/**
 * Created by fujindong on 2017/10/23.
 */

public class SportPlanViewPager extends PagerAdapter {
    private static final String TAG = "SportPlanViewPager";
    private Context mContext;

    private String careerType = "0";
    private String sportsCondition = "0";
    private String workForm = "0";

    private SportCollectClickListener sportCollectClickListener;
    private SportCollectPageListener sportCollectPageListener;

    public void setSportCollectPageListener(SportCollectPageListener listener) {
        sportCollectPageListener = listener;
    }


    public SportPlanViewPager(Context context) {
        mContext = context;
    }

    public void setCollectListener(SportCollectClickListener listener) {
        sportCollectClickListener = listener;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pager_sport_plan, null);
        container.addView(view);
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.cl_sport_collect);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        TextView finish = (TextView) view.findViewById(R.id.tv_finish);
        TextView title = (TextView) view.findViewById(R.id.tv_collect_title);
        final TextView chooseOne = (TextView) view.findViewById(R.id.tv_choose_one);
        final TextView chooseTwo = (TextView) view.findViewById(R.id.tv_choose_two);
        final TextView chooseThree = (TextView) view.findViewById(R.id.tv_choose_three);
        if (position == 0) {
            if (careerType.equals("1")) {
                chooseOne.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_yellow_shape));
                chooseOne.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            } else if (careerType.equals("2")) {
                chooseTwo.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_yellow_shape));
                chooseTwo.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            } else if (careerType.equals("3")) {
                chooseThree.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_yellow_shape));
                chooseThree.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            } else {

            }
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
            if (sportsCondition.equals("1")) {
                chooseOne.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_yellow_shape));
                chooseOne.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            } else if (sportsCondition.equals("2")) {
                chooseTwo.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_yellow_shape));
                chooseTwo.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            } else if (sportsCondition.equals("3")) {
                chooseThree.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_yellow_shape));
                chooseThree.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            } else {

            }
        } else if (position == 2) {
            title.setText(mContext.getResources().getString(R.string.sport_plan_3));
            chooseOne.setText(mContext.getResources().getString(R.string.sport_plan_3_1));
            chooseTwo.setText(mContext.getResources().getString(R.string.sport_plan_3_2));
            chooseThree.setText(mContext.getResources().getString(R.string.sport_plan_3_3));
            finish.setVisibility(View.VISIBLE);
            if (workForm.equals("1")) {
                chooseOne.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_yellow_shape));
                chooseOne.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            } else if (workForm.equals("2")) {
                chooseTwo.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_yellow_shape));
                chooseTwo.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            } else if (workForm.equals("3")) {
                chooseThree.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_yellow_shape));
                chooseThree.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            } else {

            }
        }

//        TODO 点击事件
        chooseOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(position,"1");
                chooseOne.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_yellow_shape));
                chooseOne.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                chooseTwo.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_shape));
                chooseTwo.setTextColor(mContext.getResources().getColor(R.color.bottomTabUnChecked));
                chooseThree.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_shape));
                chooseThree.setTextColor(mContext.getResources().getColor(R.color.bottomTabUnChecked));
                checkCurrentPage(position);

            }
        });
        chooseTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(position,"2");
                chooseOne.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_shape));
                chooseOne.setTextColor(mContext.getResources().getColor(R.color.bottomTabUnChecked));
                chooseTwo.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_yellow_shape));
                chooseTwo.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                chooseThree.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_shape));
                chooseThree.setTextColor(mContext.getResources().getColor(R.color.bottomTabUnChecked));
                checkCurrentPage(position);
            }
        });
        chooseThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(position,"3");
                chooseOne.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_shape));
                chooseOne.setTextColor(mContext.getResources().getColor(R.color.bottomTabUnChecked));
                chooseTwo.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_shape));
                chooseTwo.setTextColor(mContext.getResources().getColor(R.color.bottomTabUnChecked));
                chooseThree.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.sport_plan_content_yellow_shape));
                chooseThree.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                checkCurrentPage(position);
            }
        });



        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalDataYepao.getUser(mContext) != null) {

                } else {
                    LoginActivity.start(mContext);
                    return;
                }
                if (careerType.equals("0")) {
                    ToastManager.showToast(mContext,"请选择职业类型");
                    return;
                }
                if (sportsCondition.equals("0")) {
                    ToastManager.showToast(mContext,"请选择职业特征");
                    return;
                }
                if (workForm.equals("0")) {
                    ToastManager.showToast(mContext,"请选择运动情况");
                    return;
                }
                sportCollectClickListener.itemOnClickListener(careerType,sportsCondition,workForm);
            }
        });

        return view;
    }


    private void checkCurrentPage(int position) {
        switch (position) {
            case 0:
                sportCollectPageListener.refreshCurrentPager(1);
                break;

            case 1:
                sportCollectPageListener.refreshCurrentPager(2);
                break;
            default:
                break;

        }
    }


    private void setData(int position, String num) {
        switch (position) {
            case 0:
                careerType = num;
                break;
            case 1:
                sportsCondition = num;
                break;
            case 2:
                workForm = num;
                break;
            default:
                break;
        }
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
