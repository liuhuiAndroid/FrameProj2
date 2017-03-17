package com.android.loter.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.loter.R;
import com.android.loter.util.ScreenUtil;


/**
 * Created by YoKeyword on 16/6/3.
 */
public class BottomBarTab extends LinearLayout {
    private ImageView mIcon;
    private Context mContext;
    private int mTabPosition = -1;
    private TextView mTextView;

    public BottomBarTab(Context context, @DrawableRes int icon, String text) {
        this(context, null, icon,text);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int icon, String text) {
        this(context, attrs, 0, icon,text);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int defStyleAttr, int icon, String text) {
        super(context, attrs, defStyleAttr);
        init(context, icon,text);
    }

    private void init(Context context, int icon, String text) {
        this.setOrientation(LinearLayout.VERTICAL);
        this.setPadding(0, ScreenUtil.dipToPx(context,3),0,ScreenUtil.dipToPx(context,3));
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackgroundBorderless});
        Drawable drawable = typedArray.getDrawable(0);
        setBackgroundDrawable(drawable);
        typedArray.recycle();

        mIcon = new ImageView(context);
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 22, getResources().getDisplayMetrics());
        LayoutParams params = new LayoutParams(size, size);
        params.gravity = Gravity.CENTER;
        mIcon.setImageResource(icon);
        mIcon.setLayoutParams(params);
        mIcon.setColorFilter(ContextCompat.getColor(context, R.color.tab_unselect));
        addView(mIcon);

        mTextView = new TextView(context);
        mTextView.setText(text);
        LayoutParams paramsText = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTextView.setSingleLine();
        mTextView.setLayoutParams(paramsText);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTextColor(ContextCompat.getColor(context, R.color.tab_unselect));
        addView(mTextView);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimary));
            mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        } else {
            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.tab_unselect));
            mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.tab_unselect));
        }
    }

    public void setTabPosition(int position) {
        mTabPosition = position;
        if (position == 0) {
            setSelected(true);
        }
    }

    public int getTabPosition() {
        return mTabPosition;
    }
}
