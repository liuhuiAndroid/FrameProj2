package com.android.loter.ui.fragment.fifth.child;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.loter.R;
import com.android.loter.inter.CallbackMineFragment;
import com.android.loter.ui.base.BaseFragment;
import com.android.loter.ui.fragment.fourth.child.ShoppingTrolleyFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by we-win on 2017/3/6.
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    private CallbackMineFragment mCallbackMineFragment;

    public static BaseFragment newInstance() {
        MineFragment mineFragment = new MineFragment();
        return mineFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {
        mTvTitle.setText(getResources().getString(R.string.minefragment_title));
    }

    @Override
    protected void bindEvent() {

    }

    @OnClick(R.id.img_avatr)
    public void mImgAvatr() {
        BaseFragment baseFragment = InfomationFragment.newInstance();
        start(baseFragment);
        mCallbackMineFragment.changeFragment(baseFragment);
    }

    @OnClick(R.id.tv_setting)
    public void mTvSetting() {
        start(SettingFragment.newInstance());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbackMineFragment = (CallbackMineFragment) context;
    }

    @OnClick(R.id.tv_call)
    public void mTvCall() {
        new MaterialDialog.Builder(_mActivity)
                .content("拨打4008-521-521")
                .positiveText("拨打")
                .negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:4001668845"));
                        startActivity(intent);
                    }
                })
                .show();
    }

    /**
     * 消息
     */
    @OnClick(R.id.tv_message)
    public void mTvMessage() {
        start(MyMessageFragment.newInstance());
    }

    /**
     * 购物车
     */
    @OnClick(R.id.tv_shopping_cart)
    public void mTvShoppingCart() {
        start(ShoppingTrolleyFragment.newInstance());
    }

    /**
     * 背包
     */
    @OnClick(R.id.tv_backpack)
    public void mTvBackpack() {
        start(MyBackpackFragment.newInstance());
    }

    /**
     * 好友
     */
    @OnClick(R.id.tv_friend)
    public void mTvFriend() {
        start(MyFriendFragment.newInstance());
    }

    /**
     * 提现明细
     */
    @OnClick(R.id.tv_withdrawed_cash_detail)
    public void mTvWithdrawedCashDetail() {
        start(WithdrawCashDetailFragment.newInstance());
    }

    /**
     * 充值
     */
    @OnClick(R.id.btn_recharge)
    public void btnRecharge() {
        start(RechargeFragment.newInstance());
    }

    /**
     * 提现
     */
    @OnClick(R.id.btn_withdraw_cash)
    public void btnWithdrawCash() {
        start(WithdrawCashFragment.newInstance());
    }

    /**
     * 我的订单
     */
    @OnClick(R.id.rl_my_order)
    public void rlMyOrder() {
        start(MyOrderFragment.newInstance());
    }

    /**
     * 我的邀请
     */
    @OnClick(R.id.rl_my_invite)
    public void rlMyInvite() {
        start(MyInviteFragment.newInstance());
    }

    /**
     * 我的足迹
     */
    @OnClick(R.id.rl_my_footprint)
    public void rlMyFootprint() {
        start(MyFootprintFragment.newInstance());
    }

    /**
     * 我的关注  商铺列表
     */
    @OnClick(R.id.tv_follow_seller)
    public void tvFollowSeller() {
        start(FollowSellerFragment.newInstance());
    }

    /**
     * 我的关注  商品列表
     */
    @OnClick(R.id.tv_follow_product)
    public void tvFollowProduct() {
        start(FollowProductFragment.newInstance());
    }

    /**
     * 我的关注  红人列表
     */
    @OnClick(R.id.tv_follow_idol)
    public void tvFollowIdol() {
        start(FollowIdolFragment.newInstance());
    }


}
