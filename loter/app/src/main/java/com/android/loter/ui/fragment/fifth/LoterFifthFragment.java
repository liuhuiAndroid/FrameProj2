package com.android.loter.ui.fragment.fifth;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.loter.R;
import com.android.loter.ui.base.BaseMainFragment;
import com.android.loter.ui.fragment.MineFragment;

/**
 * Created by we-win on 2017/3/14.
 */

public class LoterFifthFragment extends BaseMainFragment {
    public static LoterFifthFragment newInstance() {

        Bundle args = new Bundle();
        LoterFifthFragment fragment = new LoterFifthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.loter_fragment_first;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_first_container, MineFragment.newInstance());
        }
    }
}
