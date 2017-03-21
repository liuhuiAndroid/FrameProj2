package com.android.loter.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.loter.ui.factory.OrderFragmentFactory;

/**
 * Created by we-win on 2017/3/14.
 */

public class MyOrderFragmentPagerAdapter extends SmartFragmentStatePagerAdapter {

    private String[] mTitles = new String[]{"待付款", "待发货", "待收货","待评价"};

    public MyOrderFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return OrderFragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
