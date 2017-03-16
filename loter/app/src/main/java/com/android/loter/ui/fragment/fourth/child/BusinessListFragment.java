package com.android.loter.ui.fragment.fourth.child;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.android.loter.R;
import com.android.loter.ui.adapter.MyBusinessFragmentPagerAdapter;
import com.android.loter.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by we-win on 2017/3/16.
 */

public class BusinessListFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.vp_task)
    ViewPager mVpTask;

    public static BusinessListFragment newInstance() {
        BusinessListFragment baseFragment = new BusinessListFragment();
        return baseFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_business_list;
    }

    @Override
    protected void initData() {
        MyBusinessFragmentPagerAdapter myBusinessFragmentPagerAdapter = new MyBusinessFragmentPagerAdapter(getChildFragmentManager());
        mVpTask.setAdapter(myBusinessFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mVpTask);

        //防止频繁的销毁视图
        mVpTask.setOffscreenPageLimit(3);
    }

    @Override
    protected void bindEvent() {

    }

    /**
     * 切换模式
     */
    @OnClick(R.id.iv_switch_mode)
    public void mIvSwitchMode(){
        start(BusinessMapFragment.newInstance(),SINGLETASK);
    }

}
