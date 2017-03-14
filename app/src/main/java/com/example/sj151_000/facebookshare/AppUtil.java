package com.example.sj151_000.facebookshare;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by sj151_000 on 2017/3/8.
 */

public final class AppUtil {
    private AppUtil() {

    }

    public static boolean packageInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}