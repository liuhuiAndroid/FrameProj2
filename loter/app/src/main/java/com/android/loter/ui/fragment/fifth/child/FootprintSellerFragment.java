package com.android.loter.ui.fragment.fifth.child;

import com.android.loter.R;
import com.android.loter.ui.base.BaseFragment;

/**
 * Created by we-win on 2017/3/22.
 */

public class FootprintSellerFragment extends BaseFragment {

    public static BaseFragment newInstance() {
        FootprintSellerFragment footprintSellerFragment = new FootprintSellerFragment();
        return footprintSellerFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_footprint_seller;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }
}
