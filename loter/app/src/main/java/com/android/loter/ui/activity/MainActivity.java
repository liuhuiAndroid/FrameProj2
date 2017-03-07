package com.android.loter.ui.activity;

import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.loter.R;
import com.android.loter.ui.adapter.MyFragmentPagerAdapter;
import com.android.loter.ui.base.BaseActivity;
import com.android.loter.ui.widget.NoScrollViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_main)
    NoScrollViewPager mVpMain;
    @BindView(R.id.rb_home)
    RadioButton mRbHome;
    @BindView(R.id.rb_business)
    RadioButton mRbBusiness;
    @BindView(R.id.rb_live)
    RadioButton mRbLive;
    @BindView(R.id.rb_profit)
    RadioButton mRbProfit;
    @BindView(R.id.rb_mine)
    RadioButton mRbMine;
    @BindView(R.id.rg_group)
    RadioGroup mRgGroup;
    @BindView(R.id.activity_main)
    LinearLayout mActivityMain;

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mVpMain.setAdapter(myFragmentPagerAdapter);
        mRgGroup.check(R.id.rb_home);
        //当点击底部按钮时切换页面
        mRgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_home) {
                    mVpMain.setCurrentItem(0, false);//去掉切换页面的动画
                } else if (i == R.id.rb_business) {
                    mVpMain.setCurrentItem(1, false);
                } else if (i == R.id.rb_live) {
                    mVpMain.setCurrentItem(2, false);
                } else if (i == R.id.rb_profit) {
                    mVpMain.setCurrentItem(3, false);
                } else if (i == R.id.rb_mine) {
                    mVpMain.setCurrentItem(4, false);
                }

            }
        });
        //防止频繁的销毁视图
        mVpMain.setOffscreenPageLimit(5);

    }

    @Override
    protected void bindEvent() {

    }

}
