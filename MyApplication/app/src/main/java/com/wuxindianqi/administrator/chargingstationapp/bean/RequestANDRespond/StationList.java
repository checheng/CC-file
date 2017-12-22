package com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond;

import java.util.List;

/**
 * Created by checheng on 2017/12/19.
 */

public class StationList {
    private int status;
    private String msg ;
    private List<StationInfo> data ;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<StationInfo> getData() {
        return data;
    }

    public void setData(List<StationInfo> data) {
        this.data = data;
    }
}
