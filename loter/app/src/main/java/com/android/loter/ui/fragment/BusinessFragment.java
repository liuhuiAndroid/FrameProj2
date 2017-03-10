package com.android.loter.ui.fragment;

import com.android.loter.R;
import com.android.loter.ui.base.BaseFragment;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import butterknife.BindView;

/**
 * Created by we-win on 2017/3/6.
 */

public class BusinessFragment extends BaseFragment {

    @BindView(R.id.bmapView)
    MapView mBmapView;

    BaiduMap mBaiduMap;
    // 定位相关
    LocationClient mLocClient;
    private Marker mMarkerA;

    public MyLocationListenner myListener = new MyLocationListenner();
    boolean isFirstLoc = true; // 是否首次定位

    // 初始化全局 bitmap 信息，不用时及时 recycle
    BitmapDescriptor bd = BitmapDescriptorFactory
            .fromResource(R.drawable.icon_gcoding);

    public static BaseFragment newInstance() {
        BusinessFragment baseFragment = new BusinessFragment();
        return baseFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_business;
    }

    @Override
    protected void initData() {
        //初始化地图
        mBaiduMap = mBmapView.getMap();
        mBaiduMap
                .setMyLocationConfigeration(new MyLocationConfiguration(
                        MyLocationConfiguration.LocationMode.NORMAL, true, null));
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(getActivity());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

        //初始化覆盖物
//        LatLng llA = new LatLng(121.510761, 31.278299);

        // add marker overlay
        LatLng llA = new LatLng(39.963175, 116.400244);
        MarkerOptions ooA = new MarkerOptions().position(llA).icon(bd)
                .zIndex(9).draggable(true);
        // 掉下动画
        ooA.animateType(MarkerOptions.MarkerAnimateType.drop);
        mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mBmapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBmapView.onPause();
    }

    @Override
    public void onDestroy() {
        mBmapView.onDestroy();
        super.onDestroy();
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mBmapView = null;
        bd.recycle();
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mBmapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

    }

}
