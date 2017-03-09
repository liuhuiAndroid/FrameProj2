package com.android.loter.ui.activity;

import android.content.Intent;

import com.android.loter.App;
import com.android.loter.R;
import com.android.loter.ui.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;

/**
 * Created by we-win on 2017/3/9.
 */

public class AppStartActivity extends BaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_app_start;
    }

    @Override
    protected void initData() {
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        int guide = App.getSpUtil().getGUIDE();
        if (guide != 1) {
            App.getSpUtil().setGUIDE(1);
            openActivity(GuideActivity.class);
            finish();
        } else {
            Observable.timer(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Long number) {
                            openActivity(MainActivity.class);
                            finish();
                        }
                    });
        }

    }

    @Override
    protected void bindEvent() {

    }

}
