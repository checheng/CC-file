package com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond;

import com.amap.api.maps.model.LatLng;

/**
 * Created by checheng on 2017/12/19.
 */

public class StationInfo {
    private String stationUuid;
    private String stationName;
    private String location;
    private String longitude;
    private String latitude;
    private int AcFast;
    private int AcSlow;
    private int DcFast;

    public String getStationUuid() {
        return stationUuid;
    }

    public void setStationUuid(String stationUuid) {
        this.stationUuid = stationUuid;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getAcFast() {
        return AcFast;
    }

    public void setAcFast(int acFast) {
        AcFast = acFast;
    }

    public int getAcSlow() {
        return AcSlow;
    }

    public void setAcSlow(int acSlow) {
        AcSlow = acSlow;
    }

    public int getDcFast() {
        return DcFast;
    }

    public void setDcFast(int dcFast) {
        DcFast = dcFast;
    }
}
