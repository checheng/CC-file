package com.wuxindianqi.administrator.chargingstationapp.utils.RetrofitInterface;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ModifyUserPWRespond;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.wuxindianqi.administrator.chargingstationapp.bean.URLPath.MODIFY_PASSWORD;

/**
 * Created by Administrator on 2017/11/27.
 */

public interface ModifyPassword_Interface {
	@PATCH(MODIFY_PASSWORD)
	Call<ModifyUserPWRespond> getRespond(
			@Path("token") String token ,
			@Query("passwordOld") String passwordOld,
			@Query("passwordNew") String passwordNew,
			@Header("Authorization") String authorization
	);
}
