package com.android.loter.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.loter.ui.factory.MessageFragmentFactory;

/**
 * Created by we-win on 2017/3/14.
 */

public class MyMessageFragmentPagerAdapter extends SmartFragmentStatePagerAdapter {

    private String[] mTitles = new String[]{"系统消息", "商家消息", "朋友消息","陌生人消息"};

    public MyMessageFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MessageFragmentFactory.createFragment(position);
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
