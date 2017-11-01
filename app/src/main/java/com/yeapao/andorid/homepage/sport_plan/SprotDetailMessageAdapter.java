package com.yeapao.andorid.homepage.sport_plan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.model.SportPlanDetailModel;
import com.yeapao.andorid.util.GlobalDataYepao;
import com.yeapao.andorid.util.SpannableTextUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by fujindong on 2017/10/24.
 */

public class SprotDetailMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "SportDetailMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;

    private GlideUtil glideUtil = new GlideUtil();

    private SportPlanDetailModel sportPlanDetailModel;

    private SportPlanVideoListener sportPlanVideoListener;
    private SportClickDayRefreshListener sportClickDayRefreshListener;
    private CheckCurrentItemListener checkCurrentItemListener;

    private int currentDay = 0;
    private int totalDay = 0;

    public void setCheckCurrentItemListener(CheckCurrentItemListener listener) {
        checkCurrentItemListener = listener;
    }

    public void setSportClickDayRefreshListener(SportClickDayRefreshListener listener) {
        sportClickDayRefreshListener = listener;
    }

    public void setSportPlanVideoClickListener(SportPlanVideoListener sportPlanVideoClickListener) {
        sportPlanVideoListener = sportPlanVideoClickListener;
    }

    public SprotDetailMessageAdapter(Context context, SportPlanDetailModel model) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        sportPlanDetailModel = model;
        totalDay = sportPlanDetailModel.getData().getProgrammeDetailList().size();
        currentDay = totalDay - 1;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new FirstViewHolder(inflater.inflate(R.layout.item_sport_plan_first, parent, false),sportClickDayRefreshListener);
            case 1:
                return new SecondViewHolder(inflater.inflate(R.layout.item_sport_plan_second, parent, false));
            case 2:
                return new ThirdViewHolder(inflater.inflate(R.layout.item_sport_plan_third, parent, false));
            case 3:
                return new ForthViewHolder(inflater.inflate(R.layout.item_sport_plan_forth, parent, false));
            case 4:
                return new FifthViewHolder(inflater.inflate(R.layout.item_sport_plan_fifth, parent, false));
            case 5:
                return new SixthViewHolder(inflater.inflate(R.layout.item_sport_plan_sixth, parent, false));
            case 6:
                return new SeventhViewHolder(inflater.inflate(R.layout.item_sport_plan_seventh, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof FirstViewHolder) {
            initDataUI(holder);
        }
        if (holder instanceof SecondViewHolder) {
            ((SecondViewHolder) holder).ivScoreBmi.setText(String.valueOf(sportPlanDetailModel.getData().getScore()) + "分");
            String str = "";
            if (GlobalDataYepao.getUser(mContext).getGender().equals("男")) {
                str = "先生您好";
            } else {
                str = "女士您好";
            }

            ((SecondViewHolder) holder).tvTitle.setText("尊敬的" + GlobalDataYepao.getUser(mContext).getName() + str);
            String str1 = mContext.getResources().getString(R.string.account_bmi_one);
            String str2 = sportPlanDetailModel.getData().getBMI();
            String str3 = " 属于：";
            String str4 = sportPlanDetailModel.getData().getType();
            String str5 = "。";
            if (sportPlanDetailModel.getData().getSportsCondition().equals("1")) {
                str5 += mContext.getResources().getString(R.string.sport_plan_tips_one);
            } else if (sportPlanDetailModel.getData().getSportsCondition().equals("2")) {
                str5 += mContext.getResources().getString(R.string.sport_plan_tips_two);
            } else {
                str5 += mContext.getResources().getString(R.string.sport_plan_tips_three);
            }
            String str6 = mContext.getResources().getString(R.string.account_bmi_two);
            String str7 = sportPlanDetailModel.getData().getDirection();
            String str8 = mContext.getResources().getString(R.string.account_bmi_three);

            StringBuffer sb = new StringBuffer();
            String content = str1 + str2 + str3 + str4 + str5 + str6 + str7 + str8;
            sb.append(content);
            SpannableStringBuilder stringBuilder = new SpannableStringBuilder(sb.toString());
            int start = 0;
            int end = str1.length();
            int color = Color.rgb(55, 52, 65);
            stringBuilder.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

            start = end;
            end = start + str2.length();
            int color2 = Color.rgb(255, 204, 0);
            stringBuilder.setSpan(new ForegroundColorSpan(color2), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

            start = end;
            end = start + str3.length();
            stringBuilder.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

            start = end;
            end = start + str4.length();
            stringBuilder.setSpan(new ForegroundColorSpan(color2), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

            start = end + str5.length() + str6.length();
            end = start + str7.length();
            stringBuilder.setSpan(new ForegroundColorSpan(color2), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

            ((SecondViewHolder) holder).tvAccountBmiContent.setText(stringBuilder);

        }
        if (holder instanceof ThirdViewHolder) {
            ((ThirdViewHolder) holder).tvHeartRateContent.setText(sportPlanDetailModel.getData().getHeartRate() +
                    mContext.getResources().getString(R.string.heart_rate_unit));

            ((ThirdViewHolder) holder).ivSportConsiderations.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, SportSafetyMattersActivity.class));
                }
            });
        }

        if (holder instanceof ForthViewHolder) {
            String cangDistance = "";
            long distance = (long) sportPlanDetailModel.getData().getDistance();
            double distance1 = (double) distance;
//            if (distance > 1000) {
                cangDistance = String.valueOf(distance1 / 1000) + "km";
//            } else {
//                cangDistance = String.valueOf(distance) + "m";
//            }

            ((ForthViewHolder) holder).tvCangDistance.setText("推荐:距您" + cangDistance + "处的也跑健身舱");
            if (sportPlanDetailModel.getData().getProgrammeDetailList().get(currentDay).getIsMotion() == 0) {
                ((ForthViewHolder) holder).ivSportStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.scheme_step_n));
            } else {
                ((ForthViewHolder) holder).ivSportStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.scheme_step_s));
            }
            ((ForthViewHolder)holder).tvGoRun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkCurrentItemListener.checkItem(0);
                }
            });
        }


        if (holder instanceof FifthViewHolder) {

            if (sportPlanDetailModel.getData().getProgrammeDetailList().get(currentDay).getIsWatchVideosOne() == 0) {
                ((FifthViewHolder) holder).ivSportTwoStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.scheme_step_n));
            } else {
                ((FifthViewHolder) holder).ivSportTwoStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.scheme_step_s));
            }


            ((FifthViewHolder) holder).jpsVideoPlay.setUp(sportPlanDetailModel.getData().getMediaList().get(0).getMediaSrc()
                    , JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, sportPlanDetailModel.getData().getMediaList().get(0).getTitle());
            ((FifthViewHolder) holder).jpsVideoPlay.thumbImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.pic01));
            glideUtil.glideLoadingImage(mContext, sportPlanDetailModel.getData().getMediaList().get(0).getImageUrl(),
                    R.drawable.pic01, ((FifthViewHolder) holder).jpsVideoPlay.thumbImageView);

            ((FifthViewHolder) holder).jpsVideoPlay.startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((FifthViewHolder) holder).ivSportTwoStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.scheme_step_s));
                    LogUtil.e(TAG, "First Video onClick");
                    ((FifthViewHolder) holder).jpsVideoPlay.onClick(((FifthViewHolder) holder).jpsVideoPlay.startButton);
                    if (sportPlanDetailModel.getData().getProgrammeDetailList().get(currentDay).getIsWatchVideosOne() == 0) {
                        sportPlanDetailModel.getData().getProgrammeDetailList().get(currentDay).setIsWatchVideosOne(1);
                        sportPlanVideoListener.sportPlanVideoClickListener(String.valueOf(sportPlanDetailModel.getData().getMediaList().get(0).getVideoId()),
                                String.valueOf(sportPlanDetailModel.getData().getProgrammeId()));
                        sportPlanVideoListener.refreshSportPlanStatus(currentDay,1);

                    } else {

                    }
                }
            });


        }
        if (holder instanceof SixthViewHolder) {
            if (sportPlanDetailModel.getData().getProgrammeDetailList().get(currentDay).getIsWatchVideosTwo() == 0) {
                ((SixthViewHolder) holder).ivSportThreeStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.scheme_step_n));
            } else {
                ((SixthViewHolder) holder).ivSportThreeStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.scheme_step_s));
            }

            ((SixthViewHolder) holder).jpsVideoPlay.setUp(sportPlanDetailModel.getData().getMediaList().get(1).getMediaSrc()
                    , JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, sportPlanDetailModel.getData().getMediaList().get(1).getTitle());
            ((SixthViewHolder) holder).jpsVideoPlay.thumbImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.pic01));
            glideUtil.glideLoadingImage(mContext, sportPlanDetailModel.getData().getMediaList().get(1).getImageUrl(),
                    R.drawable.pic01, ((SixthViewHolder) holder).jpsVideoPlay.thumbImageView);

            ((SixthViewHolder) holder).jpsVideoPlay.startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((SixthViewHolder) holder).ivSportThreeStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.scheme_step_s));
                    LogUtil.e(TAG, "Second Video onClick");
                    ((SixthViewHolder) holder).jpsVideoPlay.onClick(((SixthViewHolder) holder).jpsVideoPlay.startButton);
                    if (sportPlanDetailModel.getData().getProgrammeDetailList().get(currentDay).getIsWatchVideosTwo() == 0) {
                        sportPlanDetailModel.getData().getProgrammeDetailList().get(currentDay).setIsWatchVideosTwo(1);
                        sportPlanVideoListener.sportPlanVideoClickListener(String.valueOf(sportPlanDetailModel.getData().getMediaList().get(1).getVideoId()),
                                String.valueOf(sportPlanDetailModel.getData().getProgrammeId()));
                        sportPlanVideoListener.refreshSportPlanStatus(currentDay,2);
                    } else {

                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private void initDataUI(RecyclerView.ViewHolder holder) {
        int day = sportPlanDetailModel.getData().getProgrammeDetailList().size();
        switch (1) {
            case 1:
                ((FirstViewHolder) holder).ivFirstDay.setImageDrawable(getDayStatusUI(0));
                ((FirstViewHolder) holder).tvFirstDay.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            case 2:
                if (day >= 2) {
                    ((FirstViewHolder) holder).ivSecondDay.setImageDrawable(getDayStatusUI(1));
                    ((FirstViewHolder) holder).ivLine1.setImageDrawable(mContext.getDrawable(R.color.colorPrimary));
                    ((FirstViewHolder) holder).tvSecondDay.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                }
            case 3:
                if (day >= 3) {
                    ((FirstViewHolder) holder).ivThirdDay.setImageDrawable(getDayStatusUI(2));
                    ((FirstViewHolder) holder).ivLine2.setImageDrawable(mContext.getDrawable(R.color.colorPrimary));
                    ((FirstViewHolder) holder).tvThirdDay.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                }

            case 4:
                if (day >= 4) {
                    ((FirstViewHolder) holder).ivForthDay.setImageDrawable(getDayStatusUI(3));
                    ((FirstViewHolder) holder).ivLine3.setImageDrawable(mContext.getDrawable(R.color.colorPrimary));
                    ((FirstViewHolder) holder).tvForthDay.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                }
            case 5:
                if (day >= 5) {
                    ((FirstViewHolder) holder).ivFifthDay.setImageDrawable(getDayStatusUI(4));
                    ((FirstViewHolder) holder).ivLine4.setImageDrawable(mContext.getDrawable(R.color.colorPrimary));
                    ((FirstViewHolder) holder).tvFifthDay.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                }
            case 6:
                if (day >= 6) {
                    ((FirstViewHolder) holder).ivSixthDay.setImageDrawable(getDayStatusUI(5));
                    ((FirstViewHolder) holder).ivLine5.setImageDrawable(mContext.getDrawable(R.color.colorPrimary));
                    ((FirstViewHolder) holder).tvSixthDay.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                }

            case 7:
                if (day >= 7) {
                    ((FirstViewHolder) holder).ivSeventhDay.setImageDrawable(getDayStatusUI(6));
                    ((FirstViewHolder) holder).ivLine6.setImageDrawable(mContext.getDrawable(R.color.colorPrimary));
                    ((FirstViewHolder) holder).tvSeventhDay.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                }


        }
    }

    private Drawable getDayStatusUI(int position) {
        int status = 0;
        status = sportPlanDetailModel.getData().getProgrammeDetailList().get(position).getIsMotion() +
                sportPlanDetailModel.getData().getProgrammeDetailList().get(position).getIsWatchVideosOne() +
                sportPlanDetailModel.getData().getProgrammeDetailList().get(position).getIsWatchVideosTwo();
        switch (status) {
            case 0:
                return mContext.getResources().getDrawable(R.drawable.no_percent);
            case 1:
                return mContext.getResources().getDrawable(R.drawable.thirty_percent);
            case 2:
                return mContext.getResources().getDrawable(R.drawable.sixty_percent);
            case 3:
                return mContext.getResources().getDrawable(R.drawable.all_percent);
            default:
                return mContext.getResources().getDrawable(R.drawable.no_percent);
        }
    }


     class FirstViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         private SportClickDayRefreshListener listener;
        @BindView(R.id.tv_third_day)
        TextView tvThirdDay;
        @BindView(R.id.tv_forth_day)
        TextView tvForthDay;
        @BindView(R.id.tv_fifth_day)
        TextView tvFifthDay;
        @BindView(R.id.tv_sixth_day)
        TextView tvSixthDay;
        @BindView(R.id.iv_first_day)
        ImageView ivFirstDay;
        @BindView(R.id.iv_second_day)
        ImageView ivSecondDay;
        @BindView(R.id.iv_third_day)
        ImageView ivThirdDay;
        @BindView(R.id.iv_forth_day)
        ImageView ivForthDay;
        @BindView(R.id.iv_fifth_day)
        ImageView ivFifthDay;
        @BindView(R.id.iv_sixth_day)
        ImageView ivSixthDay;
        @BindView(R.id.iv_seventh_day)
        ImageView ivSeventhDay;
        @BindView(R.id.iv_line_1)
        ImageView ivLine1;
        @BindView(R.id.iv_line_2)
        ImageView ivLine2;
        @BindView(R.id.iv_line_3)
        ImageView ivLine3;
        @BindView(R.id.iv_line_4)
        ImageView ivLine4;
        @BindView(R.id.iv_line_5)
        ImageView ivLine5;
        @BindView(R.id.iv_line_6)
        ImageView ivLine6;
        @BindView(R.id.tv_first_day)
        TextView tvFirstDay;
        @BindView(R.id.tv_seventh_day)
        TextView tvSeventhDay;
        @BindView(R.id.tv_second_day)
        TextView tvSecondDay;

        FirstViewHolder(View view,SportClickDayRefreshListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            ivFirstDay.setOnClickListener(this);
            ivSecondDay.setOnClickListener(this);
            ivThirdDay.setOnClickListener(this);
            ivForthDay.setOnClickListener(this);
            ivFifthDay.setOnClickListener(this);
            ivSixthDay.setOnClickListener(this);
            ivSeventhDay.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int day = 0;
            switch (v.getId()) {
                case R.id.iv_first_day:
                    day = 0;
                    if (day < totalDay) {
                        currentDay = day;
                        listener.refreshSportPlanList();
                    }
                    LogUtil.e(TAG,"1");
                    break;
                case R.id.iv_second_day:
                    day = 1;
                    if (day < totalDay) {
                        currentDay = day;
                        listener.refreshSportPlanList();
                    }
                    LogUtil.e(TAG,"2");
                    break;
                case R.id.iv_third_day:
                    day = 2;
                    if (day < totalDay) {
                        currentDay = day;
                        listener.refreshSportPlanList();
                    }
                    LogUtil.e(TAG,"3");
                    break;
                case R.id.iv_forth_day:
                    day = 3;
                    if (day < totalDay) {
                        currentDay = day;
                        listener.refreshSportPlanList();
                    }
                    LogUtil.e(TAG,"4");
                    break;
                case R.id.iv_fifth_day:
                    day = 4;
                    if (day < totalDay) {
                        currentDay = day;
                        listener.refreshSportPlanList();
                    }
                    LogUtil.e(TAG,"5");
                    break;
                case R.id.iv_sixth_day:
                    day = 5;
                    if (day < totalDay) {
                        currentDay = day;
                        listener.refreshSportPlanList();
                    }
                    LogUtil.e(TAG,"6");
                    break;
                case R.id.iv_seventh_day:
                    day = 6;
                    if (day < totalDay) {
                        currentDay = day;
                        listener.refreshSportPlanList();
                    }
                    LogUtil.e(TAG,"7");
                    break;
                default:
                    break;
            }
        }
    }

    static class SecondViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_account_bmi_content)
        TextView tvAccountBmiContent;
        @BindView(R.id.iv_score_bmi)
        TextView ivScoreBmi;

        SecondViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ThirdViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_heart_rate_content)
        TextView tvHeartRateContent;
        @BindView(R.id.tv_sport_frequency_content)
        TextView tvSportFrequencyContent;
        @BindView(R.id.tv_sport_cycle_content)
        TextView tvSportCycleContent;
        @BindView(R.id.iv_sport_considerations)
        ImageView ivSportConsiderations;

        ThirdViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ForthViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_sport_status)
        ImageView ivSportStatus;
        @BindView(R.id.tv_cang_distance)
        TextView tvCangDistance;
        @BindView(R.id.tv_go_run)
        TextView tvGoRun;

        ForthViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class FifthViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.jps_video_play)
        JCVideoPlayerStandard jpsVideoPlay;
        @BindView(R.id.cv_image_card)
        CardView cvImageCard;
        @BindView(R.id.iv_sport_two_status)
        ImageView ivSportTwoStatus;

        FifthViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class SixthViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.jps_video_play)
        JCVideoPlayerStandard jpsVideoPlay;
        @BindView(R.id.cv_image_card)
        CardView cvImageCard;
        @BindView(R.id.iv_sport_three_status)
        ImageView ivSportThreeStatus;

        SixthViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class SeventhViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_sport_tips_content)
        TextView tvSportTipsContent;

        SeventhViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
