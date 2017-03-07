package com.android.loter.util;

import android.util.Log;

/**
 * Created by chenchao on 16/5/31.
 * cc@cchao.org
 */
public class Debug {

    private final static boolean isDebug = true;

    public static void i(String TAG, String content) {
        if (isDebug) {
            Log.i(TAG, content);
        }
    }

}
