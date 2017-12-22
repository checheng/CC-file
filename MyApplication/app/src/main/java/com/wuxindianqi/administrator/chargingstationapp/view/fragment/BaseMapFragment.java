package com.wuxindianqi.administrator.chargingstationapp.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.wuxindianqi.administrator.chargingstationapp.R;
import com.wuxindianqi.administrator.chargingstationapp.utils.MapUtils.LatlngDistanceMethod;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public abstract class BaseMapFragment extends PermissionFragment implements
        LocationSource, AMapLocationListener, AMap.OnMapClickListener, AMap.OnMapLoadedListener {

    private TextureMapView mTextureMapView;
    protected AMap mAMap;
    private MyLocationStyle myLocationStyle;
    private OnLocationChangedListener mListener;
    private AMapLocationClient locationClient;
    private AMapLocationClientOption clientOption;
    protected AMapLocation mAMapLocation;
    protected List<LatLng> pointList = new ArrayList<LatLng>();
    protected boolean isLocate = false, followMove = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTextureMapView = (TextureMapView) getView().findViewById(R.id.map);

        if (mTextureMapView != null) {
            mTextureMapView.onCreate(savedInstanceState);
            if (mAMap == null) {
                mAMap = mTextureMapView.getMap();
                setUpMap();
            } else {
                mAMap.clear();
                mAMap.setLocationSource(this);
                mAMap.setMyLocationEnabled(true);
                mAMap = mTextureMapView.getMap();
                setUpMap();
            }

        }
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        mAMap.setLocationSource(this);// 设置定位监听
        mAMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        mAMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        mAMap.setMyLocationStyle(new MyLocationStyle());//初始化定位蓝点样式类
        mAMap.moveCamera(CameraUpdateFactory.zoomTo(13));
        mAMap.setMapType(AMap.MAP_TYPE_NORMAL);
        mAMap.setOnMapTouchListener(new AMap.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent motionEvent) {
                followMove = false;
            }
        });
//		setMarker();
    }

    protected void setMarker() {
        for (int i = 0; i < getPointList().size(); i++) {
            Marker marker = mAMap.addMarker(new MarkerOptions().position(getPointList().get(i))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }

	/*	mAMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
			public boolean onMarkerClick(Marker marker) {
				Toast.makeText(getContext(),"经纬度为"+marker.getPosition(),Toast.LENGTH_SHORT).show();
				return false;
			}
		});*/

    }

    @Override
    public void onResume() {
        super.onResume();
        mTextureMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mTextureMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mTextureMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTextureMapView.onDestroy();
        if (locationClient != null) locationClient.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (locationClient == null) {
            locationClient = new AMapLocationClient(getActivity());
            clientOption = new AMapLocationClientOption();
            locationClient.setLocationListener(this);
            clientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            clientOption.setInterval(2000);
//			clientOption.setOnceLocation(true);
            locationClient.setLocationOption(clientOption);
            locationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (locationClient != null) {
            locationClient.stopLocation();
            locationClient.onDestroy();
        }
        locationClient = null;

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                mAMapLocation = aMapLocation;
                if (followMove) {
                    mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                    //移动距离超过50米重新定位
                }
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("定位报错：", errText);
            }
        }
    }

/*	*//**
     * 地图单击回调事件
     * *//*
	@Override
	public void onMapClick(LatLng latLng) {

	}

	*//**
     * 地图加载回调
     * *//*
	@Override
	public void onMapLoaded() {

	}

	*/

    /**
     * 缩放移动地图，保证所有自定义marker在可视范围中，且地图中心点不变。
     *//*
	public void zoomToSpanWithCenter() {
			if (mAMap == null)
				return;
			LatLngBounds bounds = getLatLngBounds(new LatLng(mAMapLocation.getLatitude(),mAMapLocation.getLongitude()), getPointList());
			mAMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));

	}

	//根据中心点和自定义内容获取缩放bounds
	private LatLngBounds getLatLngBounds(LatLng centerpoint, List<LatLng> pointList) {
		LatLngBounds.Builder b = LatLngBounds.builder();
		if (centerpoint != null){
			for (int i = 0; i < pointList.size(); i++) {
				LatLng p = pointList.get(i);
				LatLng p1 = new LatLng((centerpoint.latitude * 2) - p.latitude, (centerpoint.longitude * 2) - p.longitude);
				b.include(p);
				b.include(p1);
			}
		}
		return b.build();
	}*/

    //测试用
    private List<LatLng> getPointList() {

        pointList.add(new LatLng(30.707366, 114.416025));
        pointList.add(new LatLng(30.704316, 114.419005));
        pointList.add(new LatLng(30.702396, 114.416125));
        pointList.add(new LatLng(30.707166, 114.411075));
        pointList.add(new LatLng(30.703266, 114.416155));
        pointList.add(new LatLng(30.701236, 114.415935));
        pointList.add(new LatLng(30.700256, 114.407915));
        pointList.add(new LatLng(30.707166, 114.416075));
        return pointList;

    }

}
