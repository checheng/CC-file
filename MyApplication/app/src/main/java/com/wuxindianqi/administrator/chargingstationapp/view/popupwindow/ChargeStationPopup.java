package com.wuxindianqi.administrator.chargingstationapp.view.popupwindow;

import razerdp.basepopup.BasePopupWindow;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;

import com.amap.api.maps.model.LatLng;
import com.wuxindianqi.administrator.chargingstationapp.R;
import com.wuxindianqi.administrator.chargingstationapp.adapter.StationListRecyclerViewAdapter;
import com.wuxindianqi.administrator.chargingstationapp.bean.RecyclerViewItem.StationViewItem;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.StationList;
import com.wuxindianqi.administrator.chargingstationapp.utils.MapUtils.LatlngDistanceMethod;
import com.wuxindianqi.administrator.chargingstationapp.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by checheng on 2017/12/15.
 */

public class ChargeStationPopup extends BasePopupWindow implements View.OnClickListener {

    private RecyclerView mrecyclerView;
    StationListRecyclerViewAdapter listRecyclerViewAdapter;
    private List<StationViewItem> mitems = new ArrayList<StationViewItem>();
    private StationList stationList;


    public ChargeStationPopup(Context context, StationList stationList) {
        super(context);
        this.stationList = stationList;
    }

    private boolean initData() {
        if (stationList != null) {
            if (stationList.getStatus() == 0) {
       /*         for (int i  =0 ;i<stationList.getData().size();i++){
                    new LatlngDistanceMethod().getDistance()
                    mitems.add(new StationViewItem(
                       stationList.getData().get(i).getStationName(),
                            stationList.getData().get(i).getLocation()
                    ));

                }*/
            }
            return false;
        }

        StationViewItem[] items = new StationViewItem[10];
        for (int i = 0; i < 10; i++) {
            items[i] = new StationViewItem("名称" + i,"", 0,0, "位置" + i, i, i, i);
            mitems.add(items[i]);
        }
        return false;
    }

    public ChargeStationPopup(Context context) {
        super(context);
        initView();
    }


    public void initView() {
        mrecyclerView = (RecyclerView) findViewById(R.id.my_recyclerview);
        initData();
        listRecyclerViewAdapter = new StationListRecyclerViewAdapter(getContext(),(ArrayList<StationViewItem>) mitems);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mrecyclerView.addItemDecoration(new SpaceItemDecoration(30));
        mrecyclerView.setAdapter(listRecyclerViewAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected Animation initShowAnimation() {
        AnimationSet set = new AnimationSet(false);
        Animation shakeAnima = new RotateAnimation(0, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        shakeAnima.setInterpolator(new CycleInterpolator(5));
        shakeAnima.setDuration(400);
        set.addAnimation(getDefaultAlphaAnimation());
        set.addAnimation(shakeAnima);
        return set;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popupwindow_list);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.my_station_list);
    }


    public StationList getStationList() {
        return stationList;
    }

    public void setStationList(double lantitude,double longtitude,StationList StationList) {
        this.stationList = StationList;
        if (stationList.getStatus()==0) {
            List<StationViewItem> items =new ArrayList<StationViewItem>();
            for (int i = 0; i < stationList.getData().size(); i++) {
                double lan = Double.parseDouble(stationList.getData().get(i).getLatitude());
                double lng = Double.parseDouble(stationList.getData().get(i).getLongitude());
                double distance = new LatlngDistanceMethod(lantitude, longtitude, lan,lng).getDistance();
                String dis = String.valueOf(distance/1000)+"km";
                items.add(new StationViewItem(
                        stationList.getData().get(i).getStationName(),
                        dis,
                        lan,
                        lng,
                        stationList.getData().get(i).getLocation(),
                        stationList.getData().get(i).getAcSlow(),
                        stationList.getData().get(i).getAcFast(),
                        stationList.getData().get(i).getDcFast()
                ));
            }
            mitems.clear();
            mitems.addAll(items);
            listRecyclerViewAdapter.notifyDataSetChanged();
        }
    }
}
