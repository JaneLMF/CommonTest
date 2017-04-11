package com.common.application;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.common.utlis.CustomCrashHandler;
import com.common.utlis.ULog;

/**
 *  Screen adaptation
 */
public class BaseApplication extends Application {

    private static final String TAG = BaseApplication.class.getSimpleName();
    public static float density = -1.0f;
    public static float pixelHeight = -1.0f;
    public static float dpiHeight = -1.0f;
    public static float pixelWidth = -1.0f;
    public static float dpiWidth = -1.0f;

    @Override
    public void onCreate() {
        super.onCreate();
        ULog.init(getPackageName());
        ULog.d(TAG, "Enter the onCreate()");
        CustomCrashHandler.getInstance().setCustomCrashHanler(this);
        initScreen();
    }

    private void initScreen(){
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(localDisplayMetrics);

        if (density < 0.0f) {
            density = localDisplayMetrics.density;

            pixelHeight = localDisplayMetrics.heightPixels;
            if (pixelHeight >= 672 && pixelHeight <= 720) {
                pixelHeight = 720;
            }

            if (pixelHeight >= 1008 && pixelHeight <= 1080) {
                pixelHeight = 1080;
            }

            pixelWidth = localDisplayMetrics.widthPixels;

            dpiHeight = pixelHeight / density;
            dpiWidth = pixelWidth / density;

        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
