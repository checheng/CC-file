package com.wuxindianqi.administrator.chargingstationapp.utils;

import com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.RegisterRespond;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.wuxindianqi.administrator.chargingstationapp.bean.URLPath.REGISTER_;

/**
 * Created by Administrator on 2017/11/15.
 */

public interface Register_Interface {


	@POST(REGISTER_)
	Call<RegisterRespond> getRespond(@Query("mobile") String mobile,
									 @Query("password") String password,
									 @Query("verifyCode") String verifyCode ,
									 @Query("platformCompanyCode") String platformCompanyCode ,
									 @Header("Authorization") String authorization );


   //测试用
	@Headers({"Authorization:"+ MyApplicaiton.ANDROID})
	@POST("v1/user?mobile=15926268300&password=huangshuo123&verifyCode=131676&platformCompanyCode=WHWX")
	Call<RegisterRespond> getRespond();
}
