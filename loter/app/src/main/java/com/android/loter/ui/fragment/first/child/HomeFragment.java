package com.android.loter.ui.fragment.first.child;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.android.loter.R;
import com.android.loter.inter.CallbackChangeFragment;
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
import com.android.loter.util.imageloader.ImageLoaderUtil;
import com.android.loter.util.log.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    //轮播图
    @BindView(R.id.viewPager)
    MyNoSlippingViewPager mViewPager;
    //包裹广告下方小红点
    @BindView(R.id.ll_point)
    LinearLayout mLlPoint;
    @BindView(R.id.rl_advertising)
    RelativeLayout mRlAdvertising;
    @BindView(R.id.ptr_layout)
    MyPtrClassicFrameLayout mPtrLayout;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.recyclerView_sharing_makes)
    RecyclerView mRecyclerViewSharingMakes;
    @BindView(R.id.recyclerView_lives)
    RecyclerView mRecyclerViewLives;
    @BindView(R.id.recyclerView_merchants)
    RecyclerView mRecyclerViewMerchants;
    @BindView(R.id.recyclerView_products)
    RecyclerView mRecyclerViewProducts;

    @BindView(R.id.iv_ad_1)
    ImageView mIvAd1;
    @BindView(R.id.iv_ad_2)
    ImageView mIvAd2;
    @BindView(R.id.iv_ad_3)
    ImageView mIvAd3;
    @BindView(R.id.iv_ad_4)
    ImageView mIvAd4;
    @BindView(R.id.iv_ad_5)
    ImageView mIvAd5;
    @BindView(R.id.iv_ad_6)
    ImageView mIvAd6;
    @BindView(R.id.iv_ad_7)
    ImageView mIvAd7;
    @BindView(R.id.iv_ad_8)
    ImageView mIvAd8;

    //广告地址列表
    private List<String> imgUrlList;
    //广告小圆点
    private List<AdvertisingPoint> advertisingPointList;
    //之前显示的广告
    private int preAdvertisingSelect = -1;
    //当前显示广告
    private int nowAdvertisingSelect = 0;

    private CallbackChangeFragment mCallbackChangeFragment;

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
        CommonAdapter commonAdapter = new CommonAdapter<String>(getActivity(), R.layout.layout_test, stringList) {

            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setImageUrl(R.id.iv_test, "http://p0.meituan.net/movie/07b7f22e2ca1820f8b240f50ee6aa269481512.jpg");
                holder.setText(R.id.tv_test, s + " : " + holder.getLayoutPosition());
            }
        };

        mRecyclerViewSharingMakes.setAdapter(commonAdapter);
        mRecyclerViewSharingMakes.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerViewSharingMakes.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewSharingMakes.addItemDecoration(new MarginDecoration(getActivity()));

        mRecyclerViewLives.setAdapter(commonAdapter);
        mRecyclerViewLives.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerViewLives.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewLives.addItemDecoration(new MarginDecoration(getActivity()));

        mRecyclerViewMerchants.setAdapter(commonAdapter);
        mRecyclerViewMerchants.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerViewMerchants.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewMerchants.addItemDecoration(new MarginDecoration(getActivity()));


        mRecyclerViewProducts.setAdapter(commonAdapter);
        mRecyclerViewProducts.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerViewProducts.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewProducts.addItemDecoration(new MarginDecoration(getActivity()));

        //解决scrollView嵌套recyclerView卡顿
        mRecyclerViewSharingMakes.setNestedScrollingEnabled(false);
        mRecyclerViewLives.setNestedScrollingEnabled(false);
        mRecyclerViewMerchants.setNestedScrollingEnabled(false);
        mRecyclerViewProducts.setNestedScrollingEnabled(false);

        ImageLoaderUtil.getInstance().loadImage("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1489394689&di=bd853f96f6aadf1e78a0b457ce7d12b6&src=http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1508/26/c7/11740466_1440561922117.jpg", R.mipmap.ic_launcher, mIvAd1);
        ImageLoaderUtil.getInstance().loadImage("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1489394689&di=bd853f96f6aadf1e78a0b457ce7d12b6&src=http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1508/26/c7/11740466_1440561922117.jpg", R.mipmap.ic_launcher, mIvAd2);
        ImageLoaderUtil.getInstance().loadImage("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1489394689&di=bd853f96f6aadf1e78a0b457ce7d12b6&src=http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1508/26/c7/11740466_1440561922117.jpg", R.mipmap.ic_launcher, mIvAd3);
        ImageLoaderUtil.getInstance().loadImage("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1489394689&di=bd853f96f6aadf1e78a0b457ce7d12b6&src=http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1508/26/c7/11740466_1440561922117.jpg", R.mipmap.ic_launcher, mIvAd4);
        ImageLoaderUtil.getInstance().loadImage("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1489394689&di=bd853f96f6aadf1e78a0b457ce7d12b6&src=http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1508/26/c7/11740466_1440561922117.jpg", R.mipmap.ic_launcher, mIvAd5);
        ImageLoaderUtil.getInstance().loadImage("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1489394689&di=bd853f96f6aadf1e78a0b457ce7d12b6&src=http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1508/26/c7/11740466_1440561922117.jpg", R.mipmap.ic_launcher, mIvAd6);
        ImageLoaderUtil.getInstance().loadImage("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1489394689&di=bd853f96f6aadf1e78a0b457ce7d12b6&src=http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1508/26/c7/11740466_1440561922117.jpg", R.mipmap.ic_launcher, mIvAd7);
        ImageLoaderUtil.getInstance().loadImage("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1489394689&di=bd853f96f6aadf1e78a0b457ce7d12b6&src=http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1508/26/c7/11740466_1440561922117.jpg", R.mipmap.ic_launcher, mIvAd8);
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbackChangeFragment = (CallbackChangeFragment)context;
    }

    /**
     * 更多任务
     */
    @OnClick(R.id.tv_more_tasks)
    public void mTvMoreTasks() {
        mCallbackChangeFragment.changeFragment(1);
    }

    /**
     * 更多视频
     */
    @OnClick(R.id.tv_more_lives)
    public void mTvMoreLives() {
        mCallbackChangeFragment.changeFragment(2);
    }

    /**
     * 更多商家
     */
    @OnClick(R.id.tv_more_merchants)
    public void mTvMoreMerchants() {
//        openActivity(BusinessActivity.class);

        HomeSellerFragment fragment = HomeSellerFragment.newInstance();
        start(fragment);
    }

    /**
     * 更多商品
     */
    @OnClick(R.id.tv_more_product)
    public void mTvMoreProduct() {
//        openActivity(ProductActivity.class);
        start(HomeProductFragment.newInstance());
    }

}
