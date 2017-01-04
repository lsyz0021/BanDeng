package com.bandeng.bandeng.utils;

import android.util.Log;

/**
 * Author：Li ChuanWu on 2017/1/4
 * Blog  ：http://blog.csdn.net/lsyz0021/
 */
public class L {
    private static final String TAG = "tag";
    private static boolean isOpen = true;

    public static void e(String msg) {
        if (isOpen)
            Log.e(TAG, msg);
    }
}
