package com.android.loter.ui.fragment.fifth.child;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.adapter.CommonAdapter;
import com.android.loter.ui.adapter.base.ViewHolder;
import com.android.loter.ui.adapter.wrapper.HeaderAndFooterWrapper;
import com.android.loter.ui.base.BaseBackFragment;
import com.android.loter.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by we-win on 2017/3/22.
 */

public class MyInviteFragment extends BaseBackFragment {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    private List<String> mStringList;

    public static BaseFragment newInstance() {
        MyInviteFragment myInviteFragment = new MyInviteFragment();
        return myInviteFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_my_invite;
    }

    @Override
    protected void initData() {
        mTvTitle.setText("我的邀请");
        mStringList = new ArrayList<>();
        mStringList.add("!");
        mStringList.add("!");
        mStringList.add("!");
        mStringList.add("!");
        mStringList.add("!");
        mStringList.add("!");
        mStringList.add("!");
        mStringList.add("!");
        mStringList.add("!");
        mStringList.add("!");
        mStringList.add("!");
        mStringList.add("!");
        mStringList.add("!");
        mStringList.add("!");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        CommonAdapter commonAdapter = new CommonAdapter<String>(getActivity(), R.layout.layout_my_invite, mStringList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_name, "小黑猫");
                holder.setText(R.id.tv_phone_num, "13800000000");
                holder.setText(R.id.tv_time, "2016-07-16");
            }
        };
        HeaderAndFooterWrapper headerAndFooterWrapper = new HeaderAndFooterWrapper(commonAdapter);
        headerAndFooterWrapper.addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.layout_my_invite_title, mRecyclerView, false));
        mRecyclerView.setAdapter(headerAndFooterWrapper);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity(), 0));
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
