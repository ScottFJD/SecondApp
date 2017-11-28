package com.yeapao.andorid.homepage.station.dynamiclesson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ScreenUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/11/22.
 */

public class DynamicLessonDetailActivity extends BaseActivity {
    private static final String TAG = "DynamicLessonDetailActivity";
    @BindView(R.id.tv_order_title)
    TextView tvOrderTitle;
    @BindView(R.id.iv_dynamic_lesson)
    ImageView ivDynamicLesson;
    @BindView(R.id.tv_dynamic_lesson_time)
    TextView tvDynamicLessonTime;
    @BindView(R.id.tv_dynamic_lesson_address)
    TextView tvDynamicLessonAddress;
    @BindView(R.id.imageView108)
    ImageView imageView108;
    @BindView(R.id.tv_dynamic_coach_name)
    TextView tvDynamicCoachName;
    @BindView(R.id.tv_dynamic_coach_job)
    TextView tvDynamicCoachJob;
    @BindView(R.id.ll_coach)
    LinearLayout llCoach;
    @BindView(R.id.crl_dynamic_coach_header)
    CircleImageView crlDynamicCoachHeader;
    @BindView(R.id.tv_dynamic_coach_title_1)
    TextView tvDynamicCoachTitle1;
    @BindView(R.id.tv_dynamic_coach_title_2)
    TextView tvDynamicCoachTitle2;
    @BindView(R.id.ll_coach_title)
    LinearLayout llCoachTitle;
    @BindView(R.id.iv_dinner)
    ImageView ivDinner;
    @BindView(R.id.iv_content_more)
    ImageView ivContentMore;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;


    private boolean ivContentStatus = false;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, DynamicLessonDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_lesson_detail);
        ButterKnife.bind(this);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("动感光影跑");
        initBack();
    }

    @OnClick(R.id.tv_immediately_reservation)
    void reservationOnClick(View view) {
        DynamicLessonReservationActivity.start(getContext());
    }

    @OnClick(R.id.iv_content_more)
    void setIvContentMore(View view) {
        if (ivContentStatus) {
            LogUtil.e(TAG,"false");
            ivContentStatus = false;
            llCoachTitle.removeView(addView("健身教练"));
            RelativeLayout.LayoutParams layoutParams =
                    new RelativeLayout.LayoutParams(ScreenUtil.dpToPxInt(getContext(),270),
                            ScreenUtil.dpToPxInt(getContext(), 42));
            layoutParams.setMargins(ScreenUtil.dpToPxInt(getContext(),15),ScreenUtil.dpToPxInt(getContext(),45),0,0);
            llCoachTitle.setLayoutParams(layoutParams);

//            ConstraintLayout.LayoutParams constraintLayoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
//                    ScreenUtil.dpToPxInt(getContext(),120));
//            relativeLayout.setLayoutParams(constraintLayoutParams);


        } else {
            ivContentStatus = true;
            LogUtil.e(TAG,"true");
            llCoachTitle.addView(addView("健身教练"));
            RelativeLayout.LayoutParams layoutParams =
                    new RelativeLayout.LayoutParams(ScreenUtil.dpToPxInt(getContext(),270),
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(ScreenUtil.dpToPxInt(getContext(),15),ScreenUtil.dpToPxInt(getContext(),45),0,0);
            llCoachTitle.setLayoutParams(layoutParams);

//            ConstraintLayout.LayoutParams constraintLayoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
//                    ConstraintLayout.LayoutParams.WRAP_CONTENT);
//            relativeLayout.setLayoutParams(constraintLayoutParams);
        }
    }

    private View addView(String content) {

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView t1 = new TextView(this);
        t1.setLayoutParams(layoutParams);
        t1.setText(content);
        return t1;
    }

    @Override
    protected Context getContext() {

        return this;
    }
}
