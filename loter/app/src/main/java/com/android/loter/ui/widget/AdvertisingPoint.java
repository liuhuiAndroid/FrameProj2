package com.android.loter.ui.widget;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.loter.R;
import com.android.loter.util.ScreenUtil;

/**
 * 循环图片小圆点
 */
public class AdvertisingPoint {

    private static final String TAG = "AdvertisingPoint";
    private Context context;
    private View point;

    public AdvertisingPoint(Context context, LinearLayout parent, boolean isFirst) {
        this.context = context;
        point = new View(context);
        point.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_point_normal));
        parent.addView(point);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) point.getLayoutParams();
        params.width = ScreenUtil.dipToPx(context, 5);
        params.height = ScreenUtil.dipToPx(context, 5);
        if (!isFirst) {
            params.leftMargin = ScreenUtil.dipToPx(context, 8);
        }
        point.setLayoutParams(params);
        Log.i(TAG, "AdvertisingPoint:  ================================ ？？？");
    }

    public void setFocus(boolean isFocus) {
        if (isFocus) {
            point.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_point_select));
        } else {
            point.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_point_normal));
        }
    }

}
