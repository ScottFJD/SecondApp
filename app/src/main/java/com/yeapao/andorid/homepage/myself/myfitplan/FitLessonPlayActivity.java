package com.yeapao.andorid.homepage.myself.myfitplan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by fujindong on 05/02/2018.
 */

public class FitLessonPlayActivity extends AppCompatActivity {
    private static final String TAG = "FitLessonPlayActivity";


    private JCVideoPlayerStandard videoPlayerStandard;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, FitLessonPlayActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        initVideo();
    }

    private void initVideo() {

        videoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.jps_fit_video_play);
        String url = "http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516794400599.mp4?Expires=1517818989&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=pC2yUuxxunApLHxmRpo4KQzYVlU%3D";
        videoPlayerStandard.setUp(url,JCVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN,"resheng");


//        ((ViewHolder)holder).jpsVideoPlay.setUp(videoModels.getData().get(position).getUrl()
//                , JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, videoModels.getData().get(position).getTitle());
//        ((ViewHolder) holder).jpsVideoPlay.thumbImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.pic01));
//        glideUtil.glideLoadingImage(mContext,videoModels.getData().get(position).getImageUrl(),
//                R.drawable.pic01,((ViewHolder) holder).jpsVideoPlay.thumbImageView);
    }
}
