package com.android.loter.ui.base;

import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.android.loter.AppManager;
import com.android.loter.R;
import com.android.loter.network.Network;
import com.android.loter.util.Debug;
import com.android.loter.util.ToastUtil;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import butterknife.ButterKnife;
import retrofit2.HttpException;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    private InputMethodManager inputMethodManager;

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        initData();
        bindEvent();
//        Debug.i(TAG, "getCurrentActivityName = " + getCurrentActivityName(BaseActivity.this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public void hideKeyboard(View view) {
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        }
    }

    /**
     * 跳转
     *
     * @param cls
     * @param requestCode
     */
    public void openActivity(Class<?> cls, int requestCode) {
        startActivityForResult(new Intent(this, cls), requestCode);
    }

    public void openActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    /**
     * 绑定布局文件
     *
     * @return
     */
    protected abstract int bindLayout();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 绑定控件事件
     */
    protected abstract void bindEvent();

    public void loadError(Throwable throwable, Context context,int from) {
        // 从LoginAcivity 进来就显示timeout
        if(from == 1){
            if (throwable instanceof SocketTimeoutException){
                ToastUtil.Infotoast(context, throwable.getMessage());
            }else{
                loadError(throwable,context);
            }
        }
    }

    public void loadError(Throwable throwable, Context context) {
        throwable.printStackTrace();
        Debug.i(TAG, "throwable ???");
        //        ToastUtil.Infotoast(App.getmAppContext(), throwable.getMessage());
        if (throwable instanceof Network.APIException) {

        } else if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            //            ex = new ApiException(throwable, httpException.code());
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                    //                    onPermissionError(ex);          //权限错误，需要实现
                    ToastUtil.Infotoast(context, getResources().getString(R.string.error_permission));
                    break;
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    //                    ex.setDisplayMessage(networkMsg);  //均视为网络错误
                    //                    onError(ex);
                    //                    Debug.i(TAG, "no!!! == throwable instanceof");
                    ToastUtil.Infotoast(context, getResources().getString(R.string.error_network));
                    break;
            }
        } else if (throwable instanceof JsonParseException
                || throwable instanceof JSONException
                || throwable instanceof ParseException) {
            ToastUtil.Infotoast(context, getResources().getString(R.string.error_parse));
        } else if (throwable instanceof UnknownHostException) {
            ToastUtil.Infotoast(context, getResources().getString(R.string.error_network));
        } else if (throwable instanceof SocketTimeoutException) {    //超时
            ToastUtil.Infotoast(context, getResources().getString(R.string.error_network));
        } else {
            ToastUtil.Infotoast(context, getResources().getString(R.string.error_unknow));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private String getCurrentActivityName(Context context) {
//        ActivityManager am = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
//
//
//        // get the info from the currently running task
//        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
//        ComponentName componentInfo = taskInfo.get(0).topActivity;
//        return componentInfo.getClassName();
//    }
}
