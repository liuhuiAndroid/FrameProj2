package com.android.loter.ui.fragment.fifth.child;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.adapter.MyMessageFragmentPagerAdapter;
import com.android.loter.ui.base.BaseBackFragment;
import com.android.loter.ui.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by we-win on 2017/3/22.
 */

public class MyMessageFragment extends BaseBackFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.vp_task)
    ViewPager mVpTask;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public static BaseFragment newInstance() {
        MyMessageFragment myOrderFragment = new MyMessageFragment();
        return myOrderFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_my_order;
    }

    @Override
    protected void initData() {
        mTvTitle.setText("消息");
        MyMessageFragmentPagerAdapter myMessageFragmentPagerAdapter = new MyMessageFragmentPagerAdapter(getChildFragmentManager());
        mVpTask.setAdapter(myMessageFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mVpTask);

        //防止频繁的销毁视图
        mVpTask.setOffscreenPageLimit(3);
    }

    @Override
    protected void bindEvent() {

    }

}
