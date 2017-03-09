package com.android.loter.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.android.loter.R;
import com.android.loter.inter.OnButtonClickListener;

/**
 * Created by we-win on 2017/3/9.
 */

public class GuideViewPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

    private static final String TAG = "GuideViewPagerAdapter";
    private int[] drawables = {R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private Context mContext;

    public GuideViewPagerAdapter(ViewPager viewPager,Context context) {
        mContext = context;
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public int getCount() {
        return drawables.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_welcome, null);
        container.addView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Button button = (Button) view.findViewById(R.id.button);
        imageView.setImageResource(drawables[position]);
        if (position != (drawables.length - 1)) {
            button.setVisibility(View.INVISIBLE);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnButtonClickListener.onButtonClick();
            }
        });
        return view;
    }

    private OnButtonClickListener mOnButtonClickListener;

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        mOnButtonClickListener = onButtonClickListener;
    }

    // ========================
    private int currentPageScrollStatus;
    private int pos = 0;
    private int count = 0;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (positionOffsetPixels == 0
                && currentPageScrollStatus == 1 && position == (drawables.length -1)) {
            // 添加一个标示 因为这里会如果滑动到最后的话,还想在往后滑动
            // currentPageScrollStatus一直为1,会执行多次。
            // 如果想执行一次的话,添加一个标示,第二次就会更改count的值
            if (count == 0 ) {
                mOnButtonClickListener.onButtonClick();
            }
            count++;
        }
    }

    @Override
    public void onPageSelected(int position) {
        setCurrentPos(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        /*
         * 记录page滑动状态 state=1 代表着 正在滑动 state=2 代表着 滑动完毕
         */
        currentPageScrollStatus = state;
    }

    private void setCurrentPos(int postion) {
        pos = postion;
    }
}
