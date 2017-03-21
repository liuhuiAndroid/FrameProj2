package com.android.loter.ui.fragment.fifth.child;

import com.android.loter.R;
import com.android.loter.ui.base.BaseFragment;

/**
 * Created by we-win on 2017/3/21.
 */

public class OrderWaitingEvaluateFragment extends BaseFragment {

    public static BaseFragment newInstance() {
        OrderWaitingEvaluateFragment orderWaitingEvaluateFragment = new OrderWaitingEvaluateFragment();
        return orderWaitingEvaluateFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_order_waiting_evaluate;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }
}
