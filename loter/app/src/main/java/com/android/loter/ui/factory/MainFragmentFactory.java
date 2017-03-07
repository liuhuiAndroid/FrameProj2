package com.android.loter.ui.factory;

import com.android.loter.ui.base.BaseFragment;
import com.android.loter.ui.fragment.BusinessFragment;
import com.android.loter.ui.fragment.HomeFragment;
import com.android.loter.ui.fragment.LiveFragment;
import com.android.loter.ui.fragment.MineFragment;
import com.android.loter.ui.fragment.ProfitFragment;

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
                baseFragment = new HomeFragment();
            }else if(position == 1){
                baseFragment = new BusinessFragment();
            }else if(position == 2){
                baseFragment = new LiveFragment();
            }else if(position == 3){
                baseFragment = new ProfitFragment();
            }else if(position == 4){
                baseFragment = new MineFragment();
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
