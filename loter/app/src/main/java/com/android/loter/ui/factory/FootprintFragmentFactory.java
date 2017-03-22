package com.android.loter.ui.factory;

import com.android.loter.ui.base.BaseFragment;
import com.android.loter.ui.fragment.fifth.child.FootprintIdolFragment;
import com.android.loter.ui.fragment.fifth.child.FootprintProductFragment;
import com.android.loter.ui.fragment.fifth.child.FootprintSellerFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by we-win on 2017/3/14.
 */

public class FootprintFragmentFactory {

    private static Map<Integer,BaseFragment> fragmentMap = new HashMap<Integer, BaseFragment>();
    public static BaseFragment createFragment(int position){
        BaseFragment baseFragment = null;
        baseFragment = fragmentMap.get(position);
        if(baseFragment == null){
            if(position == 0){
                baseFragment = FootprintSellerFragment.newInstance();
            }else if(position == 1){
                baseFragment = FootprintProductFragment.newInstance();
            }else if(position == 2){
                baseFragment = FootprintIdolFragment.newInstance();
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
