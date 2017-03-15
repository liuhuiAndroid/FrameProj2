package com.android.loter.ui.fragment.first.child;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.activity.ProductDetailActivity;
import com.android.loter.ui.adapter.CommonAdapter;
import com.android.loter.ui.adapter.MultiItemTypeAdapter;
import com.android.loter.ui.adapter.base.ViewHolder;
import com.android.loter.ui.adapter.wrapper.LoadMoreWrapper;
import com.android.loter.ui.base.BaseBackFragment;
import com.android.loter.ui.widget.MyPtrClassicFrameLayout;
import com.android.loter.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by we-win on 2017/3/15.
 */

public class HomeProductFragment extends BaseBackFragment {

    @BindView(R.id.tv_empty)
    TextView mTvEmpty;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.ptr_layout)
    MyPtrClassicFrameLayout mPtrLayout;

    private GridLayoutManager mGridLayoutManager;
    private List<String> mStringList;
    private CommonAdapter mCommonAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;

    public static HomeProductFragment newInstance() {
        HomeProductFragment fragment = new HomeProductFragment();
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_home_product;
    }

    @Override
    protected void initData() {
        mStringList = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        mStringList.clear();
        for (int i = 0; i < 10; i++) {
            mStringList.add("1");
        }
        if (mStringList != null && mStringList.size() > 0) {
            showData();
            mTvEmpty.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mTvEmpty.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
            if (mPtrLayout.isShown()) {
                mPtrLayout.refreshComplete();
            }
        }
    }

    private void showData() {
        if (mCommonAdapter == null) {
            mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
            mRecyclerView.setLayoutManager(mGridLayoutManager);
            mCommonAdapter = new CommonAdapter<String>(getActivity(), R.layout.layout_product, mStringList) {
                @Override
                protected void convert(ViewHolder holder, String s, int position) {
                    holder.setImageUrl(R.id.iv_product, "http://p0.meituan.net/movie/07b7f22e2ca1820f8b240f50ee6aa269481512.jpg");

                    ImageView imgGood = holder.getView(R.id.iv_product);
                    ViewGroup.LayoutParams params = imgGood.getLayoutParams();
                    params.height = (ScreenUtil.width(getActivity()).px >> 1) / 2 - ScreenUtil.dipToPx(getActivity(), 16);
                    imgGood.setLayoutParams(params);
                }
            };
            mCommonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    openActivity(ProductDetailActivity.class);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
            mLoadMoreWrapper = new LoadMoreWrapper(mCommonAdapter);
            mLoadMoreWrapper.setLoadMoreView(LayoutInflater.from(getActivity()).inflate(R.layout.footer_view_load_more, mRecyclerView, false));
            mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < 6; i++) {
                                mStringList.add("1");
                            }
                            mLoadMoreWrapper.setLoadAll(false);
                            mLoadMoreWrapper.notifyDataSetChanged();
                        }
                    }, 2000);
                }
            });
            mRecyclerView.setAdapter(mLoadMoreWrapper);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            //            mRecyclerView.addItemDecoration(new DividerGridItemDecoration(ProductActivity.this, 0));
        } else {
            mCommonAdapter.notifyDataSetChanged();
        }

        if (mPtrLayout.isShown()) {
            mPtrLayout.refreshComplete();
        }

    }

    @Override
    protected void bindEvent() {
        mPtrLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                if (mGridLayoutManager != null) {
                    boolean result = false;
                    if (mGridLayoutManager.findFirstVisibleItemPosition() == 0) {
                        final View topChildView = mRecyclerView.getChildAt(0);
                        result = topChildView.getTop() == 0;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
