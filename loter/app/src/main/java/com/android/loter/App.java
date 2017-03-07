package com.android.loter;

import android.app.Application;

import com.android.loter.db.DBHelper;
import com.android.loter.util.SPUtil;

/**
 * Created by WE-WIN-027 on 2016/8/25.
 *
 * @des ${TODO}
 */
public class App extends Application {

    private static SPUtil spUtil;
    public static App mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
        DBHelper.init(this);
        spUtil = new SPUtil(this);
        new AppError(mAppContext).initUncaught();
    }

    public static SPUtil getSpUtil() {
        return spUtil;
    }

    public static App getmAppContext() {
        return mAppContext;
    }

}
