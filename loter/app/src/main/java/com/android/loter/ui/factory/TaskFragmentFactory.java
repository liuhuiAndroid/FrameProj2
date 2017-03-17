package com.android.loter.ui.factory;

import com.android.loter.ui.base.BaseFragment;
import com.android.loter.ui.fragment.second.child.TaskForwardFragment;
import com.android.loter.ui.fragment.second.child.TaskLiveFragment;
import com.android.loter.ui.fragment.second.child.TaskReadFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by we-win on 2017/3/14.
 */

public class TaskFragmentFactory {

    private static final String TAG = "TaskFragmentFactory";
    private static Map<Integer,BaseFragment> fragmentMap = new HashMap<Integer, BaseFragment>();
    public static BaseFragment createFragment(int position){
        BaseFragment baseFragment = null;
        baseFragment = fragmentMap.get(position);
        if(baseFragment == null){
            if(position == 0){
//                baseFragment = HomeProductFragment.newInstance();
                baseFragment = TaskLiveFragment.newInstance();
            }else if(position == 1){
                baseFragment = TaskForwardFragment.newInstance();
            }else if(position == 2){
                baseFragment = TaskReadFragment.newInstance();
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
