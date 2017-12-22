package com.wuxindianqi.administrator.chargingstationapp.utils.RetrofitInterface;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.BalanceRespond;
import com.wuxindianqi.administrator.chargingstationapp.bean.URLPath;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/12/7.
 */

public interface Balance_Interface {
	@GET(URLPath.GET_BALANCE)
	Call<BalanceRespond> getCall(@Path("token") String token ,
								 @Header("Authorization") String authorization);
}
