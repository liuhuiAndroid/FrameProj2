package com.android.loter.db;

import android.content.Context;

/**
 * Created by WE-WIN-027 on 2016/8/25.
 *
 * @des ${TODO}
 */
public class DBHelper {


    private static DBHelper instance;
    private static Context mContext;

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    private AttendaceDao mAttendaceDao;
    private ShoppingTrolleyDao mShoppingTrolleyDao;


    /**
     * 取得DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            // 封装了数据库升级
            MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context, "app.db",
                    null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());

//            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,
//                    "app.db", null);
//            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    private DBHelper() {
    }

    public static void init(Context context) {
        mContext = context;
        instance = new DBHelper();
        // 数据库对象

    }

    public static DBHelper getInstance() {
        return instance;
    }

    public AttendaceDao getAttendaceDao() {
        if (mAttendaceDao == null) {
            instance.setAttendaceDao(getDaoSession(mContext).getAttendaceDao());
        }
        return mAttendaceDao;
    }

    public void setAttendaceDao(AttendaceDao attendaceDao) {
        mAttendaceDao = attendaceDao;
    }


    public ShoppingTrolleyDao getShoppingTrolleyDao() {
        if (mShoppingTrolleyDao == null) {
            instance.setShoppingTrolleyDao(getDaoSession(mContext).getShoppingTrolleyDao());
        }
        return mShoppingTrolleyDao;
    }

    public void setShoppingTrolleyDao(ShoppingTrolleyDao shoppingTrolleyDao) {
        mShoppingTrolleyDao = shoppingTrolleyDao;
    }
}
