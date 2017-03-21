package com.android.loter.ui.fragment.fifth.child;

import com.android.loter.R;
import com.android.loter.ui.base.BaseFragment;

/**
 * Created by we-win on 2017/3/21.
 */

public class OrderReceivingGoodsFragment extends BaseFragment {

    public static BaseFragment newInstance() {
        OrderReceivingGoodsFragment orderReceivingGoodsFragment = new OrderReceivingGoodsFragment();
        return orderReceivingGoodsFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_order_receiving_goods;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }
}
