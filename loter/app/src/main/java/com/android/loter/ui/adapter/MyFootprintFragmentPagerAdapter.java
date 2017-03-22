package com.android.loter.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.loter.ui.factory.FootprintFragmentFactory;

/**
 * Created by we-win on 2017/3/14.
 */

public class MyFootprintFragmentPagerAdapter extends SmartFragmentStatePagerAdapter {

    private String[] mTitles = new String[]{"访问过的商家", "浏览过的商品", "看过的红人"};

    public MyFootprintFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FootprintFragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
