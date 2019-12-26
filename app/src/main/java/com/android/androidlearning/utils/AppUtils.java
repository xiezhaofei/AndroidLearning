package com.android.androidlearning.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import java.lang.reflect.Method;

/**
 * Created by xiezhaofei on 2019-11-12
 * <p>
 * Describe:
 */
public class AppUtils {
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return "获取失败";
    }

    public static Point getScreenDevicePixels(@NonNull Context context) {
        Point point = new Point();

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager == null ? null : manager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            point.x = dm.widthPixels;
            point.y = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return point;
    }

    private static int screenWidthPixels;
    private static int screenHeightPixels;
    public static int getScreenHeightPixels(Context context) {
        if (context == null) {
            //Log.e("Can't get screen size while the activity is null!");
            return 0;
        }

        if (screenHeightPixels > 0) {
            return screenHeightPixels;
        }
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        Display display = manager.getDefaultDisplay();
        if (display != null) {
            display.getMetrics(dm);
            screenHeightPixels = dm.heightPixels;
        }
        return screenHeightPixels;
    }
    public static int getScreenWidthPixels(Context context) {

        if (context == null) {
            //Log.e("Can't get screen size while the activity is null!");
            return 0;
        }

        if (screenWidthPixels > 0) {
            return screenWidthPixels;
        }
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        Display display = manager.getDefaultDisplay();
        if (display != null) {
            display.getMetrics(dm);
            screenWidthPixels = dm.widthPixels;
        }
        return screenWidthPixels;
    }
}
