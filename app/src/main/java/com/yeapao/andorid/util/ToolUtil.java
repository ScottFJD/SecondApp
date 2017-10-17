package com.yeapao.andorid.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by fujindong on 2017/10/17.
 */

public class ToolUtil {
    /**
     * 获取手机版本号
     * @param context
     * @return
     */
    public static String getVersionName(Context context){
        PackageManager manager = context.getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return info.versionName;
    }

}
