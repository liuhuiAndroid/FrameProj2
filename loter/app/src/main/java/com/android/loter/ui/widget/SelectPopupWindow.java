package com.android.loter.ui.widget;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.android.loter.R;
import com.android.loter.ui.adapter.CommonAdapter;
import com.android.loter.ui.adapter.base.ViewHolder;
import com.android.loter.ui.decoration.DividerGridItemDecoration;

import java.util.Arrays;

/**
 * Created by WE-WIN-027 on 2016/7/8.
 *
 * @des ${TODO}
 */
public class SelectPopupWindow extends PopupWindow {

    private static final String TAG = "SelectPopupWindow";

    /**
     * 立即购买
     *
     */
    public SelectPopupWindow(Context context,  final OnDismissListener onDismissListener) {
        View contentView = View.inflate(context, R.layout.dialog_panic_buying, null);
        this.setFocusable(true);
        this.setContentView(contentView);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.PopupBottomAnimation);
        this.setOnDismissListener(onDismissListener);
    }

    /**
     * 分类
     */
    public SelectPopupWindow(Context context, String[] strings,final OnDismissListener onDismissListener) {
        View contentView = View.inflate(context, R.layout.dialog_sort, null);
        this.setFocusable(true);
        this.setContentView(contentView);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.PopupBottomAnimation);
        this.setOnDismissListener(onDismissListener);

        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        CommonAdapter commonAdapter = new CommonAdapter<String>(context, R.layout.layout_dialog_live_sort, Arrays.asList(strings)) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setImageUrl(R.id.iv_sort, "http://p0.meituan.net/movie/07b7f22e2ca1820f8b240f50ee6aa269481512.jpg");
                holder.setText(R.id.tv_sort, s);
            }
        };
        recyclerView.setAdapter(commonAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(context, 0));
    }

}
