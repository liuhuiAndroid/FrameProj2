package com.android.loter.ui.fragment.fifth.child;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.base.BaseBackFragment;
import com.android.loter.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by we-win on 2017/3/21.
 */

public class RechargeFragment extends BaseBackFragment {


    @BindView(R.id.cb_wechat)
    CheckBox cbWechat;
    @BindView(R.id.cb_alipay)
    CheckBox cbAlipay;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public static BaseFragment newInstance() {
        RechargeFragment rechargeFragment = new RechargeFragment();
        return rechargeFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_recharge;
    }

    @Override
    protected void initData() {
        mTvTitle.setText("充值");
    }

    @Override
    protected void bindEvent() {

    }

    @OnClick(R.id.rl_wechat)
    public void rlWechat() {
        if (!cbWechat.isChecked()) {
            cbWechat.setChecked(true);
            cbAlipay.setChecked(false);
        } else {
            cbWechat.setChecked(false);
        }
    }

    @OnClick(R.id.rl_alipay)
    public void rlAlipay() {
        if (!cbAlipay.isChecked()) {
            cbWechat.setChecked(false);
            cbAlipay.setChecked(true);
        } else {
            cbAlipay.setChecked(false);
        }
    }

    @OnClick(R.id.btn_confirm_payment)
    public void confirm_payment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
