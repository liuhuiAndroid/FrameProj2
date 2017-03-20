package com.android.loter.ui.fragment.first.child;

import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.base.BaseBackFragment;

import butterknife.BindView;

/**
 * Created by we-win on 2017/3/20.
 */

public class MessageFragment extends BaseBackFragment {

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initData() {
        mTvTitle.setText("消息");
    }

    @Override
    protected void bindEvent() {

    }


}
