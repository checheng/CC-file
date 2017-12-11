package com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond;

/**
 * Created by Administrator on 2017/11/9.
 */

public class RegisterRespond {
	private String  msg;
	private  int status;

	public RegisterRespond(String msg, int status) {
		this.msg = msg;
		this.status = status;
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
	 * 1409 MOBILE_USED 手机号已被注册
	 * 1407 VERIFY_CODE_ERROR 短信验证码错误
	 * 1406 VERIFY_CODE_OUT_OF_DATE 短信验证码已过期
	 * 1400 VERIFY_CODE_NOT_FOUND 未获取短信验证码
	 * 1401 PLEASE_USE_A_PHONE_NUMBER 未输入正确格式的手机号
	 *  1 USER_BALANCE_STORAGE_ERROR 用户余额存储失败
	 *  1 USER_INFO_STORAGE_ERROR 用户信息储存失败
	 *  0 SUCCESS 注册成功
	 * */

	public static final int
			MOBILE_USED = 1409,
			VERIFY_CODE_ERROR = 1407,
			VERIFY_CODE_OUT_OF_DATE = 1406,
			VERIFY_CODE_NOT_FOUND = 1400,
			PLEASE_USE_A_PHONE_NUMBER = 1401,
			USER_BALANCE_STORAGE_ERROR = 1,
			USER_INFO_STORAGE_ERROR = 1,
			SUCCESS = 0;

}
