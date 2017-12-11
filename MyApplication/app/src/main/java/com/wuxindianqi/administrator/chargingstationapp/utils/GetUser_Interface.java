package com.wuxindianqi.administrator.chargingstationapp.utils;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.GetUserINFO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

import static com.wuxindianqi.administrator.chargingstationapp.bean.URLPath.GET_USER;

/**
 * Created by Administrator on 2017/11/16.
 */

public interface GetUser_Interface {

	@GET(GET_USER)
	Call<GetUserINFO> getResopnd(@Path("token") String token ,
								 @Header("Authorization") String authorization);


	//测试用
//	@GET("v1/user/8m2zv9u3gtog9fogkvpeei1tlb8w2gwr/info")
//	Call<GetUserINFO> getResopnd( @Header("Authorization") String authorization);
}
