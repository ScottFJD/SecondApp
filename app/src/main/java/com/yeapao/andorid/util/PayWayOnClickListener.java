package com.yeapao.andorid.util;

/**
 * Created by fujindong on 2017/11/24.
 */

public interface PayWayOnClickListener {
    void onPayWay(int status);

    void onCloseWindow();

    void gotoPay();
}
