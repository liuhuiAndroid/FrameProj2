package com.android.loter.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.loter.Constants;
import com.android.loter.model.HttpResult;
import com.android.loter.network.api.Api;
import com.android.loter.util.Debug;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by WE-WIN-027 on 2016/9/1.
 *
 * @des ${TODO}
 */
public class Network {

    private static final String TAG = "Network";
    public static Api api;
    private static OkHttpClient okHttpClient;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static Api getApi(Context context) {
        if (api == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Debug.i(TAG, message);
                }
            });
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(interceptor)
                    // 在每次请求中保存和添加cookie
                    .addInterceptor(new AddCookiesInterceptor(context))
                    .addInterceptor(new ReceivedCookiesInterceptor(context))
                    .retryOnConnectionFailure(true)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constants.DEFAULT_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            api = retrofit.create(Api.class);
        }
        return api;
    }


    /**
     * 对网络接口返回的Response进行分割操作
     *
     * @param response
     * @param <T>
     * @return
     */
    public static <T> Observable<T> flatResponse(final HttpResult<T> response) {
        return Observable.create(new Observable.OnSubscribe<T>() {

            @Override
            public void call(Subscriber<? super T> subscriber) {
//                response.status = -101;
                if (response.isSuccess()) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(response.data);
                    }
                } else {

                    if (!subscriber.isUnsubscribed()) {
                        //  这里好像只要一条就行 判断做在Base里面了
                        if (response.status == -101) {
                            subscriber.onError(new APIException(response.status,"UnLogin or Session timeout."));
                        } else if (response.status == -102) {
                            subscriber.onError(new APIException(response.status, "Missing required arguments."));
                        } else if (response.status == -103) {
                            subscriber.onError(new APIException(response.status, "Invalid parameter."));
                        } else if (response.status == -104) {
                            subscriber.onError(new APIException(response.status, "Submission fails."));
                        } else if (response.status == -105) {
                            subscriber.onError(new APIException(response.status, "Part submission."));
                        } else if (response.status == -199) {
                            subscriber.onError(new APIException(response.status, "The background system exception."));
                        } else if (response.status == -501) {
                            subscriber.onError(new APIException(response.status, "Username does not exist."));
                        } else if (response.status == -502) {
                            subscriber.onError(new APIException(response.status, "Wrong password.1"));
                        } else if (response.status == -503) {
                            subscriber.onError(new APIException(response.status, "Repetitive operation."));
                        } else if (response.status == -504) {
                            subscriber.onError(new APIException(response.status, "The user has no right to operate the equipment."));
                        } else if (response.status == -505) {
                            subscriber.onError(new APIException(response.status, "Devices have been frozen, cannot use."));
                        } else if (response.status == -506) {
                            subscriber.onError(new APIException(response.status, "Equipment has been lost, destruction of data."));
                        } else if (response.status == -507) {
                            subscriber.onError(new APIException(response.status, "No assignment in the region."));
                        } else {
                            subscriber.onError(new APIException(response.status, response.message));
                        }
                    }
                    return;
                }

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }

            }
        });
    }


    /**
     * 自定义异常，当接口返回的{link Response#code}不为{link Constant#OK}时，需要跑出此异常
     * eg：登陆时验证码错误；参数为传递等
     */
    public static class APIException extends Exception {
        public int code;
        public String message;

        public APIException(int code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }


    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    @NonNull
    public static RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }

    @NonNull
    public static MultipartBody.Part prepareFilePart(String partName, File file) {
        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    @NonNull
    public static MultipartBody.Part createPartString(String name, String value) {
//        RequestBody requestFile =
//                RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), value);
        return MultipartBody.Part.createFormData(name, value);
    }

}
