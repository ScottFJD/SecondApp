package com.yeapao.andorid.homepage.myself.myfitplan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.FitPlanDetailModel;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by fujindong on 05/02/2018.
 */

public class FitLessonPlayActivity extends AppCompatActivity {
    private static final String TAG = "FitLessonPlayActivity";


    private JCVideoPlayerStandard videoPlayerStandard;
    private FitPlanDetailModel fitPlanDetailModel;
    private String type;
    private List<FitPlanDetailModel.ResultMapBean.WarmUpMediaListBean> lessonVideoList = new ArrayList<>();

    private int currentPosition = 0;
    private GlideUtil glideUtil = new GlideUtil();

    private TextView previousLesson;
    private TextView nextLesson;

    public static void start(Context context,int position,FitPlanDetailModel fitPlanDetailModel,String type ) {
        Intent intent = new Intent();
        intent.setClass(context, FitLessonPlayActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("type", type);
        Bundle extra = new Bundle();
        extra.putSerializable("detailModel", fitPlanDetailModel);
        intent.putExtras(extra);
        context.startActivity(intent);
    }

    public static void start(Context context, FitPlanDetailModel fitPlanDetailModel,String type) {
        Intent intent = new Intent();
        intent.setClass(context, FitLessonPlayActivity.class);
        Bundle extra = new Bundle();
        extra.putSerializable("detailModel", fitPlanDetailModel);
        intent.putExtras(extra);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        videoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.jps_fit_video_play);
        previousLesson = (TextView) findViewById(R.id.tv_previous_lesson);
        nextLesson = (TextView) findViewById(R.id.tv_next_lesson);
        Bundle bundle = getIntent().getExtras();
        fitPlanDetailModel = (FitPlanDetailModel) bundle.getSerializable("detailModel");
        type = getIntent().getStringExtra("type");
        currentPosition = getIntent().getIntExtra("position", 0);
        initData();
    }

    private void initData() {
        lessonVideoList.addAll(fitPlanDetailModel.getResultMap().getWarmUpMediaList());
        lessonVideoList.addAll(fitPlanDetailModel.getResultMap().getOfficialMediaList());
        lessonVideoList.addAll(fitPlanDetailModel.getResultMap().getStretchMediaList());

        initVideo();

    }

    private void initVideo() {

        previousLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition <= 0) {
                    ToastManager.showToast(FitLessonPlayActivity.this,"当前是第一节课程");
                    return;
                }
                int lessonPosition = --currentPosition;

                videoPlayerStandard.setUp(lessonVideoList.get(lessonPosition).getSrcString(),
                        JCVideoPlayer.SCREEN_LAYOUT_LIST,lessonVideoList.get(lessonPosition).getName());
//                glideUtil.glideLoadingImage(FitLessonPlayActivity.this,lessonVideoList.get(lessonPosition).getImgString(),R.drawable.placeholder,
//                        videoPlayerStandard.thumbImageView);
            }
        });

        nextLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition >= lessonVideoList.size()-1) {
                    ToastManager.showToast(FitLessonPlayActivity.this,"当前是最后一节课程");
                    return;
                }
                int lessonPosition = ++currentPosition;

                videoPlayerStandard.setUp(lessonVideoList.get(lessonPosition).getSrcString(),
                        JCVideoPlayer.SCREEN_LAYOUT_NORMAL,lessonVideoList.get(lessonPosition).getName());
//                glideUtil.glideLoadingImage(FitLessonPlayActivity.this,lessonVideoList.get(lessonPosition).getImgString(),R.drawable.placeholder,
//                        videoPlayerStandard.thumbImageView);
                videoPlayerStandard.startVideo();
            }
        });

        if (type.equals("1")) {
            videoPlayerStandard.setUp(lessonVideoList.get(currentPosition).getSrcString(),
                    JCVideoPlayer.SCREEN_LAYOUT_LIST,lessonVideoList.get(currentPosition).getName());
//            glideUtil.glideLoadingImage(this,lessonVideoList.get(currentPosition).getImgString(),R.drawable.placeholder,
//                    videoPlayerStandard.thumbImageView);
            videoPlayerStandard.startVideo();
        } else {
            videoPlayerStandard.setUp(lessonVideoList.get(currentPosition).getSrcString(),
                    JCVideoPlayer.SCREEN_LAYOUT_LIST,lessonVideoList.get(currentPosition).getName());
//            glideUtil.glideLoadingImage(this,lessonVideoList.get(currentPosition).getImgString(),R.drawable.placeholder,
//                    videoPlayerStandard.thumbImageView);
            videoPlayerStandard.startVideo();
        }

//        String url = "http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516794400599.mp4?Expires=1517818989&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=pC2yUuxxunApLHxmRpo4KQzYVlU%3D";
//        String url = getIntent().getStringExtra("url");
//        LogUtil.e(TAG,url);
//        videoPlayerStandard.setUp(url,JCVideoPlayerStandard.SCREEN_LAYOUT_LIST,
//                getIntent().getStringExtra("name"));

//        ((ViewHolder)holder).jpsVideoPlay.setUp(videoModels.getData().get(position).getUrl()
//                , JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, videoModels.getData().get(position).getTitle());
//        ((ViewHolder) holder).jpsVideoPlay.thumbImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.pic01));
//        glideUtil.glideLoadingImage(mContext,videoModels.getData().get(position).getImageUrl(),
//                R.drawable.pic01,((ViewHolder) holder).jpsVideoPlay.thumbImageView);

    }


    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
