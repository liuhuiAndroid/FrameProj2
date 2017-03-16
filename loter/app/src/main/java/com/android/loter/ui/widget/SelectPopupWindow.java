package com.android.loter.ui.widget;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.android.loter.R;

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
        this.setAnimationStyle(R.style.PopupAnimation);
        this.setOnDismissListener(onDismissListener);


    }

}
