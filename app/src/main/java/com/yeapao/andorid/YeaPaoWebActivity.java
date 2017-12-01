package com.yeapao.andorid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/11/30.
 */

public class YeaPaoWebActivity extends BaseActivity {
    private static final String TAG = "YeapaoWebActivity";

    private String title;
    private String url;
    private WebView mWebView;

    public static void start(Context context,String title, String url) {
        Intent intent = new Intent();
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.setClass(context, YeaPaoWebActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeapao_web);
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        mWebView = (WebView) findViewById(R.id.wv_yeapao);
        mWebView.loadUrl(url);
        initTopBar();
    }



    @Override
    protected void initTopBar() {
        initTitle(title);
        initBack();

    }

    @Override
    protected Context getContext() {
        return this;
    }
}
