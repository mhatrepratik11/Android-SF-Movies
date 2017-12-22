package com.bsg.movies.utils;

import android.util.Log;

public class LogUtils {

    private static boolean LOG_ENABLE = true;
    private static String TAG = "PROJECT_DAIRY_FARM";

    public static void e(String Message) {
        if(LOG_ENABLE)
        Log.e(TAG, Message);
    }

    public static void d(String Message) {
        if(LOG_ENABLE)
        Log.d(TAG, Message);
    }

    public static void i(String Message) {
        if(LOG_ENABLE)
        Log.i(TAG, Message);
    }

    public static void v(String Message) {
        if(LOG_ENABLE)
        Log.v(TAG, Message);
    }

}
