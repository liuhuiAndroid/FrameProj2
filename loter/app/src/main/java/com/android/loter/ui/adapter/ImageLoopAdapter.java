package com.android.loter.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.loter.R;
import com.android.loter.inter.OnItemClickListener;
import com.android.loter.util.imageloader.ImageLoaderUtil;

import java.util.List;

/**
 * Created by we-win on 2017/3/8.
 * 图片循环Adapter
 */

public class ImageLoopAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private Context context;
    private List<String> imgUrlList;
    private int imgSize;

    public ImageLoopAdapter(ViewPager viewPager, List<String> imgUrlList) {
        this.viewPager = viewPager;
        this.context = viewPager.getContext();
        this.imgUrlList = imgUrlList;
        imgSize = imgUrlList.size();
        viewPager.addOnPageChangeListener(this);
    }

    public int getRealCurrentItem(int position) {
        return imgSize * 1000 + position;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(viewPager.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        container.addView(imageView);
        int index = position % imgUrlList.size();
        //        ImageLoaderUtil.getInstance().loadGifImage("http://image.sports.baofeng.com/19ce5d6ac3b4fff255196f200b1d3079", R.mipmap.ic_launcher, mImage);
        ImageLoaderUtil.getInstance().loadImage(imgUrlList.get(index), R.mipmap.ic_launcher, imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });
        return imageView;
        //        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mOnPageSelected != null) {
            mOnPageSelected.onPageSelected(position % imgSize);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private OnPageSelected mOnPageSelected;

    public interface OnPageSelected {
        void onPageSelected(int position);
    }

    public void setOnPageSelected(OnPageSelected onPageSelected) {
        mOnPageSelected = onPageSelected;
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
