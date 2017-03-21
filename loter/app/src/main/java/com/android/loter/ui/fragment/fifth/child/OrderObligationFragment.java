package com.android.loter.ui.fragment.fifth.child;

import com.android.loter.R;
import com.android.loter.ui.base.BaseFragment;

/**
 * Created by we-win on 2017/3/21.
 */

public class OrderObligationFragment extends BaseFragment {

    public static BaseFragment newInstance() {
        OrderObligationFragment orderObligationFragment = new OrderObligationFragment();
        return orderObligationFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_oder_obligation;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }
}
