package com.android.loter.ui.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.loter.R;
import com.android.loter.inter.CallbackChangeFragment;
import com.android.loter.ui.adapter.MyMainFragmentPagerAdapter;
import com.android.loter.ui.base.BaseActivity;
import com.android.loter.ui.widget.NoScrollViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements CallbackChangeFragment{

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
    @BindView(R.id.vp_main)
    NoScrollViewPager mVpMain;

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        MyMainFragmentPagerAdapter myMainFragmentPagerAdapter = new MyMainFragmentPagerAdapter(getSupportFragmentManager());
        mVpMain.setAdapter(myMainFragmentPagerAdapter);
        mRgGroup.check(R.id.rb_home);
        //当点击底部按钮时切换页面
        mRgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_home) {
                    mVpMain.setCurrentItem(0, false);//去掉切换页面的动画
                } else if (i == R.id.rb_profit) {
                    mVpMain.setCurrentItem(1, false);
                } else if (i == R.id.rb_live) {
                    mVpMain.setCurrentItem(2, false);
                } else if (i == R.id.rb_business) {
                    mVpMain.setCurrentItem(3, false);
                } else if (i == R.id.rb_mine) {
                    //                    mVpMain.setCurrentItem(4, false);
                    openActivity(LoginActivity.class);
                }

            }
        });
        //防止频繁的销毁视图
        mVpMain.setOffscreenPageLimit(5);

    }

    @Override
    protected void bindEvent() {

    }


    @Override
    public void changeFragment(int which) {
        mVpMain.setCurrentItem(which, false);
    }

}
