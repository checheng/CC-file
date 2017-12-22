package com.wuxindianqi.administrator.chargingstationapp.bean.RecyclerViewItem;

/**
 * Created by checheng on 2017/12/18.
 */

public class StationViewItem {
    private int  ACslow, ACfast, DCfast;
    private double latitude,longtitude;
    private String LocationName, Distance, LocationAddress;

    public StationViewItem(String locationName, String distance, double latitude, double longtitude,String locationAddress,int ACslow, int ACfast, int DCfast) {
        this.ACslow = ACslow;
        this.ACfast = ACfast;
        this.DCfast = DCfast;
        this.latitude = latitude;
        this.longtitude = longtitude;
        LocationName = locationName;
        Distance = distance;
        LocationAddress = locationAddress;
    }

    public int getACslow() {
        return ACslow;
    }

    public void setACslow(int ACslow) {
        this.ACslow = ACslow;
    }

    public int getACfast() {
        return ACfast;
    }

    public void setACfast(int ACfast) {
        this.ACfast = ACfast;
    }

    public int getDCfast() {
        return DCfast;
    }

    public void setDCfast(int DCfast) {
        this.DCfast = DCfast;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }

    public String getLocationAddress() {
        return LocationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        LocationAddress = locationAddress;
    }

    public String getDistance() {
        return Distance;
    }

    public String getACslowNum(){
        return "交流慢充："+getACslow();
    }

    public String getACfastNum(){
        return "交流快充："+getACfast();
    }

    public String getDCfastNum(){
        return " 直流快充："+getDCfast();
    }

}
