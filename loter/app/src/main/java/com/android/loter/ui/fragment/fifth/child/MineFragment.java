package com.android.loter.ui.fragment.fifth.child;

import android.content.Context;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.inter.CallbackMineFragment;
import com.android.loter.ui.base.BaseFragment;

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
    public void mImgAvatr(){
        BaseFragment baseFragment = InfomationFragment.newInstance();
        start(baseFragment);
        mCallbackMineFragment.changeFragment(baseFragment);
    }

    @OnClick(R.id.tv_setting)
    public void mTvSetting(){
        start(SettingFragment.newInstance());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbackMineFragment = (CallbackMineFragment) context;
    }
}
