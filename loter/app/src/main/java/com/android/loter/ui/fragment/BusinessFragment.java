package com.android.loter.ui.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.loter.R;
import com.android.loter.ui.activity.ScannerActivity;
import com.android.loter.ui.base.BaseFragment;
import com.android.loter.util.log.Logger;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by we-win on 2017/3/6.
 */

public class BusinessFragment extends BaseFragment {

    private static final int REQUECT_CODE_LOCATION = 2;
    private static final int REQUECT_CODE_CAMERA = 3;

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
        // 隐藏比例尺
        mBmapView.showScaleControl(false);
        // 不显示缩放控件
        mBmapView.showZoomControls(false);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(getActivity());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(10000);
        mLocClient.setLocOption(option);


        //初始化覆盖物
        // add marker overlay
        LatLng llA = new LatLng(39.963175, 116.400244);
        MarkerOptions ooA = new MarkerOptions().position(llA).icon(bd)
                .zIndex(9).draggable(true);
        // 掉下动画
        ooA.animateType(MarkerOptions.MarkerAnimateType.drop);
        mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));

        //        initPermission();
    }

    /**
     * 申请位置权限
     */
    private void initLocationPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Toast.makeText(getActivity(), getResources().getString(R.string.mainactivity_permission_location), Toast.LENGTH_SHORT).show();
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUECT_CODE_LOCATION);
            } else {
                // No explanation needed, we can request the permission.
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUECT_CODE_LOCATION);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            isFirstLoc = true;
            mLocClient.start();
        }
    }

    @Override
    protected void bindEvent() {

    }


    @Override
    public void onResume() {
        super.onResume();
        mBmapView.onResume();
        initLocationPermission();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBmapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        // 退出时销毁定位
        mLocClient.stop();
    }

    @Override
    public void onDestroy() {
        mBmapView.onDestroy();
        super.onDestroy();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mBmapView = null;
        //        bd.recycle();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Logger.i(" --------requestCode = " + requestCode + "-------- ");
        if (requestCode == REQUECT_CODE_LOCATION) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                isFirstLoc = true;
                mLocClient.registerLocationListener(myListener);
                mLocClient.start();
            } else {
                // Permission Denied
                Toast.makeText(getActivity(), getResources().getString(R.string.mainactivity_permission_location), Toast.LENGTH_SHORT).show();

            }
            return;
        }else if (requestCode == REQUECT_CODE_CAMERA){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                openActivity(ScannerActivity.class);
            } else {
                // Permission Denied
                Toast.makeText(getActivity(), getResources().getString(R.string.scanneractivity_permission_camera_deny), Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            Logger.i("MyLocationListenner 1");
            // map view 销毁后不在处理新接收的位置
            if (location == null || mBmapView == null) {
                return;
            }
            Logger.i("MyLocationListenner 2");
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

    /**
     * 消息
     */
    @OnClick(R.id.tv_message)
    public void mTvMessage(){

    }

    /**
     * 活动
     */
    @OnClick(R.id.tv_activity)
    public void mTvActivity(){

    }

    /**
     * 抢红包
     */
    @OnClick(R.id.tv_red_envelope)
    public void mTvRedEnvelope(){

    }

    /**
     * 购物车
     */
    @OnClick(R.id.tv_shopping)
    public void mTvShopping(){

    }

    /**
     * 扫一扫
     */
    @OnClick(R.id.tv_scanner)
    public void mTvScanner(){
        initCameraPermission();
    }

    /**
     * 申请拍照权限
     */
    private void initCameraPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Toast.makeText(getActivity(), getResources().getString(R.string.scanneractivity_permission_camera), Toast.LENGTH_SHORT).show();
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CAMERA},
                        REQUECT_CODE_CAMERA);
            } else {
                // No explanation needed, we can request the permission.
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CAMERA},
                        REQUECT_CODE_CAMERA);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            openActivity(ScannerActivity.class);
        }
    }
}
