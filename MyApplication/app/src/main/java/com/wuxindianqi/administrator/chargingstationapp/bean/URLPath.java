package com.wuxindianqi.administrator.chargingstationapp.bean;

/**
 * Created by Administrator on 2017/11/27.
 */

public class URLPath {
	//http://192.168.2.253:8082/v1/app_user/api_token?apiKey=a59f7076646c4667aa0e6a387891db9a'
	public static final String GET_TOKEN = "v1/app_user/api_token?";

	//http://192.168.2.253:8082/v1/app_user/session?mobile=15327218886&password=123456
	public static final String LOGIN_ = "v1/app_user/session?";

	//http://192.168.2.253:8082/v1/app_user/session
	public static final String Quit_ = "v1/app_user/session";

	//http://192.168.2.253:8082/v1/app_user/?mobile=1&password=1&verifyCode=1&platformCompanyCode=1
	public static final String REGISTER_ = "v1/app_user/?";

	//http://192.168.2.253:8082/v1/app_user/verify_code?mobile=15327218886
	public static final String SEND_VERIFYCODE = "v1/app_user/verify_code?";

	//http://192.168.2.253:8082/v1/app_user/tpascweu77omz39tyq9hw8aergnutrs1/info
	public static final String GET_USER = "v1/app_user/{token}/info";

	///app_user/{token}/info
	public static final String MODIFY_USER = "v1/app_user/{token}/info? ";

	///app_user/{token}/password
	public static final String MODIFY_PASSWORD = "v1/app_user/{token}/password?";

	//app_user/{token}/balance
	public static final String GET_BALANCE = "v1/app_user/{token}/balance";

	//v1/app_user/password
	public static final String FOPGET_PASSWORD = "v1/app_user/password";

}
