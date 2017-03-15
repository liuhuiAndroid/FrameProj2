package com.android.loter.ui.base;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.loter.R;

/**
 * Created by YoKeyword on 16/2/7.
 */
public abstract class BaseBackFragment extends BaseFragment {

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }

    @Override
    protected void initToolbar(View rootView) {
        super.initToolbar(rootView);
        rootView.findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }
}
