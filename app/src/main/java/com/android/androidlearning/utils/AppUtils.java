package com.android.androidlearning.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import java.lang.reflect.InvocationTargetException;
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


    public static void startDevelopmentActivity(Activity activity) {
        try {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
            activity.startActivity(intent);
        } catch (Exception e) {
            try {
                ComponentName componentName = new ComponentName("com.android.settings", "com.android.settings.DevelopmentSettings");
                Intent intent = new Intent();
                intent.setComponent(componentName);
                intent.setAction("android.intent.action.View");
                activity.startActivity(intent);
            } catch (Exception e1) {
                try {
                    Intent intent = new Intent("com.android.settings.APPLICATION_DEVELOPMENT_SETTINGS");//部分小米手机采用这种方式跳转
                    activity.startActivity(intent);
                } catch (Exception e2) {

                }

            }
        }
    }

    public static void writeDebugHwOverdrawOptions() {

        Class clz = null;
        try {
            clz = Class.forName("android.os.ServiceManager");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method method = null;
        Method methodcheckService = null;
        try {
            method = clz.getMethod("listServices");
            methodcheckService = clz.getMethod("checkService", String.class);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        String[] services = null;
        try {
            services = (String[]) method.invoke(clz);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Parcel data = null;
        for (String service : services) {
            IBinder obj = null;
            try {
                obj = (IBinder) methodcheckService.invoke(clz, service);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            if (obj != null) {
                data = Parcel.obtain();
                try {
                    obj.transact(('_'<<24)|('S'<<16)|('P'<<8)|'R', data, null, 0);
                } catch (RemoteException e) {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            data.recycle();
        }
    }
}
