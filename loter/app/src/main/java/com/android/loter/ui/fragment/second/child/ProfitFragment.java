package com.android.loter.ui.fragment.second.child;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.android.loter.R;
import com.android.loter.ui.adapter.MyTaskFragmentPagerAdapter;
import com.android.loter.ui.base.BaseBackFragment;
import com.android.loter.ui.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by we-win on 2017/3/6.
 */

public class ProfitFragment extends BaseBackFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.vp_task)
    ViewPager mVpTask;

    public static BaseFragment newInstance() {
        ProfitFragment profitFragment = new ProfitFragment();
        return profitFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_profit;
    }

    @Override
    protected void initData() {
        MyTaskFragmentPagerAdapter myMainFragmentPagerAdapter = new MyTaskFragmentPagerAdapter(getChildFragmentManager());
        mVpTask.setAdapter(myMainFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mVpTask);

        //防止频繁的销毁视图
        mVpTask.setOffscreenPageLimit(3);
    }

    @Override
    protected void bindEvent() {

    }


}
