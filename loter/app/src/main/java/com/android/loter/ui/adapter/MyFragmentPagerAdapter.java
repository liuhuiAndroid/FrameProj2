package com.android.loter.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.android.loter.ui.factory.MainFragmentFactory;

/**
 * Created by we-win on 2017/3/6.
 */

public class MyFragmentPagerAdapter extends SmartFragmentStatePagerAdapter {

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return 5;
    }
}
