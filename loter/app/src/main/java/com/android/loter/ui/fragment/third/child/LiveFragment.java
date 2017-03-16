package com.android.loter.ui.fragment.third.child;

import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.adapter.CommonAdapter;
import com.android.loter.ui.adapter.base.ViewHolder;
import com.android.loter.ui.adapter.wrapper.LoadMoreWrapper;
import com.android.loter.ui.base.BaseFragment;
import com.android.loter.ui.decoration.DividerGridItemDecoration;
import com.android.loter.ui.widget.MyPtrClassicFrameLayout;
import com.android.loter.ui.widget.SelectPopupWindow;
import com.android.loter.util.AnimationUtils;
import com.android.loter.util.ScreenUtil;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by we-win on 2017/3/6.
 */

public class LiveFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_empty)
    TextView mTvEmpty;
    @BindView(R.id.ptr_layout)
    MyPtrClassicFrameLayout mPtrLayout;
    @BindView(R.id.viewMask)
    View mViewMask;
    @BindView(R.id.rl_container)
    RelativeLayout mRlContainer;

    private LinearLayoutManager mLinearLayoutManager;
    private List<String> mStringList;
    private CommonAdapter mCommonAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;

    public static BaseFragment newInstance() {
        LiveFragment liveFragment = new LiveFragment();
        return liveFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initData() {
        mStringList = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        mStringList.clear();
        for (int i = 0; i < 3; i++) {
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
            mLinearLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mCommonAdapter = new CommonAdapter<String>(getActivity(), R.layout.layout_live, mStringList) {
                @Override
                protected void convert(ViewHolder holder, String s, int position) {
                    View view = holder.getView(R.id.iv_live);
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layoutParams.height = ScreenUtil.width(baseActivity).px ;
                    view.setLayoutParams(layoutParams);

                    holder.setImageUrl(R.id.iv_live, "http://p0.meituan.net/movie/07b7f22e2ca1820f8b240f50ee6aa269481512.jpg");
                    holder.setImageUrl(R.id.iv_avatar, "http://p0.meituan.net/movie/07b7f22e2ca1820f8b240f50ee6aa269481512.jpg");

                    //==============
                    RecyclerView recyclerView = holder.getView(R.id.rv_product);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity,3);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    CommonAdapter commonAdapter = new CommonAdapter<String>(_mActivity,R.layout.layout_live_product,mStringList){

                        @Override
                        protected void convert(ViewHolder holder, String s, int position) {

                        }
                    };
                    recyclerView.setAdapter(commonAdapter);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
//                    recyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity(), 0));
                }
            };
            mLoadMoreWrapper = new LoadMoreWrapper(mCommonAdapter);
            mLoadMoreWrapper.setLoadMoreView(LayoutInflater.from(getActivity()).inflate(R.layout.footer_view_load_more, mRecyclerView, false));
            mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < 3; i++) {
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
            mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity(), 0));
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
                if (mLinearLayoutManager != null) {
                    boolean result = false;
                    if (mLinearLayoutManager.findFirstVisibleItemPosition() == 0) {
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

    /**
     * 分类
     */
    @OnClick(R.id.iv_sort)
    public void mIvSort() {
        String[] stringArray = getResources().getStringArray(R.array.live_sort);
        SelectPopupWindow menuWindow_immediately_indiana = new SelectPopupWindow(_mActivity,stringArray, new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                AnimationUtils.hideAlpha(mViewMask);
                StatusBarUtil.setColor(_mActivity, getResources().getColor(R.color.colorPrimary), 0);
            }
        });
        //设置layout在PopupWindow中显示的位置
        menuWindow_immediately_indiana.showAtLocation(mRlContainer, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        AnimationUtils.showAlpha(mViewMask);
        StatusBarUtil.setColor(_mActivity, getResources().getColor(R.color.colorPrimaryDialog), 0);

    }

}
