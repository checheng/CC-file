package com.wuxindianqi.administrator.chargingstationapp.utils;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ForgetPWRespond;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Query;

import static com.wuxindianqi.administrator.chargingstationapp.bean.URLPath.FOPGET_PASSWORD;

/**
 * Created by Administrator on 2017/12/8.
 */

public interface ForgetPW_Interface {
	@PATCH(FOPGET_PASSWORD)
	Call<ForgetPWRespond> getRespond(
			@Query("mobile") String mobile ,
			@Query("passwordNew") String passwordNew,
			@Query("verifyCode") String verifyCode,
			@Header("Authorization") String authorization
	);
}
