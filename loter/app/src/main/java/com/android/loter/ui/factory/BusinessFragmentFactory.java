package com.android.loter.ui.factory;

import com.android.loter.ui.base.BaseFragment;
import com.android.loter.ui.fragment.fourth.child.BusinessProductListFragment;
import com.android.loter.ui.fragment.fourth.child.BusinessSellerListFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by we-win on 2017/3/14.
 */

public class BusinessFragmentFactory {

    private static final String TAG = "TaskFragmentFactory";
    private static Map<Integer,BaseFragment> fragmentMap = new HashMap<Integer, BaseFragment>();
    public static BaseFragment createFragment(int position){
        BaseFragment baseFragment = null;
        baseFragment = fragmentMap.get(position);
        if(baseFragment == null){
            if(position == 0){
                baseFragment = BusinessProductListFragment.newInstance();
            }else if(position == 1){
                baseFragment = BusinessSellerListFragment.newInstance();
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
