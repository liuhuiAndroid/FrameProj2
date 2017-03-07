package com.android.loter.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.loter.util.Debug;

import static com.android.loter.db.DaoMaster.dropAllTables;

/**
 * Created by WE-WIN-027 on 2016/9/5.
 *
 * @des ${TODO}
 */
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    private static final String TAG = "MySQLiteOpenHelper";
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Debug.i(TAG,"oldVersion = "+oldVersion);

        dropAllTables(db, true);//删除所有的表
        onCreate(db);//重新建表

    }

}
