package com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond;

/**
 * Created by Administrator on 2017/11/9.
 */

public class RegisterRequest {

	private String mobile,password,verifyCode,platformCompanyCode="WHWX";

	public RegisterRequest(String mobile, String password, String verifyCode) {
		this.mobile = mobile;
		this.password = password;
		this.verifyCode = verifyCode;
	}

	public RegisterRequest(String mobile, String password, String verifyCode, String platformCompanyCode) {
		this.mobile = mobile;
		this.password = password;
		this.verifyCode = verifyCode;
		this.platformCompanyCode = platformCompanyCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getPlatformCompanyCode() {
		return platformCompanyCode;
	}

	public void setPlatformCompanyCode(String platformCompanyCode) {
		this.platformCompanyCode = platformCompanyCode;
	}
}