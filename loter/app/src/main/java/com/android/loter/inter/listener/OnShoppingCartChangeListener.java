package com.android.loter.inter.listener;

public interface OnShoppingCartChangeListener {
    void onDataChange(String selectCount, String selectMoney);
    void onSelectItem(boolean isSelectedAll); 
}
