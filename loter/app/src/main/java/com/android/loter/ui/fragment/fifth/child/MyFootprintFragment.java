package com.android.loter.ui.fragment.fifth.child;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.adapter.MyFootprintFragmentPagerAdapter;
import com.android.loter.ui.base.BaseBackFragment;
import com.android.loter.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by we-win on 2017/3/22.
 */

public class MyFootprintFragment extends BaseBackFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.vp_task)
    ViewPager mVpTask;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public static BaseFragment newInstance() {
        MyFootprintFragment myFootprintFragment = new MyFootprintFragment();
        return myFootprintFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_my_footprint;
    }

    @Override
    protected void initData() {
        mTvTitle.setText("我的足迹");
        MyFootprintFragmentPagerAdapter myFootprintFragmentPagerAdapter = new MyFootprintFragmentPagerAdapter(getChildFragmentManager());
        mVpTask.setAdapter(myFootprintFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mVpTask);

        //防止频繁的销毁视图
        mVpTask.setOffscreenPageLimit(3);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
