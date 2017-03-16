package com.android.loter.ui.fragment.first.child;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.adapter.ProductHeadersAdapter;
import com.android.loter.ui.base.BaseBackFragment;
import com.android.loter.ui.widget.SelectPopupWindow;
import com.android.loter.util.AnimationUtils;
import com.android.loter.util.BusUtil;
import com.android.loter.util.CommonEvent;
import com.android.loter.util.log.Logger;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;


/**
 * Created by we-win on 2017/3/15.
 */
public class ProductDetailFragment extends BaseBackFragment implements StickyListHeadersListView.OnStickyHeaderChangedListener {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.radioGroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.listView)
    StickyListHeadersListView mListView;
    @BindView(R.id.rl_container)
    RelativeLayout mRlContainer;
    @BindView(R.id.viewMask)
    View mViewMask;

    public static ProductDetailFragment newInstance() {
        ProductDetailFragment fragment = new ProductDetailFragment();
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_product_detail;
    }

    @Override
    protected void initData() {
        mTvTitle.setText(getResources().getString(R.string.productdetailfragment_title));

        ProductHeadersAdapter productHeadersAdapter = new ProductHeadersAdapter(_mActivity);
        mListView.setAdapter(productHeadersAdapter);
        mListView.setOnStickyHeaderChangedListener(this);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onStickyHeaderChanged(StickyListHeadersListView l, View header, int itemPosition, long headerId) {
        if (itemPosition == 0) {
            mRadioGroup.check(R.id.tv_product);
        } else if (itemPosition == 1) {
            mRadioGroup.check(R.id.tv_detail);
        } else {
            mRadioGroup.check(R.id.tv_evaluate);
        }
    }

    @OnClick(R.id.tv_product)
    public void mTvProduct() {
        mListView.setSelection(0);
        //        mListView.post(new Runnable() {
        //            @Override
        //            public void run() {
        //                mListView.smoothScrollToPosition(0);
        //            }
        //        });
    }

    @OnClick(R.id.tv_detail)
    public void mTvDetail() {
        mListView.setSelection(1);
        //        mListView.post(new Runnable() {
        //            @Override
        //            public void run() {
        //                mListView.smoothScrollToPosition(1);
        //            }
        //        });
    }

    @OnClick(R.id.tv_evaluate)
    public void mTvEvaluate() {
        mListView.setSelection(2);
        //        mListView.post(new Runnable() {
        //            @Override
        //            public void run() {
        //                mListView.smoothScrollToPosition(2);
        //            }
        //        });
    }

    @Override
    public void onResume() {
        super.onResume();
        BusUtil.getBus().post(new CommonEvent().new BottombarStatusEvent(8));
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            BusUtil.getBus().post(new CommonEvent().new BottombarStatusEvent(0));
        } else {
            BusUtil.getBus().post(new CommonEvent().new BottombarStatusEvent(8));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        BusUtil.getBus().post(new CommonEvent().new BottombarStatusEvent(0));
    }

    /**
     * 客服
     */
    @OnClick(R.id.bottom_tv_custom_service)
    public void mBottomTvCustomService() {

    }

    /**
     * 店铺
     */
    @OnClick(R.id.bottom_tv_seller)
    public void mBottomTvSeller() {
        start(SellerHomePageFragment.newInstance());
    }

    /**
     * 加入购物车
     */
    @OnClick(R.id.bottom_tv_add_shopping_car)
    public void mBottomTvAddShoppingCar() {

    }

    /**
     * 立即购买
     */
    @OnClick(R.id.bottom_buy_immediately)
    public void mBottomBuyImmediately() {
        SelectPopupWindow menuWindow_immediately_indiana = new SelectPopupWindow(_mActivity, new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                AnimationUtils.hideAlpha(mViewMask);
                Logger.i("setTranslucent 0");
                StatusBarUtil.setColor(_mActivity,getResources().getColor(R.color.colorPrimary),0);
            }
        });
        //设置layout在PopupWindow中显示的位置
        menuWindow_immediately_indiana.showAtLocation(mRlContainer, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        AnimationUtils.showAlpha(mViewMask);
        StatusBarUtil.setColor(_mActivity,getResources().getColor(R.color.colorPrimaryDialog),0);
        Logger.i("setTranslucent 100");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
