package com.yeapao.andorid;


/**
 * Created by fujindong on 2017/12/4.
 */


import android.app.ActionBar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.yeapao.andorid.util.CustomVideoView;
import com.yeapao.andorid.util.GlobalDataYepao;
import com.yeapao.andorid.util.SampleSlide;

/**
 * 视频资源要添加res文件夹下创建raw文件夹
 * 需要在onRestart()方法里重新加载视频，防止退出返回时视频黑屏
 * 我这样写简单粗暴而已，当然，也可优雅的以自己看播放控件的VideoView处理方法，去处理资源释放和播放显示的问题。
 * 记得修改布局控件<CustomVideoView...引用的包名，不然会报错哦
 * android:screenOrientation="portrait" 习惯性的把横竖屏切换也设置一下
 * android:theme="@style/Theme.AppCompat.Light.NoActionBar" ActionBar也可以设置成不显示的状态，可以根据自己喜好和项目需求
 */
public class YeaPaoVideoIntroActivity extends AppCompatActivity {
    //创建播放视频的控件对象
    private CustomVideoView videoview;
    private ImageView immediateStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_intro);
        if (GlobalDataYepao.getUser(this) == null) {
            initView();
        } else {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
    }

    private void initView() {
        //加载视频资源控件
        videoview = (CustomVideoView) findViewById(R.id.videoview);
        //设置播放加载路径
        videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video8));
        //播放
        videoview.start();
        //循环播放
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoview.start();
            }
        });

        immediateStart = (ImageView) findViewById(R.id.iv_immediate_experience);
        immediateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(YeaPaoVideoIntroActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    //返回重启加载
    @Override
    protected void onRestart() {
        initView();
        super.onRestart();
    }

    //防止锁屏或者切出的时候，音乐在播放
    @Override
    protected void onStop() {
        videoview.stopPlayback();
        super.onStop();
    }
}

