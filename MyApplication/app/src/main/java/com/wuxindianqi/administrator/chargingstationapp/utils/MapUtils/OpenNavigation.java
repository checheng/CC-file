package com.wuxindianqi.administrator.chargingstationapp.utils.MapUtils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by checheng on 2017/12/20.
 */

public class OpenNavigation {


    //手机中是否包含当前应用名
    public static boolean isAvilible(Context context, String packageName) {
        final PackageManager manager = context.getPackageManager();
        List<PackageInfo> packageInfos = manager.getInstalledPackages(0);
        List<String> packageNames = new ArrayList<String>();
        for (int i = 0; i < packageInfos.size(); i++) {
            String packagename = packageInfos.get(i).packageName;
            packageNames.add(packagename);
        }
        return packageNames.contains(packageName);

    }


    //调用高德地图第三方导航
    public static boolean isAmapNavi(Context context, double lat, double lng) {
        StringBuffer stringBuffer = new StringBuffer("androidamap://navi?sourceApplication=")
                .append("Charging Station App")
                .append("&lat=").append(lat)
                .append("&lon=").append(lng)
                .append("&dev=").append(1)
                .append("&style=").append(0);

        if (isAvilible(context, "com.autonavi.minimap")) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(stringBuffer.toString()));
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setPackage("com.autonavi.minimap");
                context.startActivity(intent);
                return true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    //调用百度地图第三方导航
    public static boolean isBaiduNavi(Context context, double lat, double lng) {
        StringBuffer stringBuffer = new StringBuffer("intent://map/direction?")
                .append("Charging Station App")
                .append("destination=latlng:").append(lat)
                .append(",").append(lng)
//                .append("|").append(locationname)
                .append("&mode=").append("driving");
        if (isAvilible(context, "com.baidu.BaiduMap")) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(stringBuffer.toString()));
                intent.setPackage("com.baidu.BaiduMap");
                context.startActivity(intent);
                return true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    //调用导航，失败则返回信息
    public static boolean isAvilibaleNavi(Context context, double lat, double lng){
        if (isAmapNavi(context,lat,lng)){
            return true;
        }else if (isBaiduNavi(context,lat,lng)){
            return true;
        }else {
            Toast.makeText(context, "您尚未安装可用地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }


        return false;
    }
}

