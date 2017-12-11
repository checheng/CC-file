package com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond;

/**
 * Created by Administrator on 2017/11/9.
 */

public class SendSMSVerifyCodeRespond {
	private String  msg;
	private  int status;

	public SendSMSVerifyCodeRespond(String msg, int status) {
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
	 * 1401 PLEASE_USE_A_PHONE_NUMBER 未输入正确格式的手机号
	 * 1409 MOBILE_USED 手机号已被注册
	 * 1410 VERIFY_CODE_TOO_FREQUENTLY 验证码发送间隔过短（一分钟）
	 * 1 VERIFY_CODE_STORAGE_ERROR 短信验证码存储错误
	 * 0 SUCCESS 发送成功
	 * */

	public static final int
			PLEASE_USE_A_PHONE_NUMBER = 1401,
			MOBILE_USED = 1409,
			VERIFY_CODE_TOO_FREQUENTLY = 1410,
			VERIFY_CODE_STORAGE_ERROR = 1,
			SUCCESS = 0;
}
