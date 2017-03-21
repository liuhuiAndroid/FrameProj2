package com.android.loter.ui.fragment.fifth.child;

import com.android.loter.R;
import com.android.loter.ui.base.BaseFragment;

/**
 * Created by we-win on 2017/3/21.
 */

public class OrderWaitingForDeliveryFragment extends BaseFragment{

    public static BaseFragment newInstance() {
        OrderWaitingForDeliveryFragment orderWaitingForDelivery = new OrderWaitingForDeliveryFragment();
        return orderWaitingForDelivery;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_order_waiting_for_delivery;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }
}
