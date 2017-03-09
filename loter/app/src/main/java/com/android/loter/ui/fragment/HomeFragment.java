package com.android.loter.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.android.loter.R;
import com.android.loter.inter.OnItemClickListener;
import com.android.loter.ui.adapter.CommonAdapter;
import com.android.loter.ui.adapter.ImageLoopAdapter;
import com.android.loter.ui.adapter.base.ViewHolder;
import com.android.loter.ui.base.BaseFragment;
import com.android.loter.ui.decoration.MarginDecoration;
import com.android.loter.ui.widget.AdvertisingPoint;
import com.android.loter.ui.widget.MyNoSlippingViewPager;
import com.android.loter.ui.widget.MyPtrClassicFrameLayout;
import com.android.loter.util.ScreenUtil;
import com.android.loter.util.log.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by we-win on 2017/3/6.
 */

public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    //轮播图
    @BindView(R.id.viewPager)
    MyNoSlippingViewPager mViewPager;
    //包裹广告下方小红点
    @BindView(R.id.ll_point)
    LinearLayout mLlPoint;
    @BindView(R.id.rl_advertising)
    RelativeLayout mRlAdvertising;
    @BindView(R.id.recyclerView2)
    RecyclerView mRecyclerView2;
    @BindView(R.id.ptr_layout)
    MyPtrClassicFrameLayout mPtrLayout;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;

    //广告地址列表
    private List<String> imgUrlList;
    //广告小圆点
    private List<AdvertisingPoint> advertisingPointList;
    //之前显示的广告
    private int preAdvertisingSelect = -1;
    //当前显示广告
    private int nowAdvertisingSelect = 0;

    public static BaseFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {

        initBanner();
        List<String> stringList = new ArrayList<>();
        stringList.add("1 - ");
        stringList.add("2 - ");
        stringList.add("3 - ");
        stringList.add("4 - ");
        stringList.add("5 - ");
        stringList.add("6 - ");
        CommonAdapter commonAdapter = new CommonAdapter<String>(getActivity(), R.layout.layout_test, stringList) {

            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setImageUrl(R.id.iv_test, "http://p0.meituan.net/movie/07b7f22e2ca1820f8b240f50ee6aa269481512.jpg");
                holder.setText(R.id.tv_test, s + " : " + holder.getLayoutPosition());
            }
        };
        mRecyclerView.setAdapter(commonAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new MarginDecoration(getActivity()));

        mRecyclerView2.setAdapter(commonAdapter);
        mRecyclerView2.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView2.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView2.addItemDecoration(new MarginDecoration(getActivity()));

        //解决scrollView嵌套recyclerView卡顿
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView2.setNestedScrollingEnabled(false);
    }

    /**
     * 初始化轮播图
     */
    private void initBanner() {

        ViewGroup.LayoutParams layoutParams = mRlAdvertising.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ScreenUtil.width(baseActivity).px * 312 / 750;
        mRlAdvertising.setLayoutParams(layoutParams);

        // 轮播图测试数据
        imgUrlList = new ArrayList<>();
        advertisingPointList = new ArrayList<>();

        imgUrlList.add("http://p1.meituan.net/movie/f1e42208897d8674bb7aab89fb078baf487236.jpg");
        imgUrlList.add("http://p1.meituan.net/movie/aa3c2bac8f9aaa557e63e20d56e214dc192471.jpg");
        imgUrlList.add("http://p0.meituan.net/movie/07b7f22e2ca1820f8b240f50ee6aa269481512.jpg");

        if (imgUrlList != null && imgUrlList.size() == 1) {
            mLlPoint.setVisibility(View.INVISIBLE);
        } else {
            mLlPoint.setVisibility(View.VISIBLE);
        }
        advertisingPointList.clear();
        mLlPoint.removeAllViews();
        for (int i = 0; i < imgUrlList.size(); i++) {
            advertisingPointList.add(new AdvertisingPoint(baseActivity, mLlPoint, i == 0 ? true :
                    false));
        }
        changeAdvertisingPoint(nowAdvertisingSelect);
        if (imgUrlList.size() == 1) {
            mViewPager.setScanScroll(false);
        } else {
            mViewPager.setScanScroll(true);
        }

        ImageLoopAdapter imageLoopAdapter = new ImageLoopAdapter(mViewPager, imgUrlList);
        imageLoopAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Logger.i("position = " + position);
            }
        });
        mViewPager.setAdapter(imageLoopAdapter);
        mViewPager.setCurrentItem(imageLoopAdapter.getRealCurrentItem(nowAdvertisingSelect));
        imageLoopAdapter.setOnPageSelected(new ImageLoopAdapter.OnPageSelected() {
            @Override
            public void onPageSelected(int position) {
                changeAdvertisingPoint(position);
            }
        });
        if (imgUrlList != null && imgUrlList.size() > 1) {
            changeViewPagerPoint();
        }

    }

    @Override
    protected void bindEvent() {

        mPtrLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                if (mScrollView != null) {
                    boolean result = false;
                    if (mScrollView.getScrollY() == 0) {
                        result = true;
                    }
                    return result && PtrDefaultHandler
                            .checkContentCanBePulledDown(frame, content, header);
                } else {
                    return PtrDefaultHandler
                            .checkContentCanBePulledDown(frame, content, header);
                }
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if (mPtrLayout.isShown()) {
                    mPtrLayout.refreshComplete();
                }
            }
        });
        //显示时间
        mPtrLayout.setLastUpdateTimeRelateObject(this);
        //viewpager滑动时禁用下拉
        mPtrLayout.disableWhenHorizontalMove(true);
    }

    private boolean isChangeViewPagerPoint = false;

    // 轮播图每隔5秒循环
    private void changeViewPagerPoint() {
        if (isChangeViewPagerPoint) {
            return;
        }
        isChangeViewPagerPoint = true;
        Observable.interval(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Long aLong) {
                        int currentItem = mViewPager.getCurrentItem();
                        currentItem++;
                        mViewPager.setCurrentItem(currentItem);
                        //                        loopAdvertising.setCurrentItem(currentItem + imgUrlList.size() *
                        // 1000);
                    }
                });
    }

    /**
     * 修改循环广告下方小圆点
     *
     * @param position
     */
    private void changeAdvertisingPoint(int position) {
        preAdvertisingSelect = nowAdvertisingSelect;
        nowAdvertisingSelect = position;
        if (preAdvertisingSelect != -1) {
            advertisingPointList.get(preAdvertisingSelect).setFocus(false);
        }
        if (nowAdvertisingSelect != -1) {
            advertisingPointList.get(nowAdvertisingSelect).setFocus(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
