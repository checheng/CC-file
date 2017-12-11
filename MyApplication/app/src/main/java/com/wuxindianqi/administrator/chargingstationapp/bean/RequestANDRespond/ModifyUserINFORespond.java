package com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond;

/**
 * Created by Administrator on 2017/11/9.
 */

public class ModifyUserINFORespond {
	private int status;
	private String msg;

	public ModifyUserINFORespond(int status, String msg) {
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
	 *
	 1401 PLEASE_USE_AN_EMAIL_ADDRESS 未输入正确格式的邮箱
	 1400 USER_NOT_FOUND 用户不存在
	 1400 TOKEN_NOT_FOUND token不存在
	 1300 TOKEN_OUT_OF_DATE token已过期
	 1409 NICKNAME_USED 昵称已被使用
	 1409 EMAIL_USED 邮箱已被使用
	 10 NEED_LOGIN 用户未登录
	 1 USER_INFO_STORAGE_ERROR 用户信息存储失败
	 0 SUCCESS 用户信息修改成功
	 * */

	public static final int
			PLEASE_USE_AN_EMAIL_ADDRESS = 1401,
			USER_NOT_FOUND = 1400,
			TOKEN_NOT_FOUND = 1400,
			TOKEN_OUT_OF_DATE = 1300,
			NICKNAME_USED = 1409,
			EMAIL_USED = 1409,
			NEED_LOGIN = 10,
			USER_INFO_STORAGE_ERROR = 1,
			SUCCESS = 0;

}
