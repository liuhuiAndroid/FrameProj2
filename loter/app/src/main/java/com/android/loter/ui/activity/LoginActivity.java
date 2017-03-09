package com.android.loter.ui.activity;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by we-win on 2017/3/9.
 */

public class LoginActivity extends BaseActivity {

    public static final int REQUEST_CODE = 100;
    public static final int RESULT_CODE = 101;

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;

    @Override
    protected int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        setImgBack(mImgBack);
        mTvTitle.setText(getResources().getString(R.string.loginactivity_title));
    }

    @Override
    protected void bindEvent() {

    }

    @OnClick(R.id.btn_login)
    public void login(){

    }

    @OnClick(R.id.btn_register)
    public void register(){
        openActivity(RegisterActivity.class,REQUEST_CODE);
    }

    @OnClick(R.id.btn_forget_password)
    public void forget_password(){
        openActivity(ForgetPasswordActivity.class);
    }

    @OnClick(R.id.img_wx)
    public void img_wx(){

    }

    @OnClick(R.id.img_qq)
    public void img_qq(){

    }

    @OnClick(R.id.img_sina)
    public void img_sina(){

    }
}
