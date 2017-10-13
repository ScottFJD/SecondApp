package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.homepage.circle.circledetail.CircleDetailActivity;
import com.yeapao.andorid.model.MyselfPostModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/23.
 */

public class MyselfPostActivity extends BaseActivity {

    private static final String TAG = "MyselfPostActivity";
    @BindView(R.id.rv_my_post_list)
    RecyclerView rvMyPostList;
    private LinearLayoutManager llm;
    private MyselfPostMessageAdapter mPostMessageAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private long currentPage = 0;
    private int totalPage;
    private MyselfPostModel myselfPostModel = new MyselfPostModel();

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfPostActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        ButterKnife.bind(this);
        initTopBar();
        initView();
    }

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_post);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 0;
                getNetWork(GlobalDataYepao.getUser(getContext()).getId(),String.valueOf(currentPage));
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);


        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMyPostList.setLayoutManager(llm);
        rvMyPostList.setOnScrollListener(new RecyclerView.OnScrollListener() {

            boolean isSlidingToLast = false;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                    判断是否滑动到底部 加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        if (currentPage < totalPage - 1) {
                            getNetWork(GlobalDataYepao.getUser(getContext()).getId(),String.valueOf(++currentPage));
                        } else {
                            mPostMessageAdapter.loadNothing();
                            ToastManager.showToast(getContext(), "没有更多");
                        }
                    }

                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isSlidingToLast = dy > 0;

            }

        });
        swipeRefreshLayout.setRefreshing(true);
        getNetWork(GlobalDataYepao.getUser(getContext()).getId(),String.valueOf(currentPage));

    }

    private void refreshView(MyselfPostModel model) {
        if (mPostMessageAdapter == null) {
            mPostMessageAdapter = new MyselfPostMessageAdapter(getContext(),model);
            rvMyPostList.setAdapter(mPostMessageAdapter);
            mPostMessageAdapter.setItemOnClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    ToastManager.showToast(getContext(), "onItemClick");
                    CircleDetailActivity.start(getContext(),
                            String.valueOf(myselfPostModel.getData().getCommunityList().get(position).getCommunityId()),"0");
                }
            });
        } else {
            rvMyPostList.setAdapter(mPostMessageAdapter);
            mPostMessageAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initTopBar() {
        initTitle("我的帖子");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    private void getNetWork(String customerId, String page) {
        LogUtil.e(TAG, customerId + page);
        subscription = Network.getYeapaoApi()
                .requestMyListPost(customerId, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<MyselfPostModel> modelObserver = new Observer<MyselfPostModel>() {
        @Override
        public void onCompleted() {
            swipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());
        }

        @Override
        public void onNext(MyselfPostModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                if (currentPage == 0) {
                    myselfPostModel = model;
                    refreshView(model);
                } else {
                    showResultAdd(model);
                    LogUtil.e(TAG + "  add model sum", String.valueOf(model.getData().getCommunityList().size()));
                    myselfPostModel.getData().getCommunityList().addAll(model.getData().getCommunityList());
                }

                totalPage = model.getData().getTotalPage();
            }
        }
    };

    private void showResultAdd(MyselfPostModel model) {
        mPostMessageAdapter.loadMore(model);
    }


}
