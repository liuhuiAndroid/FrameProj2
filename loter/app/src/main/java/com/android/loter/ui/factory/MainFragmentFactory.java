package com.android.loter.ui.factory;

import com.android.loter.ui.base.BaseFragment;
import com.android.loter.ui.fragment.BusinessFragment;
import com.android.loter.ui.fragment.LiveFragment;
import com.android.loter.ui.fragment.MineFragment;
import com.android.loter.ui.fragment.ProfitFragment;
import com.android.loter.ui.fragment.first.child.HomeFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by we-win on 2017/3/6.
 */

public class MainFragmentFactory {

    private static final String TAG = "MainFragmentFactory";
    private static Map<Integer,BaseFragment> fragmentMap = new HashMap<Integer, BaseFragment>();
    public static BaseFragment createFragment(int position){
        BaseFragment baseFragment = null;
        baseFragment = fragmentMap.get(position);
        if(baseFragment == null){
            if(position == 0){
                baseFragment = HomeFragment.newInstance();
            }else if(position == 1){
                baseFragment = ProfitFragment.newInstance();
            }else if(position == 2){
                baseFragment = LiveFragment.newInstance();
            }else if(position == 3){
                baseFragment = BusinessFragment.newInstance();
            }else if(position == 4){
                baseFragment = MineFragment.newInstance();
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
