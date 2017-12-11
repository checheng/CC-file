package com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond;

/**
 * Created by Administrator on 2017/11/9.
 */

public class SendSMSVerifyCode {
	private String mobile,use;

	public SendSMSVerifyCode(String mobile, String use) {
		this.mobile = mobile;
		this.use = use;
	}

	public SendSMSVerifyCode(String mobile) {
		this.mobile = mobile;
		use = "register";
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}
}
