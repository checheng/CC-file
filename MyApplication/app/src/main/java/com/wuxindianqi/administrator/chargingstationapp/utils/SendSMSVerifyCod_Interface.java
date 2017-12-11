package com.wuxindianqi.administrator.chargingstationapp.utils;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.SendSMSVerifyCodeRespond;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.wuxindianqi.administrator.chargingstationapp.bean.URLPath.SEND_VERIFYCODE;

/**
 * Created by Administrator on 2017/11/15.
 */

public interface SendSMSVerifyCod_Interface {

	@POST(SEND_VERIFYCODE)
	Call<SendSMSVerifyCodeRespond> getRespond(@Query("mobile") String mobile,
											  @Query("use") String use,
											  @Header("Authorization") String authorization);
}
