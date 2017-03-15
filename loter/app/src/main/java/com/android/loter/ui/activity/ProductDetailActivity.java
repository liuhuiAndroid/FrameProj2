package com.android.loter.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.adapter.ProductHeadersAdapter;
import com.android.loter.ui.base.BaseActivity;

import butterknife.BindView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by we-win on 2017/3/15.
 */

public class ProductDetailActivity extends BaseActivity implements StickyListHeadersListView.OnStickyHeaderChangedListener{

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_product)
    RadioButton mTvProduct;
    @BindView(R.id.tv_detail)
    RadioButton mTvDetail;
    @BindView(R.id.tv_evaluate)
    RadioButton mTvEvaluate;
    @BindView(R.id.radioGroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.listView)
    StickyListHeadersListView mListView;

    @Override
    protected int bindLayout() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setImgBack(mImgBack);
        mTvTitle.setText(getResources().getString(R.string.productdetailactivity_title));

        ProductHeadersAdapter productHeadersAdapter = new ProductHeadersAdapter(ProductDetailActivity.this);
        mListView.setAdapter(productHeadersAdapter);
        mListView.setOnStickyHeaderChangedListener(this);
    }

    @Override
    protected void bindEvent() {


    }

    @Override
    public void onStickyHeaderChanged(StickyListHeadersListView l, View header, int itemPosition, long headerId) {
        if(itemPosition == 0){
            mRadioGroup.check(R.id.tv_product);
        }else if(itemPosition == 1){
            mRadioGroup.check(R.id.tv_detail);
        }else{
            mRadioGroup.check(R.id.tv_evaluate);
        }
    }
}
