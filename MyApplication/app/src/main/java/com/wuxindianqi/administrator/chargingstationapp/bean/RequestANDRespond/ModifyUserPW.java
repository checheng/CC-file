package com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond;

/**
 * Created by Administrator on 2017/11/9.
 */

public class ModifyUserPW {
	private String token,passwordOld,passwordNew;

	public ModifyUserPW(String token, String passwordOld, String passwordNew) {
		this.token = token;
		this.passwordOld = passwordOld;
		this.passwordNew = passwordNew;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPasswordOld() {
		return passwordOld;
	}

	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
	}

	public String getPasswordNew() {
		return passwordNew;
	}

	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}
}
