package com.yeapao.andorid.homepage.station.dynamiclesson;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ScreenUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.LoginActivity;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.DynamiclessonDetailModel;
import com.yeapao.andorid.util.GlobalDataConstant;
import com.yeapao.andorid.util.GlobalDataYepao;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    @BindView(R.id.ll_coach_title)
    TextView llCoachTitle;
    @BindView(R.id.iv_dinner)
    ImageView ivDinner;
    @BindView(R.id.iv_content_more)
    ImageView ivContentMore;
    @BindView(R.id.iv_content_more_2)
    ImageView ivContentMore2;
    @BindView(R.id.iv_content_more_3)
    ImageView ivContentMore3;
    @BindView(R.id.tv_lesson_info)
    TextView tvLessonInfo;
    @BindView(R.id.tv_safe_info)
    TextView tvSafeInfo;

    @BindView(R.id.rcp_bespeak)
    RoundCornerProgressBar roundCornerProgressBar;
    @BindView(R.id.tv_reservation_people_sum)
    TextView tvReservationPeopleSum;
    @BindView(R.id.tv_immediately_reservation)
    TextView tvImmediatelyReservation;

//    @BindView(R.id.cl_dynamic_lesson_detail)
//    ConstraintLayout clDynamicLessonDetail;

    private ConstraintLayout clDynamicLessonDetail;
    private ConstraintSet applyConstraintSet = new ConstraintSet();
    private ConstraintSet resetConstraintSet = new ConstraintSet();

    private GlideUtil glideUtil = new GlideUtil();


    private DynamiclessonDetailModel dynamiclessonDetailModel;

    private boolean ivContentStatus = false;
    private boolean ivContentStatus2 = false;
    private boolean ivContentStatus3 = false;
    private String lessonId = "";

    public static void start(Context context, String lessonId) {

        Intent intent = new Intent();
        intent.putExtra("lessonId", lessonId);
        intent.setClass(context, DynamicLessonDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_lesson_detail);
        ButterKnife.bind(this);
        initTopBar();
        lessonId = getIntent().getStringExtra("lessonId");
        LogUtil.e(TAG, lessonId);

        if (GlobalDataYepao.getUser(getContext()) != null) {
            getNetWork(GlobalDataYepao.getUser(getContext()).getId(), lessonId);
        } else {
            getNetWork("0", lessonId);
        }
//        clDynamicLessonDetail = (ConstraintLayout) findViewById(R.id.cl_dynamic_lesson_detail);
//        applyConstraintSet.clone(clDynamicLessonDetail);
//        resetConstraintSet.clone(clDynamicLessonDetail);
//        if (Build.VERSION.SDK_INT >= 19) {
//            TransitionManager.beginDelayedTransition(clDynamicLessonDetail);
//        }
    }

    private void showView() {
        if (dynamiclessonDetailModel != null) {
            glideUtil.glideLoadingImage(getContext(), dynamiclessonDetailModel.getData().getUrl(),
                    R.drawable.lesson_three, ivDynamicLesson);
            glideUtil.glideLoadingImage(getContext(), dynamiclessonDetailModel.getData().getHeadImage(),
                    R.drawable.y_you, crlDynamicCoachHeader);
            tvDynamicCoachName.setText(dynamiclessonDetailModel.getData().getCoachName() + "／");
            tvDynamicCoachJob.setText(dynamiclessonDetailModel.getData().getJobTitle());
            tvDynamicLessonAddress.setText(dynamiclessonDetailModel.getData().getShopAddress());
            tvDynamicLessonTime.setText(dynamiclessonDetailModel.getData().getDate() + " " +
                    dynamiclessonDetailModel.getData().getStartTime() + "-" + dynamiclessonDetailModel.getData().getEndTime());
            tvLessonInfo.setText(dynamiclessonDetailModel.getData().getNote() + getResources().getString(R.string.sport_plan));
            tvSafeInfo.setText(dynamiclessonDetailModel.getData().getAttention() + getResources().getString(R.string.sport_plan));
            int defaultHeight = ScreenUtil.dpToPxInt(getContext(), 42);
            String coachTitle = "";
            for (int i = 0; i < dynamiclessonDetailModel.getData().getInformation().size(); i++) {
                if (i == 0) {
                    coachTitle = dynamiclessonDetailModel.getData().getInformation().get(i);
                } else {
                    coachTitle += "\n" + dynamiclessonDetailModel.getData().getInformation().get(i);
                }
            }
            llCoachTitle.setText(coachTitle);
            LogUtil.e(TAG,"llcoachTitle"+String.valueOf(llCoachTitle.getHeight())+"   "+String.valueOf(defaultHeight));
            if (llCoachTitle.getHeight() > defaultHeight) {
                ivContentMore.setVisibility(View.VISIBLE);
            } else {
                ivContentMore.setVisibility(View.VISIBLE);
            }
            if (tvLessonInfo.getHeight() > defaultHeight) {
                ivContentMore2.setVisibility(View.VISIBLE);
            } else {
                ivContentMore2.setVisibility(View.VISIBLE);
            }
            if (tvSafeInfo.getHeight() > defaultHeight) {
                ivContentMore3.setVisibility(View.VISIBLE);
            } else {
                ivContentMore3.setVisibility(View.VISIBLE);
            }
            roundCornerProgressBar.setProgress(dynamiclessonDetailModel.getData().getReservation());
            roundCornerProgressBar.setMax(dynamiclessonDetailModel.getData().getMaxMember());
            roundCornerProgressBar.setProgressBackgroundColor(0xfff2f2f2);
            tvReservationPeopleSum.setText(dynamiclessonDetailModel.getData().getReservation() +
                    "/" + dynamiclessonDetailModel.getData().getMaxMember());
            int status = dynamiclessonDetailModel.getData().getStatus();
            if (status == 2 || status == 3) {
                tvImmediatelyReservation.setBackgroundColor(getResources().getColor(R.color.bg_grey));
            } else {
                tvImmediatelyReservation.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }

            clDynamicLessonDetail = (ConstraintLayout) findViewById(R.id.cl_dynamic_lesson_detail);
            applyConstraintSet.clone(clDynamicLessonDetail);
            resetConstraintSet.clone(clDynamicLessonDetail);
            if (Build.VERSION.SDK_INT >= 19) {
                TransitionManager.beginDelayedTransition(clDynamicLessonDetail);
            }

        }
    }


    @Override
    protected void initTopBar() {
        initTitle("动感光影跑");
        initBack();
    }

    @OnClick(R.id.tv_immediately_reservation)
    void reservationOnClick(View view) {
        if (GlobalDataYepao.getUser(getContext()) != null) {
            DynamicLessonReservationActivity.start(getContext(), lessonId);
        } else {
            ToastManager.showToast(getContext(),"请先登陆");
            LoginActivity.start(getContext());
        }

    }

    @OnClick(R.id.iv_content_more)
    void setIvContentMore(View view) {
        setCoachTitleLayoutParams();
    }

    @OnClick(R.id.iv_content_more_2)
    void setIvContentMore2(View view) {
        setLessonInfo();
    }

    @OnClick(R.id.iv_content_more_3)
    void setIvContentMore3(View view) {
        setsafeInfo();
    }

    private void setCoachTitleLayoutParams() {
        applyConstraintSet.constrainHeight(R.id.ll_coach_title, ConstraintSet.WRAP_CONTENT);
        applyConstraintSet.setVisibility(R.id.iv_content_more,ConstraintSet.GONE);
        applyConstraintSet.applyTo(clDynamicLessonDetail);
    }

    private void setLessonInfo() {
        applyConstraintSet.constrainHeight(R.id.tv_lesson_info, ConstraintSet.WRAP_CONTENT);
        applyConstraintSet.setVisibility(R.id.iv_content_more_2,ConstraintSet.GONE);
        applyConstraintSet.applyTo(clDynamicLessonDetail);
    }

    private void setsafeInfo() {
        applyConstraintSet.constrainHeight(R.id.tv_safe_info, ConstraintSet.WRAP_CONTENT);
        applyConstraintSet.setVisibility(R.id.iv_content_more_3,ConstraintSet.GONE);
        applyConstraintSet.applyTo(clDynamicLessonDetail);
    }

    private View addView(String content) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,ScreenUtil.dpToPxInt(getContext(),3),0,0);
        TextView t1 = new TextView(this);
        t1.setTextSize(13);
        t1.setLayoutParams(layoutParams);
        t1.setText(content);
        return t1;
    }

    @Override
    protected Context getContext() {

        return this;
    }


    private void getNetWork(String id, String calendarId) {
        LogUtil.e(TAG, id + " " + calendarId);
        subscription = Network.getYeapaoApi()
                .requestDynamicDetail(id, calendarId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);

    }

    Observer<DynamiclessonDetailModel> modelObserver = new Observer<DynamiclessonDetailModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(DynamiclessonDetailModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                dynamiclessonDetailModel = model;
                showView();
            }
        }
    };

}
