package com.wuxindianqi.administrator.chargingstationapp;

import android.app.Application;
import android.util.Log;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.wuxindianqi.administrator.chargingstationapp.bean.UserInfo;
import com.wuxindianqi.administrator.chargingstationapp.utils.SharedUtils;

/**
 * Created by Administrator on 2017/11/13.
 */

public class MyApplicaiton extends Application {

	/**
	 * baseURL
	 */
	public static final String BASE_URL = "http://192.168.2.253:8082/";
	/**
	 * API_key
	 */
	public static final String ANDROID = "AC5870E67EB327C48049DC86690F3A63";
	public static final String IOS = "8FA6683037EB216F2583FE8FEDCB6D30";
	public static final String WECHAT = "3FF30277D14419595E7A7AA9AE6114FA";

	public static final String API_KEY = "a59f7076646c4667aa0e6a387891db9a";

	public static String API_TOKEN ="";
	public static boolean LOGIN_MARKER =false;
	public static String Login_TOKEN="";
	public static UserInfo sUserInfo;


	@Override
	public void onCreate() {
		super.onCreate();
		ZXingLibrary.initDisplayOpinion(this);
		if (SharedUtils.getIdentity(getApplicationContext()).length() > 0 && SharedUtils.getIDPassword(getApplicationContext()).length() > 0){
			setCacheID(SharedUtils.getIdentity(getApplicationContext()));
			setCachePWD(SharedUtils.getIDPassword(getApplicationContext()));
		}
	}

	public static String getApiToken() {
		return API_TOKEN;
	}

	public static void setApiToken(String apiToken) {
		Log.i("传入Token值",apiToken);
		API_TOKEN = apiToken;
	}

	public static boolean isLOGIN_MARKER() {
		return LOGIN_MARKER;
	}

	public static void setLOGIN_MARKER(boolean LOGIN_MARKER) {
		MyApplicaiton.LOGIN_MARKER = LOGIN_MARKER;
	}


	public static void clearLogin_TOKEN(){
		Login_TOKEN="";
		LOGIN_MARKER = false;
	}

	public static String getLogin_TOKEN() {
		return Login_TOKEN;
	}

	public static void setLogin_TOKEN(String login_TOKEN) {
		Login_TOKEN = login_TOKEN;
		LOGIN_MARKER = true;
	}

	public static UserInfo getUserInfo() {
		return sUserInfo;
	}

	public static void setUserInfo(UserInfo userInfo) {
		sUserInfo = userInfo;
	}

	private static String CacheID="";
	private static String CachePWD = "";

	public static String getCacheID() {
		return CacheID;
	}

	public static void setCacheID(String mCacheID) {
		CacheID = mCacheID;
	}

	public static String getCachePWD() {
		return CachePWD;
	}

	public static void setCachePWD(String mCachePWD) {
		CachePWD = mCachePWD;
	}

	public static void ClearCache(){
		CacheID = "";
		CachePWD = "";
	}
}
