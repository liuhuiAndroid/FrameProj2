package com.android.loter.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.loter.R;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by we-win on 2017/3/15.
 */

public class ProductHeadersAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private static final int TYPE_PRODUCT = 0;
    private static final int TYPE_DETAIL = 1;
    private static final int TYPE_EVALUATE = 2;

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
        if(position == 0){
            convertView = mInflater.inflate(R.layout.item_product_detail_product, parent, false);
        }else if (position == 1){
            convertView = mInflater.inflate(R.layout.item_product_detail_detail, parent, false);
        }else{
            convertView = mInflater.inflate(R.layout.item_product_detail_evaluate, parent, false);
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


}
