package com.android.androidlearning;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by xiezhaofei on 2019-10-31
 * <p>
 * Describe:
 */
public class MainApplication extends Application {

//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        String processName = getCurProcessName();
//        ALLog.d("MainApplication", "process name :" + processName);
//    }


    public static RefWatcher getRefWatcher(Context context) {
        MainApplication application = (MainApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
    }

    private String getCurProcessName() {
        //WeakReference<Application> reference = new WeakReference<Application>(this,new ReferenceQueue<Application>());
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return "获取失败";
    }
}
