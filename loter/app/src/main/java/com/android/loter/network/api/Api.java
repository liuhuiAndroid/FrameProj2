package com.android.loter.network.api;

import android.database.Observable;

import com.android.loter.model.HttpResult;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by WE-WIN-027 on 2016/9/1.
 *
 * @des ${TODO}
 */
public interface Api {

    //用户登录
    @FormUrlEncoded
    @POST("Account/Login")
    Observable<HttpResult<Void>> login(@Field("deviceid") String deviceid);
}
