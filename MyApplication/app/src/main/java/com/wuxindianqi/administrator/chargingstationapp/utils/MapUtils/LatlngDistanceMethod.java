package com.wuxindianqi.administrator.chargingstationapp.utils.MapUtils;

import com.amap.api.location.AMapLocation;

/**
 * Created by Administrator on 2017/12/8.
 */

public class LatlngDistanceMethod {
	private double distance = 0;//单位为米
	private static final double EARTH_RADIUS = 6378137.0;


	public LatlngDistanceMethod(AMapLocation latLng1,AMapLocation latLng2) {
		distance = gettheDistance(latLng1.getLongitude(),latLng1.getLatitude(),latLng2.getLongitude(),latLng2.getLatitude());
	}

	public LatlngDistanceMethod(double localLan,double localLon,double destinationLan,double destinationLon) {
		distance = gettheDistance(localLon,localLan,destinationLon,destinationLan);
	}

	public double getDistance() {
		return distance;
	}


	public double gettheDistance(double longitude1, double latitude1,
							  double longitude2, double latitude2) {
		double Lat1 = rad(latitude1);
		double Lat2 = rad(latitude2);
		double a = Lat1 - Lat2;
		double b = rad(longitude1) - rad(longitude2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(Lat1) * Math.cos(Lat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	private double rad(double d) {
		return d * Math.PI / 180.0;
	}
}
