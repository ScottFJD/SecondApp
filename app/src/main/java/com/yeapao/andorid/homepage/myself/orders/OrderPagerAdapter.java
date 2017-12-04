package com.yeapao.andorid.homepage.myself.orders;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by fujindong on 2017/9/18.
 */

public class OrderPagerAdapter extends FragmentPagerAdapter {


    private String[] titles;
    private Context context;

    private CangOrderFragment orderFragment;
    private StationOrderFragment stationorderFragment;



    public OrderPagerAdapter(FragmentManager fragmentManager, Context context, CangOrderFragment cangOrderFragment,
                             StationOrderFragment stationOrderFragment) {
        super(fragmentManager);
        this.context = context;
        titles = new String[]{"健身舱订单","跑站订单"};
        this.orderFragment = cangOrderFragment;
        this.stationorderFragment = stationOrderFragment;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 1) {
            return stationorderFragment;
        } else {
            return orderFragment;
        }

    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }
}
