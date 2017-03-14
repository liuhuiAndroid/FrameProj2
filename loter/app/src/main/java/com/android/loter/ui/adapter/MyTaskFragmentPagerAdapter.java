package com.android.loter.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.loter.ui.factory.TaskFragmentFactory;

/**
 * Created by we-win on 2017/3/14.
 */

public class MyTaskFragmentPagerAdapter extends SmartFragmentStatePagerAdapter {

    private String[] mTitles = new String[]{"直播任务", "转发任务", "阅读任务"};

    public MyTaskFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TaskFragmentFactory.createFragment(position);
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
