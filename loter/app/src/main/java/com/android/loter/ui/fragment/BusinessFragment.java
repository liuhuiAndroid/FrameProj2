package com.android.loter.ui.fragment;

import com.android.loter.R;
import com.android.loter.ui.base.BaseFragment;

/**
 * Created by we-win on 2017/3/6.
 */

public class BusinessFragment extends BaseFragment {

    public static BaseFragment newInstance() {
        BusinessFragment baseFragment = new BusinessFragment();
        return baseFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_business;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }
}
