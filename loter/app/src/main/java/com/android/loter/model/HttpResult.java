package com.android.loter.model;


import com.android.loter.Constants;
import com.google.gson.annotations.SerializedName;

/**
 * Created by WE-WIN-027 on 2016/6/29.
 *
 *
 * @des ${TODO}
 */
public class HttpResult<T> {
    public int status;
    public String message;
    public String time;
    public boolean isSuccess() {
        return status == Constants.OK;
    }

    public @SerializedName("content") T data;


    public String getTime() {
        return time;
    }
}
