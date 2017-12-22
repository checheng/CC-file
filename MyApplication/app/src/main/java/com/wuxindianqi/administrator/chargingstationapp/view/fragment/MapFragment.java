package com.wuxindianqi.administrator.chargingstationapp.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.wuxindianqi.administrator.chargingstationapp.R;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageEvent;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageStationList;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.StationList;
import com.wuxindianqi.administrator.chargingstationapp.utils.MapUtils.OpenNavigation;
import com.wuxindianqi.administrator.chargingstationapp.view.popupwindow.ChargeStationPopup;
import com.wuxindianqi.administrator.chargingstationapp.view.popupwindow.ShowLimitPopup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopup;
import razerdp.basepopup.BasePopupWindow;

/**
 *
 */
public class MapFragment extends BaseMapFragment {

    private Button ShowBTN, CollectBTN, ListBTN, FocusBTN;
    private EditText UserInputEdit;
    private boolean listBtnShow = false;
    private StationList stationList;
    private List<Marker> markers = new ArrayList<Marker>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        theinitView();
        initOnClick();
        mAMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
//                Toast.makeText(getContext(), marker.getPosition().latitude + "||" + marker.getPosition().longitude, Toast.LENGTH_LONG).show();
                OpenNavigation.isAvilibaleNavi(getContext(), marker.getPosition().latitude, marker.getPosition().longitude);
            }
        });
    }

    public void theinitView() {
        ShowBTN = (Button) getView().findViewById(R.id.show_button);
        CollectBTN = (Button) getView().findViewById(R.id.add_button);
        ListBTN = (Button) getView().findViewById(R.id.list_button);
        FocusBTN = (Button) getView().findViewById(R.id.focus_utton);
        UserInputEdit = (EditText) getView().findViewById(R.id.user_input);

    }

    public void initOnClick() {
        ShowBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowLimitPopup showLimitPopup = new ShowLimitPopup(getContext());
                showLimitPopup.showPopupWindow(R.id.show_button);
            }
        });
        CollectBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        ListBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChargeStationPopup chargeStationPopup = new ChargeStationPopup(getContext());
                if (listBtnShow) {
                    listBtnShow = false;
                    chargeStationPopup.dismiss();
                }
                if (!listBtnShow) {
                    if (stationList != null) {
                        if (mAMapLocation != null && mAMapLocation.getErrorCode() == 0) {
                            listBtnShow = true;
                            chargeStationPopup.showPopupWindow(R.id.list_button);
                            chargeStationPopup.setStationList(mAMapLocation.getLatitude(), mAMapLocation.getLongitude(), stationList);
                        } else Toast.makeText(getContext(), "未获取到定位信息", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "未获取到电站列表", Toast.LENGTH_SHORT).show();
                        EventBus.getDefault().post(new MessageStationList());
                    }
                }

            }
        });
        FocusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                followMove = true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
//        Marker marker = mAMap.addMarker(new MarkerOptions().position(new LatLng(30.704316, 114.419005))
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
//        marker.remove();


    }

    public StationList getStationList() {
        return stationList;
    }

    public void setStationList(StationList stationList) {
        this.stationList = stationList;
        if (stationList.getData().size() > 0) {
            for (int i = 0; i < stationList.getData().size(); i++) {

                double lan = Double.parseDouble(stationList.getData().get(i).getLatitude());
                double lng = Double.parseDouble(stationList.getData().get(i).getLongitude());
                Log.i("收到电站信息", lan + "  | " + lng);

                Marker marker = mAMap.addMarker(new MarkerOptions().position
                        (new LatLng(lan, lng))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                marker.setTitle(stationList.getData().get(i).getStationName());
                markers.add(marker);
            }
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onMapLoaded() {

    }
}
