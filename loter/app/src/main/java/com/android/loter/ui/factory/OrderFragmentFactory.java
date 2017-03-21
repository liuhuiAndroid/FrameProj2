package com.android.loter.ui.factory;

import com.android.loter.ui.base.BaseFragment;
import com.android.loter.ui.fragment.fifth.child.OrderObligationFragment;
import com.android.loter.ui.fragment.fifth.child.OrderReceivingGoodsFragment;
import com.android.loter.ui.fragment.fifth.child.OrderWaitingEvaluateFragment;
import com.android.loter.ui.fragment.fifth.child.OrderWaitingForDeliveryFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by we-win on 2017/3/14.
 */

public class OrderFragmentFactory {

    private static final String TAG = "OrderFragmentFactory";
    private static Map<Integer,BaseFragment> fragmentMap = new HashMap<Integer, BaseFragment>();
    public static BaseFragment createFragment(int position){
        BaseFragment baseFragment = null;
        baseFragment = fragmentMap.get(position);
        if(baseFragment == null){
            if(position == 0){
                baseFragment = OrderObligationFragment.newInstance();
            }else if(position == 1){
                baseFragment = OrderWaitingForDeliveryFragment.newInstance();
            }else if(position == 2){
                baseFragment = OrderReceivingGoodsFragment.newInstance();
            }else if(position == 3){
                baseFragment = OrderWaitingEvaluateFragment.newInstance();
            }
            if(baseFragment!=null){
                fragmentMap.put(position,baseFragment);
            }
        }

        return baseFragment;
    }

    public static void clearFragment(){
        fragmentMap.clear();
    }
}
