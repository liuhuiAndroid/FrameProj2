package com.android.loter.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by we-win on 2017/3/9.
 */

public class ForgetPasswordActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected int bindLayout() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setImgBack(mImgBack);
        mTvTitle.setText(getResources().getString(R.string.forgetpasswordactivity_title));
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
