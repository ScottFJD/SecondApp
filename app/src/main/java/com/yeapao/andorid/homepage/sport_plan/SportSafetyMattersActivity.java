package com.yeapao.andorid.homepage.sport_plan;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/10/25.
 */

public class SportSafetyMattersActivity extends BaseActivity {


    private static final String TAG = "SportSafetyMattersActivity";

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);
        initTopBar();
        mWebView = (WebView) findViewById(R.id.wv_refund);
        mWebView.loadUrl("http://47.92.113.97:8080/security/attention.html");
    }

    @Override
    protected void initTopBar() {
        initTitle("安全注意事项");
        initBack();

    }

    @Override
    protected Context getContext() {
        return this;
    }
}
