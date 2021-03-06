package com.yeapao.andorid;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import android.databinding.DataBindingUtil;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.util.NetUtils;
import com.jaeger.library.StatusBarUtil;
import com.scottfu.sflibrary.net.CloudClient;
import com.scottfu.sflibrary.net.JSONResultHandler;
import com.scottfu.sflibrary.permission.ActivityCollector;
import com.scottfu.sflibrary.permission.PermissionActivity;
import com.scottfu.sflibrary.permission.PermissionListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.umeng.analytics.MobclickAgent;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.api.NetImpl;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.databinding.ActivityMainBinding;
import com.yeapao.andorid.homepage.circle.CircleFragmentView;
import com.yeapao.andorid.homepage.circle.CirclePresenter;
import com.yeapao.andorid.homepage.lesson.LessonFragmentView;
import com.yeapao.andorid.homepage.lesson.LessonPresenter;
import com.yeapao.andorid.homepage.map.MapFragmentView;
import com.yeapao.andorid.homepage.myself.MyselfFragmentView;
import com.yeapao.andorid.homepage.myself.MyselfPresenter;
import com.yeapao.andorid.homepage.shopping.ShoppingFragmentView;
import com.yeapao.andorid.homepage.shopping.ShoppingPresenter;
import com.yeapao.andorid.homepage.sport_plan.SportPlanFragment;
import com.yeapao.andorid.homepage.station.StationFragmentView;
import com.yeapao.andorid.homepage.station.StationPresenter;
import com.yeapao.andorid.homepage.video.VideoContract;
import com.yeapao.andorid.homepage.video.VideoFragmentView;
import com.yeapao.andorid.homepage.video.VideoPresenter;
import com.yeapao.andorid.model.BodySideThirdSaveModel;
import com.yeapao.andorid.model.LoginModel;
import com.yeapao.andorid.model.UserData;
import com.yeapao.andorid.model.UserDetailsModel;
import com.yeapao.andorid.userinfo.FillUserInfoActivity;
import com.yeapao.andorid.util.DownloadManager;
import com.yeapao.andorid.util.GlobalDataYepao;
import com.yeapao.andorid.yeapaojpush.ExampleUtil;
import com.yeapao.andorid.yeapaojpush.LocalBroadcastManager;

import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends PermissionActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding bind;

    private MainPagerAdapter mainPagerAdapter;

    private LessonFragmentView lessonFragmentView;
    private ShoppingFragmentView shoppingFragmentView;
    private CircleFragmentView circleFragmentView;
    private MyselfFragmentView myselfFragmentView;
    private VideoFragmentView videoFragmentView;
    private MapFragmentView mapFragmentView;
    private SportPlanFragment sportPlanFragment;
    private StationFragmentView stationFragmentView;

    private LessonPresenter lessonPresenter;
    private ShoppingPresenter shoppingPresenter;
    private CirclePresenter circlePresenter;
    private MyselfPresenter myselfPresenter;
    private VideoPresenter videoPresenter;
    private StationPresenter stationPresenter;

    private ImageView tab1;
    private ImageView tab2;
    private ImageView tab3;
    private ImageView tab4;


    private long exitTime = 0;

    public static int currentTab = 0;

    protected Subscription subscription;

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }


    private SparseIntArray items;// used for change ViewPager selected item
    private List<Fragment> fragments;// used for ViewPager adapter

    //    jpush
    public static boolean isForeground = false;

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public static MainActivity mainActivity;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    //   推送过来接收的广播
    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    if (messge != null) {
                        LogUtil.e(TAG, "message   " + messge);
                        mapFragmentView.refreshMessageIcon();
                    }
//                    toast key
                    LogUtil.e("Jpush_MessageReceiver", "-------" + messge);

                }
            } catch (Exception e) {

            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        isForeground = true;
        ActivityCollector.addActivity(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//初始隐藏键盘
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main);

        loginAccount();


        initView();
        permissionCheck();
        registerMessageReceiver();//极光


        fragments = new ArrayList<>(3);
        items = new SparseIntArray(3);

        if (savedInstanceState != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
//            lessonFragmentView = (LessonFragmentView) fragmentManager.getFragment(savedInstanceState, "lesson");
//            shoppingFragmentView = (ShoppingFragmentView) fragmentManager.getFragment(savedInstanceState, "shopping");
            circleFragmentView = (CircleFragmentView) fragmentManager.getFragment(savedInstanceState, "circle");
            myselfFragmentView = (MyselfFragmentView) fragmentManager.getFragment(savedInstanceState, "myself");
//            videoFragmentView = (VideoFragmentView) fragmentManager.getFragment(savedInstanceState, "video");
            mapFragmentView = (MapFragmentView) fragmentManager.getFragment(savedInstanceState, "map");
            sportPlanFragment = (SportPlanFragment) fragmentManager.getFragment(savedInstanceState, "sport");
            stationFragmentView = (StationFragmentView) fragmentManager.getFragment(savedInstanceState, "station");


            fragments.add(mapFragmentView);
            fragments.add(stationFragmentView);
//            fragments.add(sportPlanFragment);
//            fragments.add(lessonFragmentView);
//            fragments.add(shoppingFragmentView);
            fragments.add(circleFragmentView);
            fragments.add(myselfFragmentView);

        } else {
//            lessonFragmentView = LessonFragmentView.newInstance();
//            shoppingFragmentView = ShoppingFragmentView.newInstance();
            circleFragmentView = CircleFragmentView.newInstance();
            myselfFragmentView = MyselfFragmentView.newInstance();
//            videoFragmentView = VideoFragmentView.newInstance();
            mapFragmentView = MapFragmentView.newInstance();
//            sportPlanFragment = SportPlanFragment.newInstance();
            stationFragmentView = StationFragmentView.newInstance();


            fragments.add(mapFragmentView);
//            fragments.add(sportPlanFragment);
            fragments.add(stationFragmentView);
//            fragments.add(videoFragmentView);
//            fragments.add(lessonFragmentView);
//            fragments.add(shoppingFragmentView);
            fragments.add(circleFragmentView);
            fragments.add(myselfFragmentView);
        }

//        lessonPresenter = new LessonPresenter(getContext(), lessonFragmentView);
//        shoppingPresenter = new ShoppingPresenter(getContext(), shoppingFragmentView);
        circlePresenter = new CirclePresenter(getContext(), circleFragmentView);
        myselfPresenter = new MyselfPresenter(getContext(), myselfFragmentView);
//        videoPresenter = new VideoPresenter(getContext(), videoFragmentView);
        stationPresenter = new StationPresenter(getContext(), stationFragmentView);
//        fragments.add(lessonFragmentView);
//        fragments.add(shoppingFragmentView);
//        fragments.add(circleFragmentView);
//        fragments.add(myselfFragmentView);


        items.put(R.id.home_cang, 0);
        items.put(R.id.home_run, 1);
        items.put(R.id.home_circle, 2);
        items.put(R.id.home_myself, 3);


        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        bind.vp.setAdapter(mainPagerAdapter);
        bind.vp.setOffscreenPageLimit(4);


        initEvent();

        checkVersion();

        thread.start();

    }



    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (circleFragmentView != null) {
                    circleFragmentView.refreshBanner();
                }

            }
        }
    });


    public void checkItem() {
        LogUtil.e(TAG,"checkItem");
        bind.vp.setCurrentItem(0);

    }

    private void checkVersion() {
        DownloadManager dowmmanager = new DownloadManager(getContext());
        dowmmanager.checkUpdate(false, new DownloadManager.SuccessCallback() {
            @Override
            public void onSuccess(String version) {

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribe();
        ActivityCollector.removeActivity(this);
        isForeground = false;

    }


    private void initView() {
        // disable all animations
        bind.bnvTab.enableAnimation(false);
        bind.bnvTab.enableShiftingMode(false);
        bind.bnvTab.enableItemShiftingMode(false);

        bind.bnvTab.setIconSize(25, 25);
//        bind.bnvTab.setTextSize(10);
        bind.bnvTab.setTextVisibility(false);

//        loginAccount();
        // add badge
//        addBadgeAt(2, 1);

        bind.ivYeapao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bind.vp.setCurrentItem(0);
                bind.ivYeapao1.setImageResource(R.drawable.cangv2_s);
                bind.ivYeapao2.setImageResource(R.drawable.run_station_n);
                bind.ivYeapao3.setImageResource(R.drawable.circle_n);
                bind.ivYeapao4.setImageResource(R.drawable.myself_n);
            }
        });

        bind.ivYeapao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bind.vp.setCurrentItem(1);
                bind.ivYeapao1.setImageResource(R.drawable.cangv2_n);
                bind.ivYeapao2.setImageResource(R.drawable.run_station_s);
                bind.ivYeapao3.setImageResource(R.drawable.circle_n);
                bind.ivYeapao4.setImageResource(R.drawable.myself_n);
            }
        });

        bind.ivYeapao3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bind.vp.setCurrentItem(2);
                bind.ivYeapao1.setImageResource(R.drawable.cangv2_n);
                bind.ivYeapao2.setImageResource(R.drawable.run_station_n);
                bind.ivYeapao3.setImageResource(R.drawable.circle_s);
                bind.ivYeapao4.setImageResource(R.drawable.myself_n);
            }
        });

        bind.ivYeapao4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bind.vp.setCurrentItem(3);
                bind.ivYeapao1.setImageResource(R.drawable.cangv2_n);
                bind.ivYeapao2.setImageResource(R.drawable.run_station_n);
                bind.ivYeapao3.setImageResource(R.drawable.circle_n);
                bind.ivYeapao4.setImageResource(R.drawable.myself_s);
            }
        });
    }


    /**
     * set listeners
     */
    private void initEvent() {
        // set listener to change the current item of view pager when click bottom nav item
        bind.bnvTab.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = 0;
//                switch (item.getItemId()) {
//                    case R.id.menu_music:
//                        id = 0;
//                        break;
//                    case R.id.menu_backup:
//                        id = 1;
//                        break;
//                    case R.id.menu_friends:
//                        id = 2;
//                        break;
//                }
//                if(previousPosition != id) {
//                  bind.vp.setCurrentItem(id, false);
//                  previousPosition = id;
//                }

                // you can write as above.
                // I recommend this method. You can change the item order or counts without update code here.
                int position = items.get(item.getItemId());
                if (previousPosition != position) {
                    // only set item when item changed
                    previousPosition = position;
                    Log.i(TAG, "-----bnve-------- previous item:" + bind.bnvTab.getCurrentItem() + " current item:" + position + " ------------------");
                    bind.vp.setCurrentItem(position);
                }

                return true;
            }
        });

        // set listener to change the current checked item of bottom nav when scroll view pager
        bind.vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentTab = position;
                if (currentTab != 1) {
                    JCVideoPlayer.releaseAllVideos();
                }
                if (currentTab == 2) {
//                    circleFragmentView.onResume();
                }
                Log.i(TAG, "-----ViewPager-------- previous item:" + bind.bnvTab.getCurrentItem() + " current item:" + position + " ------------------");
                bind.bnvTab.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    /**
     * tab添加红色小标
     *
     * @param position
     * @param number
     * @return
     */
    private Badge addBadgeAt(int position, int number) {
        // add badge
        return new QBadgeView(this)
                .setBadgeNumber(number)
                .setGravityOffset(12, 2, true)
                .bindTarget(bind.bnvTab.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState) {
                            ToastManager.showToast(getContext(), "badge is removed");
                        }
                    }
                });
    }


    /**
     * 权限申请
     */
    private void permissionCheck() {
        requestRuntimePermission(new String[]{Manifest.permission.CALL_PHONE,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.READ_PHONE_STATE}, new PermissionListener() {
            @Override
            public void onGranted() {
//                Toast.makeText(getContext(), "所有权限已同意", Toast.LENGTH_SHORT).show();
                if (mapFragmentView != null) {
                    mapFragmentView.onResume();
                } else {

                }

            }

            @Override
            public void onDenied(List<String> deniedPermissions) {
                for (String deniedPermission : deniedPermissions) {
                    Toast.makeText(getContext(), "未同意的权限" + deniedPermission, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        FragmentManager manager = getSupportFragmentManager();
//        manager.putFragment(outState, "lesson", lessonFragmentView);
//        manager.putFragment(outState, "shopping", shoppingFragmentView);
//        manager.putFragment(outState, "circle", circleFragmentView);
//        manager.putFragment(outState, "myself", myselfFragmentView);
//    }

    private Context getContext() {
        return this;
    }


    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) { //先退出视频
            return;
        }
        super.onBackPressed();
    }

    /**
     * 主要是为了解决在首页 店铺少的时候 无法实现筛选栏的顶部悬浮。 课程功能去掉
     *
     * @param
     * @return
     */
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//
//        if (bind.bnvTab.getCurrentItem() == 1) {
//            lessonFragmentView.goneScreening();
//        }
//        return super.dispatchTouchEvent(ev);
//    }
    private void loginAccount() {
        if (GlobalDataYepao.getUser(getContext()) == null) {

        } else {
            UserData userData = GlobalDataYepao.getUser(getContext());
            GlobalDataYepao.setIsLogin(true);

            emClientInit(userData.getId());



            LogUtil.e(TAG, String.valueOf(GlobalDataYepao.getUser(getContext()).getStatus()));
            if (GlobalDataYepao.getUser(getContext()).getStatus() == 0) {
                FillUserInfoActivity.start(getContext());
            } else {

            }
            JPushInterface.setAlias(this, 22, GlobalDataYepao.getUser(getContext()).getId());
            MobclickAgent.onProfileSignIn(GlobalDataYepao.getUser(getContext()).getId());


        }

    }


    private void emClientInit(String userId) {
        LogUtil.e(TAG,"EmClient  "+userId);
        //login 环信登陆
        EMClient.getInstance().login(userId, "123456", new EMCallBack() {

            @Override
            public void onSuccess() {
                LogUtil.e(TAG,"登陆成功");
//                    startActivity(new Intent(MainActivity.this, MainActivity.class));
//                    finish();
            }

            @Override
            public void onProgress(int progress, String status) {
                LogUtil.e(TAG,"ONPROGRESS   "+status);
            }

            @Override
            public void onError(int code, String error) {
                LogUtil.e(TAG,"error    "+error);
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), "login failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //注册一个监听连接状态的listener
        EMClient.getInstance().addConnectionListener(new MyConnectionListener());

    }




    //实现ConnectionListener接口
    private class MyConnectionListener implements EMConnectionListener {
        @Override
        public void onConnected() {
            LogUtil.e(TAG,"EMClient connected");
        }
        @Override
        public void onDisconnected(final int error) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if(error == EMError.USER_REMOVED){
                        // 显示帐号已经被移除
                        LogUtil.e(TAG,"EMClient user removed");
                    }else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                        // 显示帐号在其他设备登录
                        LogUtil.e(TAG,"EMClient user login another device");
                    } else {

                        if (NetUtils.hasNetwork(MainActivity.this)){
                            //连接不到聊天服务器
                        } else{
                            //当前网络不可用，请检查网络设置
                        }

                    }
                }
            });
        }
    }



    private void getNetWork(String phone, String password) {
        LogUtil.e(TAG, phone + password);
        subscription = Network.getYeapaoApi()
                .requestLogin(phone, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<UserDetailsModel> modelObserver = new Observer<UserDetailsModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(UserDetailsModel model) {
            LogUtil.e(TAG + "login", model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                GlobalDataYepao.setIsLogin(true);
            }
        }
    };


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            exit();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    /**
     * 程序退出提醒
     */
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            exitTime = System.currentTimeMillis();
            ToastManager.showToast(this, getResources().getString(R.string.exit_again));
        } else {
            mapFragmentView.onDestroy();
            finish();
            System.exit(0);
        }
    }

}
