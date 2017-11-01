package com.yeapao.andorid.util;

import java.io.Serializable;

/**
 * Created by fujindong on 2017/10/24.
 */

public class MyLoaction implements Serializable {
    private double longitude;
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
