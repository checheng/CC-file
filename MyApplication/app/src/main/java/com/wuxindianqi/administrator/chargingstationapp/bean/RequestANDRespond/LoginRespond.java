package com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond;

/**
 * Created by Administrator on 2017/11/9.
 */

public class LoginRespond {
	private String msg;
	private int status;
	private UserInfo data;



	public LoginRespond(String msg, int status, UserInfo userINFO) {
		this.msg = msg;
		this.status = status;
		data = userINFO;
	}

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

	public  UserInfo getData() {
		return data;
	}

	public void setData( UserInfo data) {
		this.data = data;
	}
}
