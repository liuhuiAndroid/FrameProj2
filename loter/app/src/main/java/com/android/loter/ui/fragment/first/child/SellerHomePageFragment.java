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
import com.android.loter.ui.adapter.CommonAdapter;
import com.android.loter.ui.adapter.MultiItemTypeAdapter;
import com.android.loter.ui.adapter.base.ViewHolder;
import com.android.loter.ui.adapter.wrapper.LoadMoreWrapper;
import com.android.loter.ui.base.BaseBackFragment;
import com.android.loter.util.ScreenUtil;
import com.android.loter.util.imageloader.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by we-win on 2017/3/15.
 */

public class SellerHomePageFragment extends BaseBackFragment {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_empty)
    TextView mTvEmpty;

    private GridLayoutManager mGridLayoutManager;
    private List<String> mStringList;
    private CommonAdapter mCommonAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;

    public static SellerHomePageFragment newInstance() {
        SellerHomePageFragment fragment = new SellerHomePageFragment();
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_seller_homepage;
    }

    @Override
    protected void initData() {
        mTvTitle.setText(getResources().getString(R.string.sellerhomepagefragment_title));

        ViewGroup.LayoutParams layoutParams = mImageView.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ScreenUtil.width(baseActivity).px * 312 / 750;
        mImageView.setLayoutParams(layoutParams);

        ImageLoaderUtil.getInstance().loadImage("http://p1.meituan.net/movie/aa3c2bac8f9aaa557e63e20d56e214dc192471.jpg", R.mipmap.ic_launcher, mImageView);

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
                    start(ProductDetailFragment.newInstance());
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


    }


    @Override
    protected void bindEvent() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
