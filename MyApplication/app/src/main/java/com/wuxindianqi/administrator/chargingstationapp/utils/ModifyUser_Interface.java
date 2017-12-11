package com.wuxindianqi.administrator.chargingstationapp.utils;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ModifyUserINFORespond;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.wuxindianqi.administrator.chargingstationapp.bean.URLPath.MODIFY_USER;

/**
 * Created by Administrator on 2017/11/17.
 */

public interface ModifyUser_Interface {
	@PATCH(MODIFY_USER )
	Call<ModifyUserINFORespond> getRespond(@Path("token") String token ,
										   @Query("nickname") String nickname,
										   @Query("email") String email,
										   @Query("sex") int sex,
										   @Header("Authorization") String authorization);
}
