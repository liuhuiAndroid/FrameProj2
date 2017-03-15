package com.android.loter.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.base.BaseActivity;
import com.android.loter.util.imageloader.ImageLoaderUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by we-win on 2017/3/13.
 */

public class ImproveInfomationActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_avator)
    CircleImageView mIvAvator;

    String imagePath = "http://img.cnjiayu.net/3211573049-3310678237-21-0.jpg";

    @Override
    protected int bindLayout() {
        return R.layout.activity_improve_infomation;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setImgBack(mImgBack);
        mTvTitle.setText(getResources().getString(R.string.improveinfomationactivity_title));

        ImageLoaderUtil.getInstance().loadImage(imagePath, R.mipmap.ic_launcher, mIvAvator);
    }

    @Override
    protected void bindEvent() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
