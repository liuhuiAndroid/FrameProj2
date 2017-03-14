package com.android.loter.ui.activity;

import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.base.BaseActivity;
import com.android.loter.util.PhoneUtil;
import com.android.loter.util.ToastUtil;

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
    public void login() {
        String username = mEtUsername.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            ToastUtil.Infotoast(LoginActivity.this, "手机号码不能为空");
            return;
        }
        if (!PhoneUtil.isMobile(username)) {
            ToastUtil.Infotoast(LoginActivity.this, "手机号码格式不正确");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.Infotoast(LoginActivity.this, "密码不能为空");
            return;
        }
        // TODO 掉接口

    }

    @OnClick(R.id.btn_register)
    public void register() {
        openActivity(RegisterActivity.class, REQUEST_CODE);
    }

    @OnClick(R.id.btn_forget_password)
    public void forget_password() {
        openActivity(ForgetPasswordActivity.class);
    }

    @OnClick(R.id.img_wx)
    public void img_wx() {

    }

    @OnClick(R.id.img_qq)
    public void img_qq() {

    }

    @OnClick(R.id.img_sina)
    public void img_sina() {

    }

    /**
     * 密码明暗文切换
     */
    @OnClick(R.id.iv_switch)
    public void iv_switch() {
        if (mEtPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
            mEtPassword.setInputType(129);
            Editable etable = mEtPassword.getText();
            Selection.setSelection(etable, etable.length());
        } else {
            mEtPassword
                    .setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            Editable etable = mEtPassword.getText();
            Selection.setSelection(etable, etable.length());
        }
    }

}
