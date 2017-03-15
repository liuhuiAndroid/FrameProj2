package com.android.loter.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.android.loter.R;

/**
 * Created by we-win on 2017/3/15.
 */

public class ProductMutilItemAdapter extends RecyclerView.Adapter {

    private static final int TYPE_PRODUCT = 0;
    private static final int TYPE_DETAIL = 1;
    private static final int TYPE_EVALUATE = 2;
    private Context mContext;

    public ProductMutilItemAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_PRODUCT) {
            view = View.inflate(mContext, R.layout.item_product_detail_product, null);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);
            return new ProductViewHolder(view);
        } else if (viewType == TYPE_DETAIL) {
            view = View.inflate(mContext, R.layout.item_product_detail_detail, null);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);
            return new DetailViewHolder(view);
        } else if (viewType == TYPE_EVALUATE) {
            view = View.inflate(mContext, R.layout.item_product_detail_evaluate, null);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);
            return new EvaluateViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ProductViewHolder) {

        } else if (holder instanceof DetailViewHolder){

        }else if (holder instanceof EvaluateViewHolder){

        }
    }


    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        public ProductViewHolder(View itemView) {
            super(itemView);
        }

    }

    class DetailViewHolder extends RecyclerView.ViewHolder {

        public DetailViewHolder(View itemView) {
            super(itemView);
        }

    }

    class EvaluateViewHolder extends RecyclerView.ViewHolder {

        public EvaluateViewHolder(View itemView) {
            super(itemView);
        }
    }


}