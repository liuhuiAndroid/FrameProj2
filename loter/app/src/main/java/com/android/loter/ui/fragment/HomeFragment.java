package com.android.loter.ui.fragment;

import android.widget.ImageView;

import com.android.loter.R;
import com.android.loter.ui.base.BaseFragment;
import com.android.loter.util.imageloader.ImageLoaderUtil;

import butterknife.BindView;

/**
 * Created by we-win on 2017/3/6.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.image)
    ImageView mImage;

    public static BaseFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        ImageLoaderUtil.getInstance().loadGifImage("http://image.sports.baofeng.com/19ce5d6ac3b4fff255196f200b1d3079", R.mipmap.ic_launcher, mImage);

    }

    @Override
    protected void bindEvent() {

    }

}
