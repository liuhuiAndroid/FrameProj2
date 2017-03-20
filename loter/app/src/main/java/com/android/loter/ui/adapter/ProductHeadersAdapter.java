package com.android.loter.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.util.imageloader.ImageLoaderUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by we-win on 2017/3/15.
 */

public class ProductHeadersAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private LayoutInflater mInflater;
    private String[] mCountries;
    private Context mContext;

    public ProductHeadersAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mCountries = context.getResources().getStringArray(R.array.product);
        mContext = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ProductViewHolder productViewHolder = null;
        DetailViewHolder detailViewHolder = null;
        EvaluateViewHolder evaluateViewHolder = null;

        int type = getItemViewType(position);

        if (convertView == null) {
            switch (type) {
                case TYPE_PRODUCT:
                    convertView = mInflater.inflate(R.layout.item_product_detail_product, parent, false);
                    productViewHolder = new ProductViewHolder(convertView);
                    convertView.setTag(productViewHolder);
                    break;
                case TYPE_DETAIL:
                    detailViewHolder = new DetailViewHolder();
                    convertView = mInflater.inflate(R.layout.item_product_detail_detail, parent, false);
                    convertView.setTag(detailViewHolder);
                    break;
                case TYPE_EVALUATE:
                    evaluateViewHolder = new EvaluateViewHolder();
                    convertView = mInflater.inflate(R.layout.item_product_detail_evaluate, parent, false);
                    convertView.setTag(evaluateViewHolder);
                    break;
            }


        } else {
            switch (type) {
                case TYPE_PRODUCT:
                    productViewHolder = (ProductViewHolder) convertView.getTag();
                    break;
                case TYPE_DETAIL:
                    detailViewHolder = (DetailViewHolder) convertView.getTag();
                    break;
                case TYPE_EVALUATE:
                    evaluateViewHolder = (EvaluateViewHolder) convertView.getTag();
                    break;
            }
        }

        switch (type) {
            case TYPE_PRODUCT:
                ImageLoaderUtil.getInstance().loadImage("http://p1.meituan.net/movie/f1e42208897d8674bb7aab89fb078baf487236.jpg", R.mipmap.ic_launcher, productViewHolder.mImageView);
                break;
            case TYPE_DETAIL:
                break;
            case TYPE_EVALUATE:
                break;
        }
        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        View view = new View(mContext);
        view.setVisibility(View.GONE);
        return view;
    }

    @Override
    public long getHeaderId(int position) {
        return mCountries[position].subSequence(0, 1).charAt(0);
    }

    private final int TYPE_PRODUCT = 0;
    private final int TYPE_DETAIL = 1;
    private final int TYPE_EVALUATE = 2;
    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_PRODUCT;
        }else if (position == 1){
            return TYPE_DETAIL;
        }else{
            return TYPE_EVALUATE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    class DetailViewHolder {


    }

    class EvaluateViewHolder {


    }

    static class ProductViewHolder {
        @BindView(R.id.imageView)
        ImageView mImageView;
        @BindView(R.id.tv_product_name)
        TextView mTvProductName;
        @BindView(R.id.tv_product_detail)
        TextView mTvProductDetail;
        @BindView(R.id.tv_product_price)
        TextView mTvProductPrice;
        @BindView(R.id.tv_product_sales)
        TextView mTvProductSales;
        @BindView(R.id.tv_product_buy)
        TextView mTvProductBuy;
        @BindView(R.id.tv_product_profit_buy)
        TextView mTvProductProfitBuy;
        @BindView(R.id.tv_product_profit_share)
        TextView mTvProductProfitShare;
        @BindView(R.id.tv_product_profit_delivery)
        TextView mTvProductProfitDelivery;
        @BindView(R.id.tv_product_profit_type)
        TextView mTvProductProfitType;

        ProductViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
