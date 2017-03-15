package com.android.loter.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;
import rx.Subscription;

/**
 * Created by WE-WIN-027 on 2016/8/29.
 *
 * @des ${TODO}
 */
public abstract class BaseFragment extends SupportFragment {
    protected Subscription subscription;
    protected View rootView;
    protected BaseActivity baseActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(container.getContext()).inflate(bindLayout(), null);
        ButterKnife.bind(this, rootView);

        if(rootView != null){
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }

        if (getActivity() instanceof BaseActivity) {
            baseActivity = (BaseActivity) getActivity();
        }

        initToolbar(rootView);
        initData();
        bindEvent();
        return rootView;
    }

    protected void initToolbar(View rootView){};

    public void openActivity(Class<?> cls) {
        startActivity(new Intent(baseActivity, cls));
    }


    /**
     * 绑定布局文件
     * @return
     */
    protected abstract int bindLayout();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 绑定控件事件
     */
    protected abstract void bindEvent();

    public void loadError(Throwable throwable) {
        throwable.printStackTrace();
    }

    protected void unsubscribe(){
        if(subscription != null && !subscription.isUnsubscribed()){
            //用于取消订阅
            subscription.unsubscribe();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

}
