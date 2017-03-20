package com.android.loter.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.base.BaseActivity;
import com.android.loter.util.PhoneUtil;
import com.android.loter.util.RxCountDown;
import com.android.loter.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by we-win on 2017/3/9.
 */

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_sms)
    EditText mEtSms;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.et_invitation_code)
    EditText mEtInvitationCode;
    @BindView(R.id.btn_sms)
    Button mBtnSms;

    @Override
    protected int bindLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setImgBack(mImgBack);
        mTvTitle.setText(getResources().getString(R.string.registeractivity_title));
    }

    @Override
    protected void bindEvent() {

    }

    /**
     * 获取验证码
     */
    @OnClick(R.id.btn_sms)
    public void mBtnSms() {
        String username = mEtUsername.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            ToastUtil.Infotoast(RegisterActivity.this, "手机号码不能为空");
            return;
        }
        if (!PhoneUtil.isMobile(username)) {
            ToastUtil.Infotoast(RegisterActivity.this, "手机号码格式不正确");
            return;
        }

        subscription = RxCountDown.countdown(60)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        mBtnSms.setText(getResources().getString(R.string.registeractivity_sms_send));
                        mBtnSms.setClickable(true);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        mBtnSms.setText(integer + "s后重获");
                        mBtnSms.setClickable(false);
                    }
                });
    }

    /**
     * 密码明暗文切换
     */
    @OnClick(R.id.iv_switch)
    public void mIvSwitch() {
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

    /**
     * 注册
     */
    @OnClick(R.id.btn_register)
    public void mBtnRegister() {
        String username = mEtUsername.getText().toString().trim();
        String sms = mEtSms.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        String invitationCode = mEtInvitationCode.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            ToastUtil.Infotoast(RegisterActivity.this, "手机号码不能为空");
            return;
        }
        if (!PhoneUtil.isMobile(username)) {
            ToastUtil.Infotoast(RegisterActivity.this, "手机号码格式不正确");
            return;
        }
        if (TextUtils.isEmpty(sms)) {
            ToastUtil.Infotoast(RegisterActivity.this, "请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.Infotoast(RegisterActivity.this, "请输入密码");
            return;
        }

//        openActivity(ImproveInfomationActivity.class);
        finish();
    }

}
