package com.yeapao.andorid.homepage.sport_plan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.LoginActivity;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseFragment;
import com.yeapao.andorid.dialog.DialogCallback;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.homepage.message.MyMessageActivity;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.model.SportPlanDetailModel;
import com.yeapao.andorid.util.GlobalDataConstant;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/10/23.
 */

public class SportPlanFragment extends BaseFragment {
    private static final String TAG = "SportPlanFragment";

    private SportCollectFragment sportCollectFragment;
    private SportPlanDetailFragment sportPlanDetailFragment;
    private SportPlanDetailModel sportPlanDetailModel;

    private ImageView sportCollectImageView;
    private ImageView messageIcon;

    private boolean isDetail = false;


    public static SportPlanFragment newInstance() {
        return new SportPlanFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sprot_plan, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        messageIcon.setVisibility(View.GONE);
        messageIcon = (ImageView) view.findViewById(R.id.iv_sport_message);
        messageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (GlobalDataYepao.isLogin()) {
                        MyMessageActivity.start(getContext());
                    } else {
                        ToastManager.showToast(getContext(), "请先登陆");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        sportCollectImageView = (ImageView) view.findViewById(R.id.iv_sport_collect_icon);
        sportCollectImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogUtils.showCustomTwoButtonDialog(getContext(), getContext().getResources().getString(R.string.sport_plan_reset_title),
                        getContext().getResources().getString(R.string.sport_plan_reset_content),
                        "取消", "重置", new DialogCallback() {
                            @Override
                            public void onItemClick(int position) {

                            }

                            @Override
                            public void onLeftClick() {

                            }

                            @Override
                            public void onRightClick() {
                                requestResetSportPlan(String.valueOf(sportPlanDetailModel.getData().getProgrammeId()));
                            }
                        });
            }
        });
        if (GlobalDataYepao.getUser(getContext()) != null) {
            getSportDetail();
        } else {
            sportCollectImageView.setVisibility(View.GONE);

            sportCollectFragment = new SportCollectFragment();
            sportCollectFragment.setSportCollectFinishListener(new SportCollectFinishListener() {
                @Override
                public void sportCollectfinish(String careerType, String sportsCondition, String workForm) {
                    getNetWork(careerType, sportsCondition, workForm);
                }
            });
            android.support.v4.app.FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_sport_content, sportCollectFragment).commit();
        }
    }

    private void showFragment() {
        if (sportPlanDetailModel.getData().getMyPlan().equals("1")) {

            sportCollectImageView.setVisibility(View.VISIBLE);

            sportPlanDetailFragment = new SportPlanDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("sportDetail", sportPlanDetailModel);
            sportPlanDetailFragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_sport_content, sportPlanDetailFragment).commit();
        } else {

            sportCollectImageView.setVisibility(View.GONE);

            sportCollectFragment = new SportCollectFragment();
            sportCollectFragment.setSportCollectFinishListener(new SportCollectFinishListener() {
                @Override
                public void sportCollectfinish(String careerType, String sportsCondition, String workForm) {
                    getNetWork(careerType, sportsCondition, workForm);
                }
            });
            android.support.v4.app.FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_sport_content, sportCollectFragment).commit();
        }
    }

    private void getNetWork(String careerType, String sportsCondition, String workForm) {
        subscription = Network.getYeapaoApi()
                .requestSportDetail(GlobalDataYepao.getUser(getContext()).getId(),
                        String.valueOf(GlobalDataYepao.getMyLocation(getContext()).getLongitude()),
                        String.valueOf(GlobalDataYepao.getMyLocation(getContext()).getLatitude()),
                        careerType, sportsCondition, workForm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<SportPlanDetailModel> modelObserver = new Observer<SportPlanDetailModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(SportPlanDetailModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                sportPlanDetailModel = model;
                showFragment();
            }
        }
    };

    private void getSportDetail() {
        subscription = Network.getYeapaoApi()
                .getSportDetail(GlobalDataYepao.getUser(getContext()).getId(),
                        String.valueOf(GlobalDataYepao.getMyLocation(getContext()).getLongitude()),
                        String.valueOf(GlobalDataYepao.getMyLocation(getContext()).getLatitude()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserverGetDetail);
    }

    Observer<SportPlanDetailModel> modelObserverGetDetail = new Observer<SportPlanDetailModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(SportPlanDetailModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                sportPlanDetailModel = model;
                showFragment();
            }
        }
    };


    private void requestResetSportPlan(String programmeId) {
        LogUtil.e(TAG, programmeId);
        subscription = Network.getYeapaoApi()
                .requestSportPlanReset(programmeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserverResetPlan);
    }

    Observer<NormalDataModel> modelObserverResetPlan = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());
            ToastManager.showToast(getContext(), "重置失败");

        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                sportCollectFragment = new SportCollectFragment();
                sportCollectFragment.setSportCollectFinishListener(new SportCollectFinishListener() {
                    @Override
                    public void sportCollectfinish(String careerType, String sportsCondition, String workForm) {
                        getNetWork(careerType, sportsCondition, workForm);
                    }
                });
                android.support.v4.app.FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_sport_content, sportCollectFragment).commit();
            }
        }
    };
}
