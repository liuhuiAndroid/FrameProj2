package com.android.loter.ui.fragment;

import com.android.loter.R;
import com.android.loter.ui.base.BaseFragment;

/**
 * Created by we-win on 2017/3/6.
 */

public class LiveFragment extends BaseFragment {

    public static BaseFragment newInstance() {
        LiveFragment liveFragment = new LiveFragment();
        return liveFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }
}
