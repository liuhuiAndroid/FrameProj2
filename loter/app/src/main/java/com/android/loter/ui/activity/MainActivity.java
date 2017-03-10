package com.android.loter.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.loter.R;
import com.android.loter.ui.adapter.MyFragmentPagerAdapter;
import com.android.loter.ui.base.BaseActivity;
import com.android.loter.ui.widget.NoScrollViewPager;
import com.android.loter.util.log.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    private static final int REQUECT_CODE_LOCATION = 2;

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
    protected void initData() {
        ButterKnife.bind(this);
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        if (mVpMain == null) {
            Logger.i("mVpMain == null");
            return;
        }
        if (myFragmentPagerAdapter == null) {
            Logger.i("myFragmentPagerAdapter == null");
            return;
        }
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
                    //                    mVpMain.setCurrentItem(4, false);
                    openActivity(LoginActivity.class);
                }

            }
        });
        //防止频繁的销毁视图
        mVpMain.setOffscreenPageLimit(5);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Toast.makeText(this, getResources().getString(R.string.mainactivity_permission_location), Toast.LENGTH_SHORT).show();

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUECT_CODE_LOCATION);

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUECT_CODE_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }


        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUECT_CODE_LOCATION);


        }
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUECT_CODE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                // Permission Denied
                Toast.makeText(this, getResources().getString(R.string.mainactivity_permission_location), Toast.LENGTH_SHORT).show();

            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
