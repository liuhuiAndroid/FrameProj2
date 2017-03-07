package com.android.loter.ui.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.android.loter.R;
import com.android.loter.ui.adapter.CommonAdapter;
import com.android.loter.ui.adapter.base.ViewHolder;
import com.android.loter.ui.base.BaseFragment;
import com.android.loter.ui.decoration.DividerGridItemDecoration;
import com.android.loter.util.imageloader.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by we-win on 2017/3/6.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.image)
    ImageView mImage;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

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

        List<String> stringList = new ArrayList<>();
        stringList.add("1 - ");
        stringList.add("2 - ");
        stringList.add("3 - ");
        CommonAdapter commonAdapter = new CommonAdapter<String>(getActivity(),R.layout.layout_test,stringList) {

            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_test, s + " : " + holder.getLayoutPosition());
            }

        };
        mRecyclerView.setAdapter(commonAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity(), 0));


    }

    @Override
    protected void bindEvent() {

    }

}
