package com.android.loter.ui.fragment;

import com.android.loter.R;
import com.android.loter.ui.base.BaseFragment;

/**
 * Created by we-win on 2017/3/6.
 */

public class ProfitFragment extends BaseFragment {

    public static BaseFragment newInstance() {
        ProfitFragment profitFragment = new ProfitFragment();
        return profitFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_profit;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }
}
