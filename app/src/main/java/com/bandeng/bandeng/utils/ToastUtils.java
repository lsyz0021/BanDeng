package com.bandeng.bandeng.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Author：Li ChuanWu on 2017/1/4
 * Blog  ：http://blog.csdn.net/lsyz0021/
 */
public class ToastUtils {

    private static Toast sToast;
    private static Point sOutSize;
    private static WindowManager sWindowManager;

    /**
     * 显示短时间吐司，不会连续显示
     *
     * @param context 建议使用全局的context，避免使用activity的以免引起内存泄露
     * @param msg     要显示的信息
     */
    public static void show(Context context, String msg) {
        if (sWindowManager == null) {
            sWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        if (sOutSize == null) {
            sOutSize = new Point();
        }
        sWindowManager.getDefaultDisplay().getSize(sOutSize);
        int y = sOutSize.y;
        if (sToast == null) {
            sToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        sToast.setGravity(Gravity.TOP, 0, 3 * y / 4); // 自定义显示屏幕的3/4的位置
        sToast.setDuration(Toast.LENGTH_SHORT);
        sToast.setText(msg);
        sToast.show();
    }

    /**
     * 显示长时间吐司，不会连续显示
     *
     * @param context 建议使用全局的context，避免使用activity的以免引起内存泄露
     * @param msg     要显示的信息
     */
    public static void showLong(Context context, String msg) {
        if (sWindowManager == null) {
            sWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        if (sOutSize == null) {
            sOutSize = new Point();
        }
        sWindowManager.getDefaultDisplay().getSize(sOutSize);
        int y = sOutSize.y;
        if (sToast == null) {
            sToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        }
        sToast.setGravity(Gravity.TOP, 0, 3 * y / 4);// 自定义显示屏幕的3/4的位置
        sToast.setDuration(Toast.LENGTH_LONG);
        sToast.setText(msg);
        sToast.show();
    }

    /**
     * 显示短时间吐司，连续显示
     *
     * @param context 建议使用全局的context，避免使用activity的以免引起内存泄露
     * @param msg     要显示的信息
     */
    public static void showSequence(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
