package com.android.loter.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.loter.ui.factory.BusinessFragmentFactory;

/**
 * Created by we-win on 2017/3/14.
 */

public class MyBusinessFragmentPagerAdapter extends SmartFragmentStatePagerAdapter {

    private String[] mTitles = new String[]{"商铺", "商品"};

    public MyBusinessFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return BusinessFragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
