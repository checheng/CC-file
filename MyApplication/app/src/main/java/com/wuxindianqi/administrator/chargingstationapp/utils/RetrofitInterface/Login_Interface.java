package com.wuxindianqi.administrator.chargingstationapp.utils.RetrofitInterface;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.LoginRespond;
import com.wuxindianqi.administrator.chargingstationapp.bean.URLPath;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/11/15.
 */

public interface Login_Interface {

	@POST(URLPath.LOGIN_)
	Call<LoginRespond> getRespond(@Query("mobile") String mobile,
								  @Query("password") String password,
								  @Header("Authorization") String authorization);

}
