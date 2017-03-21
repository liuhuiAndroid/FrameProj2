package com.android.loter.ui.fragment.fifth.child;

import com.android.loter.R;
import com.android.loter.ui.base.BaseBackFragment;
import com.android.loter.ui.base.BaseFragment;

/**
 * Created by we-win on 2017/3/21.
 */

public class WithdrawCashFragment extends BaseBackFragment {

    public static BaseFragment newInstance() {
        WithdrawCashFragment withdrawCashFragment = new WithdrawCashFragment();
        return withdrawCashFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_withdraw_cash;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }
}
