package com.wuxindianqi.administrator.chargingstationapp.utils.RetrofitInterface;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ApiTokenRespond;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.StationList;
import com.wuxindianqi.administrator.chargingstationapp.bean.URLPath;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by checheng on 2017/12/19.
 */

public interface StationList_interface {

    @GET(URLPath.GET_STATION)
    Call<StationList> getCall(
            @Header("Authorization") String authorization
    );
}
