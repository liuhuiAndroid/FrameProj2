package com.android.loter.ui.fragment.fifth.child;

import com.android.loter.R;
import com.android.loter.ui.base.BaseFragment;

/**
 * Created by we-win on 2017/3/22.
 */

public class FootprintProductFragment extends BaseFragment {

    public static BaseFragment newInstance() {
        FootprintProductFragment footprintProductFragment = new FootprintProductFragment();
        return footprintProductFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_footprint_product;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }
}
