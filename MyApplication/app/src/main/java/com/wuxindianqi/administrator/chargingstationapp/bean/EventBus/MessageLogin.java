package com.wuxindianqi.administrator.chargingstationapp.bean.EventBus;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.Login;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.LoginRespond;

/**
 * Created by Administrator on 2017/12/6.
 */

public class MessageLogin {
	private int MessageCode = 0;
	private Login mLogin;
	private LoginRespond mLoginRespond;

	public MessageLogin(int messageCode, LoginRespond loginRespond) {
		MessageCode = messageCode;
		mLoginRespond = loginRespond;
	}

	public MessageLogin(int messageCode, Login login) {
		MessageCode = messageCode;
		mLogin = login;
	}

	public int getMessageCode() {
		return MessageCode;
	}

	public void setMessageCode(int messageCode) {
		MessageCode = messageCode;
	}

	public Login getLogin() {
		return mLogin;
	}

	public void setLogin(Login login) {
		mLogin = login;
	}

	public LoginRespond getLoginRespond() {
		return mLoginRespond;
	}

	public void setLoginRespond(LoginRespond loginRespond) {
		mLoginRespond = loginRespond;
	}
}
