package com.yeapao.andorid.homepage.circle.circledetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.util.ComplaintDialogFragment;
import com.yeapao.andorid.util.GlobalDataYepao;

/**
 * Created by fujindong on 2017/7/18.
 */

public class CircleDetailActivity extends BaseActivity {
    private static final String TAG = "CircleDetailActivity";

    private CircleDetailFragmentView mFragment;
    private String mCommunityId;
    private ComplaintDialogFragment complaintDialogFragment;



    public static void start(Context context, String communityId,String fabulous) {
        LogUtil.e(TAG+"+++start", communityId+"   "+fabulous);
        Intent intent = new Intent();
        intent.putExtra("communityId", communityId);
        intent.putExtra("fabulous", fabulous);
        intent.setClass(context, CircleDetailActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_detail);
        initTopBar();
        complaintDialogFragment = new ComplaintDialogFragment();

        if (savedInstanceState != null) {
            mFragment = (CircleDetailFragmentView) getSupportFragmentManager().getFragment(savedInstanceState, "CircleDetailFragment");
        } else {
            mFragment = new CircleDetailFragmentView();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, mFragment).commit();
        }

        CircleDetailPresenter presenter = new CircleDetailPresenter(subscription, CircleDetailActivity.this, mFragment);

        Intent intent = getIntent();

        presenter.setCommunityId(intent.getStringExtra("communityId"));
        presenter.setFabulous(intent.getStringExtra("fabulous"));

    }

    private void showPayWay() {
        if (complaintDialogFragment.isVisible()) {
            complaintDialogFragment.dismiss();
        } else {
            complaintDialogFragment.show(getSupportFragmentManager(), "date");
        }
    }

    @Override
    protected void initTopBar() {
        initTitle("帖子详情");
        initBack();
        if (GlobalDataYepao.isLogin()) {
            initRightTextWithClick("投诉", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.e(TAG, "投诉");
                    showPayWay();
                    complaintDialogFragment.setComplaintOnClickListener(new ComplaintDialogFragment.ComplaintDialogClickListener() {
                        @Override
                        public void complaint1() {
                            mFragment.getNetWorkComplaint("广告骚扰");
                            complaintDialogFragment.dismiss();
                        }

                        @Override
                        public void complaint2() {
                            mFragment.getNetWorkComplaint("色情低俗");
                            complaintDialogFragment.dismiss();
                        }

                        @Override
                        public void complaint3() {
                            mFragment.getNetWorkComplaint("钱财诈骗");
                            complaintDialogFragment.dismiss();
                        }

                        @Override
                        public void complaint4() {
                            mFragment.getNetWorkComplaint("政治敏感");
                            complaintDialogFragment.dismiss();
                        }

                        @Override
                        public void complaint5() {
                            mFragment.getNetWorkComplaint("其他");
                            complaintDialogFragment.dismiss();
                        }

                        @Override
                        public void complaintCancel() {
                            complaintDialogFragment.dismiss();
                        }
                    });
                }
            });
        } else {
            initRightText("投诉");
        }


    }


    @Override
    protected Context getContext() {
        return this;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "CircleDetailFragment", mFragment);
        }
    }


}
