package com.yeapao.andorid.homepage.station.dynamiclesson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseFragment;
import com.yeapao.andorid.model.DynamicLessonListModel;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/11/22.
 */

public class DynamicLessonListFragment extends BaseFragment {
    private static final String TAG = "DynamicLessonListFragment";


    private RecyclerView rvDynamicLesson;
    private ImageView noLessonImageView;
    private String bgRes;
    private DynamicLessonAdapter dynamicLessonAdapter;
    private DynamicLessonListModel dynamicLessonListModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bgRes = getArguments().getString("data");
        LogUtil.e(TAG, bgRes);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamic_lesson, container, false);
        initView(view);
        getNetWork("0",bgRes);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view) {
        rvDynamicLesson = (RecyclerView) view.findViewById(R.id.rv_dynamic_lesson_list);
        noLessonImageView = (ImageView) view.findViewById(R.id.iv_no_lesson);
        rvDynamicLesson.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void showResult() {
        dynamicLessonAdapter = new DynamicLessonAdapter(getContext(),dynamicLessonListModel);
        rvDynamicLesson.setAdapter(dynamicLessonAdapter);
        dynamicLessonAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                DynamicLessonDetailActivity.start(getContext(),
                        String.valueOf(dynamicLessonListModel.getData().get(position).getCalendarId()));
            }
        });

    }

    private void getNetWork(String id, String date) {
        LogUtil.e(TAG, id+"  "+date);
        subscription = Network.getYeapaoApi()
                .requestDynamicLesson(id, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<DynamicLessonListModel> modelObserver = new Observer<DynamicLessonListModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(DynamicLessonListModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                dynamicLessonListModel = model;
                if (dynamicLessonListModel.getData().size() == 0) {
                    noLessonImageView.setVisibility(View.VISIBLE);
                    rvDynamicLesson.setVisibility(View.GONE);
                } else {
                    noLessonImageView.setVisibility(View.GONE);
                    rvDynamicLesson.setVisibility(View.VISIBLE);
                    showResult();
                }
            }
        }
    };

}
