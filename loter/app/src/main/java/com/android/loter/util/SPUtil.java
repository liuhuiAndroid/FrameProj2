package com.android.loter.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by WE-WIN-027 on 2016/9/1.
 *
 * @des ${TODO}
 */
public class SPUtil {

    private final String PPG_DATA = "ppg_data";
    private final String USER_ID = "userid";
    private final String GUIDE = "guide";
    private final String IS_LOGIN = "isLogin";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SPUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(PPG_DATA, Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private void save(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    private void save(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    private void save(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    private String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    private int getInt(String key) {
        return sharedPreferences.getInt(key, -1);
    }

    private boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    //================================================================

    public void setUserId(String birth) {
        save(USER_ID, birth);
    }

    public String getUserId() {
        return getString(USER_ID);
    }

    public void setGUIDE(int is_first_open) {
        save(GUIDE, is_first_open);
    }

    public int getGUIDE() {
        return getInt(GUIDE);
    }

    public void setIS_LOGIN(int is_login) {
        save(IS_LOGIN, is_login);
    }

    public int getIS_LOGIN() {
        return getInt(IS_LOGIN);
    }

}
