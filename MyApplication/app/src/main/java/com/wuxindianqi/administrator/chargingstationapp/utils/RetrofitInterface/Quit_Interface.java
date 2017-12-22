package com.wuxindianqi.administrator.chargingstationapp.utils.RetrofitInterface;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.QuitRespond;
import com.wuxindianqi.administrator.chargingstationapp.bean.URLPath;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Header;

/**
 * Created by Administrator on 2017/11/16.
 */

public interface Quit_Interface {

	@DELETE(URLPath.Quit_)
	Call<QuitRespond> getResond(@Header("Authorization") String authorization);

}
