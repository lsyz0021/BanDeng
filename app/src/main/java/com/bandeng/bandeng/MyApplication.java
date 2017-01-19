package com.bandeng.bandeng;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;

/**
 * Author：Li ChuanWu on 2017/1/19
 * Blog  ：http://blog.csdn.net/lsyz0021/
 */
public class MyApplication extends Application {
    private static Context sContext;
    private static MyApplication sInstance;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        sContext = context;
        sInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        sInstance = this;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        try {
            ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            MemoryInfo memoryInfo = new MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            Log.e("ProgramInitManager", "local mem = " + memoryInfo.availMem + ",isLow = " + memoryInfo.lowMemory + ",threshold = " + memoryInfo.threshold);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                Log.e("ProgramInitManager", "totalMem = " + memoryInfo.totalMem);
            }
            Runtime runtime = Runtime.getRuntime();
            Log.e("ProgramInitManager", "totalMemory = " + runtime.totalMemory() + ",freeMemory = " + runtime.freeMemory() + ",max = " + runtime.maxMemory());
        } catch (Throwable th) {
            th.printStackTrace();
        }

    }

    public static Context getContext() {
        return sContext;
    }

    public static MyApplication getInstance() {
        return sInstance;
    }
}
