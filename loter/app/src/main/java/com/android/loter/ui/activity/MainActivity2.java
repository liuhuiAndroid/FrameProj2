package com.android.loter.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.android.loter.R;
import com.android.loter.inter.CallbackChangeFragment;
import com.android.loter.ui.base.BaseActivity;
import com.android.loter.ui.base.BaseMainFragment;
import com.android.loter.ui.fragment.BusinessFragment;
import com.android.loter.ui.fragment.LiveFragment;
import com.android.loter.ui.fragment.MineFragment;
import com.android.loter.ui.fragment.ProfitFragment;
import com.android.loter.ui.fragment.fifth.LoterFifthFragment;
import com.android.loter.ui.fragment.first.LoterFirstFragment;
import com.android.loter.ui.fragment.first.child.HomeFragment;
import com.android.loter.ui.fragment.fourth.LoterFourthFragment;
import com.android.loter.ui.fragment.second.LoterSecondFragment;
import com.android.loter.ui.fragment.third.LoterThirdFragment;
import com.android.loter.ui.widget.BottomBar;
import com.android.loter.ui.widget.BottomBarTab;
import com.android.loter.util.BusUtil;
import com.android.loter.util.CommonEvent;
import com.android.loter.util.log.Logger;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.helper.FragmentLifecycleCallbacks;


/**
 * Created by we-win on 2017/3/14.
 */

public class MainActivity2 extends BaseActivity implements BaseMainFragment.OnBackToFirstListener ,CallbackChangeFragment {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIVE = 4;
    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    private SupportFragment[] mFragments = new SupportFragment[5];

    @Override
    protected int bindLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mFragments[FIRST] = LoterFirstFragment.newInstance();
            mFragments[SECOND] = LoterSecondFragment.newInstance();
            mFragments[THIRD] = LoterThirdFragment.newInstance();
            mFragments[FOURTH] = LoterFourthFragment.newInstance();
            mFragments[FIVE] = LoterFifthFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH],
                    mFragments[FIVE]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = findFragment(LoterFirstFragment.class);
            mFragments[SECOND] = findFragment(LoterSecondFragment.class);
            mFragments[THIRD] = findFragment(LoterThirdFragment.class);
            mFragments[FOURTH] = findFragment(LoterFourthFragment.class);
            mFragments[FIVE] = findFragment(LoterFifthFragment.class);
        }

        mBottomBar.addItem(new BottomBarTab(this, R.mipmap.ic_launcher))
                .addItem(new BottomBarTab(this, R.mipmap.ic_launcher))
                .addItem(new BottomBarTab(this, R.mipmap.ic_launcher))
                .addItem(new BottomBarTab(this, R.mipmap.ic_launcher))
                .addItem(new BottomBarTab(this, R.mipmap.ic_launcher));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                SupportFragment currentFragment = mFragments[position];
                int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();

                // 如果不在该类别Fragment的主页,则回到主页;
                if (count > 1) {
                    if (currentFragment instanceof LoterFirstFragment) {
                        currentFragment.popToChild(HomeFragment.class, false);
                    } else if (currentFragment instanceof LoterSecondFragment) {
                        currentFragment.popToChild(ProfitFragment.class, false);
                    } else if (currentFragment instanceof LoterThirdFragment) {
                        currentFragment.popToChild(LiveFragment.class, false);
                    } else if (currentFragment instanceof LoterFourthFragment) {
                        currentFragment.popToChild(BusinessFragment.class, false);
                    } else if (currentFragment instanceof LoterFifthFragment) {
                        currentFragment.popToChild(MineFragment.class, false);
                    }
                    return;
                }


                // 这里推荐使用EventBus来实现 -> 解耦
                if (count == 1) {
                    // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
                    // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                    BusUtil.getBus().post(new CommonEvent().new TabSelectedEvent(position));
                }
            }
        });
    }

    @Override
    protected void bindEvent() {
        // 可以监听该Activity下的所有Fragment的18个 生命周期方法
        registerFragmentLifecycleCallbacks(new FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentSupportVisible(SupportFragment fragment) {
                Logger.i("onFragmentSupportVisible--->" + fragment.getClass().getSimpleName());
            }
        });
    }

    @Override
    public void onBackToFirstFragment() {
        mBottomBar.setCurrentItem(0);
    }

    @Override
    public void changeFragment(int which) {
        mBottomBar.setCurrentItem(which);
    }

}
