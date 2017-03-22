package com.android.loter.ui.factory;

import com.android.loter.ui.base.BaseFragment;
import com.android.loter.ui.fragment.fifth.child.MessageFriendFragment;
import com.android.loter.ui.fragment.fifth.child.MessageSellerFragment;
import com.android.loter.ui.fragment.fifth.child.MessageStrangerFragment;
import com.android.loter.ui.fragment.fifth.child.MessageSystemFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by we-win on 2017/3/14.
 */

public class MessageFragmentFactory {

    private static Map<Integer,BaseFragment> fragmentMap = new HashMap<Integer, BaseFragment>();

    public static BaseFragment createFragment(int position){
        BaseFragment baseFragment = null;
        baseFragment = fragmentMap.get(position);
        if(baseFragment == null){
            if(position == 0){
                baseFragment = MessageSystemFragment.newInstance();
            }else if(position == 1){
                baseFragment = MessageSellerFragment.newInstance();
            }else if(position == 2){
                baseFragment = MessageFriendFragment.newInstance();
            }else if(position == 3){
                baseFragment = MessageStrangerFragment.newInstance();
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
