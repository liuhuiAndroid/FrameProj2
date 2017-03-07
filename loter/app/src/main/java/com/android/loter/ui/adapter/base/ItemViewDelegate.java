package com.android.loter.ui.adapter.base;


/**
 * Created by zhy on 16/6/22.
 * 委托
 */
public interface ItemViewDelegate<T>
{

    int getItemViewLayoutId();

    /**
     * 判断是不是该种类型
     * @param item
     * @param position
     * @return
     */
    boolean isForViewType(T item, int position);

    /**
     * 实现onBindViewHolder的功能
     * @param holder
     * @param t
     * @param position
     */
    void convert(ViewHolder holder, T t, int position);

}
