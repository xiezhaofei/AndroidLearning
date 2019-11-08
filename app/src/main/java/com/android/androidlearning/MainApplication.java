package com.android.androidlearning;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.android.androidlearning.utils.ALLog;

/**
 * Created by xiezhaofei on 2019-10-31
 * <p>
 * Describe:
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        String processName = getCurProcessName();
        ALLog.d("MainApplication", "process name :" + processName);
    }

    private String getCurProcessName() {
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
