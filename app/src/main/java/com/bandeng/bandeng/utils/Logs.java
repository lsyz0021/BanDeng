package com.bandeng.bandeng.utils;

/**
 * Author：Li ChuanWu on 2017/1/4
 * Blog  ：http://blog.csdn.net/lsyz0021/
 */
public class Logs {
    private static final String TAG = "tag";
    private static boolean isOpen = true;

    public static void e(String msg) {
        if (isOpen)
            android.util.Log.e(TAG, msg);
    }
}
