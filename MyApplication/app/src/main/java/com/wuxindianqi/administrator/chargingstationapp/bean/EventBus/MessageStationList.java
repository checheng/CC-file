package com.wuxindianqi.administrator.chargingstationapp.bean.EventBus;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.StationList;

/**
 * Created by checheng on 2017/12/19.
 */

public class MessageStationList {
    private int MessageCode = 0;
    private StationList stationList;

    public MessageStationList() {
        MessageCode = MessageEvent.RequestStation;
    }

    public MessageStationList(StationList stationList) {
        MessageCode = MessageEvent.AnswerStation;
        this.stationList = stationList;
    }

    public int getMessageCode() {
        return MessageCode;
    }

    public void setMessageCode(int messageCode) {
        MessageCode = messageCode;
    }

    public StationList getStationList() {
        return stationList;
    }

    public void setStationList(StationList stationList) {
        this.stationList = stationList;
    }
}
