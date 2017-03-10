package com.android.loter;

import android.app.Application;
import android.util.Log;

import com.android.loter.db.DBHelper;
import com.android.loter.util.SPUtil;
import com.android.loter.util.log.CrashlyticsTree;
import com.android.loter.util.log.Logger;
import com.android.loter.util.log.Settings;
import com.baidu.mapapi.SDKInitializer;
import com.squareup.leakcanary.LeakCanary;

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

        // 初始化日志功能
        Logger.initialize(
                new Settings()
                        .isShowMethodLink(true)
                        .isShowThreadInfo(false)
                        .setMethodOffset(0)
                        .setLogPriority(BuildConfig.DEBUG ? Log.VERBOSE : Log.ASSERT)
        );
        if (!BuildConfig.DEBUG) {
            // for release
            Logger.plant(new CrashlyticsTree());
        }

        // 初始化LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...

        // 百度地图在使用 SDK 各组间之前初始化 context 信息，传入ApplicationContext
        SDKInitializer.initialize(this);
    }

    public static SPUtil getSpUtil() {
        return spUtil;
    }

    public static App getmAppContext() {
        return mAppContext;
    }

}
