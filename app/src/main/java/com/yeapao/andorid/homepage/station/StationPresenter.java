package com.yeapao.andorid.homepage.station;

import android.content.Context;

/**
 * Created by fujindong on 2017/11/21.
 */

public class StationPresenter implements StationConstract.Presenter {

    private static final String TAG  = "StationPresenter";


    private Context mContext;
    private StationConstract.View mView;

    public StationPresenter(Context context, StationConstract.View view) {
        mContext = context;
        mView = view;
        mView.setPresenter(this);

    }

    @Override
    public void start() {

    }
}
