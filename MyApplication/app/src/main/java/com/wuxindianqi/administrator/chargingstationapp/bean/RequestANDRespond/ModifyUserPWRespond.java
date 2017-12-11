package com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond;

/**
 * Created by Administrator on 2017/11/9.
 */

public class ModifyUserPWRespond {
	private int status;
	private String msg;

	public ModifyUserPWRespond(int status, String msg) {
		this.status = status;
		this.msg = msg;
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

	/**
	 1400 USER_NOT_FOUND 用户不存在
	 1400 TOKEN_NOT_FOUND token不存在
	 1300 TOKEN_OUT_OF_DATE token已过期
	 1411 PASSWORD_ERROR 旧密码错误
	 10 NEED_LOGIN 用户未登录
	 1 PASSWORD_UPDATE_ERROR 密码修改失败
	 0 SUCCESS 密码修改成功
	 * */

	public static final int
			USER_NOT_FOUND = 1400,
			TOKEN_NOT_FOUND = 1400,
			TOKEN_OUT_OF_DATE = 1300,
			PASSWORD_ERROR = 1411,
			NEED_LOGIN = 10,
			PASSWORD_UPDATE_ERROR = 1,
			SUCCESS = 0;

}