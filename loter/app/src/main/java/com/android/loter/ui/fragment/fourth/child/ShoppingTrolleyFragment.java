package com.android.loter.ui.fragment.fourth.child;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.loter.R;
import com.android.loter.db.DBHelper;
import com.android.loter.db.ShoppingTrolley;
import com.android.loter.inter.listener.OnShoppingCartChangeListener;
import com.android.loter.inter.listener.ResponseCallBack;
import com.android.loter.inter.listener.ShoppingCartBiz;
import com.android.loter.inter.listener.ShoppingCartHttpBiz;
import com.android.loter.model.entity.ShoppingCartBean;
import com.android.loter.ui.adapter.MyExpandableListAdapter;
import com.android.loter.ui.base.BaseBackFragment;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by we-win on 2017/3/17.
 */

public class ShoppingTrolleyFragment extends BaseBackFragment {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.rlShoppingTrolleyEmpty)
    RelativeLayout mRlShoppingTrolleyEmpty;
    @BindView(R.id.expandableListView)
    ExpandableListView mExpandableListView;
    @BindView(R.id.ivSelectAll)
    ImageView mIvSelectAll;
    @BindView(R.id.tvCountMoney)
    TextView mTvCountMoney;
    @BindView(R.id.btnSettle)
    TextView mBtnSettle;
    @BindView(R.id.rlBottomBar)
    RelativeLayout mRlBottomBar;

    private List<ShoppingCartBean> mListGoods = new ArrayList<ShoppingCartBean>();
    private MyExpandableListAdapter adapter;

    public static ShoppingTrolleyFragment newInstance() {
        ShoppingTrolleyFragment baseFragment = new ShoppingTrolleyFragment();
        return baseFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_shopping_trolley;
    }

    @Override
    protected void initData() {
        mTvTitle.setText(getResources().getString(R.string.shoppingtrolleyfragment_title));
        setAdapter();
        requestShoppingCartList();
    }

    private void setAdapter() {
        adapter = new MyExpandableListAdapter(_mActivity);
        mExpandableListView.setAdapter(adapter);
        adapter.setOnShoppingCartChangeListener(new OnShoppingCartChangeListener() {

            public void onDataChange(String selectCount, String selectMoney) {
                int goodsCount = DBHelper.getInstance().getShoppingTrolleyDao().queryBuilder().list().size();
                if (goodsCount == 0) {
                    showEmpty(true);
                } else {
                    showEmpty(false);//其实不需要做这个判断，因为没有商品的时候，必须退出去添加商品；
                }
                String countMoney = String.format("合计：￥%s", selectMoney);
                mTvCountMoney.setText(countMoney);
            }

            public void onSelectItem(boolean isSelectedAll) {
                ShoppingCartBiz.checkItem(isSelectedAll, mIvSelectAll);
            }
        });
        //通过监听器关联Activity和Adapter的关系，解耦；
        View.OnClickListener listener = adapter.getAdapterListener();
        if (listener != null) {
            //即使换了一个新的Adapter，也要将“全选事件”传递给adapter处理；
            mIvSelectAll.setOnClickListener(adapter.getAdapterListener());
            //结算时，一般是需要将数据传给订单界面的
            mBtnSettle.setOnClickListener(adapter.getAdapterListener());
        }
        mExpandableListView.setGroupIndicator(null);
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });
    }

    public void showEmpty(boolean isEmpty) {
        if (isEmpty) {
            mExpandableListView.setVisibility(View.GONE);
            mRlShoppingTrolleyEmpty.setVisibility(View.VISIBLE);
            mRlBottomBar.setVisibility(View.GONE);
        } else {
            mExpandableListView.setVisibility(View.VISIBLE);
            mRlShoppingTrolleyEmpty.setVisibility(View.GONE);
            mRlBottomBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void bindEvent() {

    }

    /**
     * 获取购物车列表的数据（数据和网络请求也是非通用部分）
     */
    private void requestShoppingCartList() {
        testAddGood();
        //使用本地JSON，作测试用。本来应该是将商品ID发送的服务器，服务器返回对应的商品信息；
        ShoppingCartHttpBiz.requestOrderList(_mActivity, new ResponseCallBack() {

            public void handleResponse(Object o, int code) {
                mListGoods = ShoppingCartHttpBiz.handleOrderList((JSONObject) o, code);
                updateListView();
            }
        });
    }

    private void updateListView() {
        adapter.setList(mListGoods);
        adapter.notifyDataSetChanged();
        expandAllGroup();
    }

    /**
     * 展开所有组
     */
    private void expandAllGroup() {
        for (int i = 0; i < mListGoods.size(); i++) {
            mExpandableListView.expandGroup(i);
        }
    }

    /**
     * 测试添加数据 ，添加的动作是通用的，但数据上只是添加ID而已，数据非通用
     */
    private void testAddGood() {
        DBHelper.getInstance().getShoppingTrolleyDao().insert(new ShoppingTrolley(null, "279457f3-4692-43bf-9676-fa9ab9155c38", "6"));
        DBHelper.getInstance().getShoppingTrolleyDao().insert(new ShoppingTrolley(null, "8c6e52fb-d57c-45ee-8f05-50905138801b", "6"));
        DBHelper.getInstance().getShoppingTrolleyDao().insert(new ShoppingTrolley(null, "7d6e52fb-d57c-45ee-8f05-50905138801d", "3"));
        DBHelper.getInstance().getShoppingTrolleyDao().insert(new ShoppingTrolley(null, "7d6e52fb-d57c-45ee-8f05-50905138801e", "3"));
        DBHelper.getInstance().getShoppingTrolleyDao().insert(new ShoppingTrolley(null, "7d6e52fb-d57c-45ee-8f05-50905138801f", "3"));
        DBHelper.getInstance().getShoppingTrolleyDao().insert(new ShoppingTrolley(null, "7d6e52fb-d57c-45ee-8f05-50905138801g", "3"));
        DBHelper.getInstance().getShoppingTrolleyDao().insert(new ShoppingTrolley(null, "7d6e52fb-d57c-45ee-8f05-50905138801h", "9"));
        DBHelper.getInstance().getShoppingTrolleyDao().insert(new ShoppingTrolley(null, "95fbe11d-7303-4b9f-8ca4-537d06ce2f8a", "8"));
    }

}
