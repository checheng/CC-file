package com.wuxindianqi.administrator.chargingstationapp.utils.RetrofitInterface;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ApiTokenRespond;
import com.wuxindianqi.administrator.chargingstationapp.bean.URLPath;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.API_KEY;

/**
 * Created by Administrator on 2017/11/14.
 */

public interface GetToken_Interface {

	@GET(URLPath.GET_TOKEN)
	Call<ApiTokenRespond> getCall(
			@Query("apiKey") String apiKey,
			 @Header("Authorization") String authorization
	);

	//测试用
	@GET(URLPath.GET_TOKEN+API_KEY)
	Call<ApiTokenRespond> getCall(
			);

}
