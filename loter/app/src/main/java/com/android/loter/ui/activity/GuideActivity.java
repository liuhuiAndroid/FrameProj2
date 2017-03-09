package com.android.loter.ui.activity;

import android.support.v4.view.ViewPager;

import com.android.loter.R;
import com.android.loter.inter.OnButtonClickListener;
import com.android.loter.ui.adapter.GuideViewPagerAdapter;
import com.android.loter.ui.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by we-win on 2017/3/9.
 */

public class GuideActivity extends BaseActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected int bindLayout() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initData() {
        GuideViewPagerAdapter guideViewPagerAdapter = new GuideViewPagerAdapter(mViewPager,GuideActivity.this);
        guideViewPagerAdapter.setOnButtonClickListener(new OnButtonClickListener() {
            @Override
            public void onButtonClick() {
                openActivity(MainActivity.class);
            }
        });
        mViewPager.setAdapter(guideViewPagerAdapter);
    }

    @Override
    protected void bindEvent() {

    }

}
