package com.android.loter.ui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by WE-WIN-027 on 2016/8/12.
 *
 * @des ${TODO}
 */
public class MyNoSlippingViewPager extends ViewPager {

    private boolean isCanScroll = true;

    public MyNoSlippingViewPager(Context context) {
        super(context);
    }

    public MyNoSlippingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScanScroll(boolean isCanScroll){
        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isCanScroll == false) {
            return false;
        } else {
            return super.onTouchEvent(ev);
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isCanScroll == false) {
            return false;
        } else {
            return super.onInterceptTouchEvent(ev);
        }

    }
}
