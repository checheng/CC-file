package com.wuxindianqi.administrator.chargingstationapp.bean.EventBus;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ForgetPW;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ForgetPWRespond;

/**
 * Created by Administrator on 2017/12/8.
 */

public class MessageForget {
	private int MessageCode = 0;
	private ForgetPW mForgetPW;
	private ForgetPWRespond mForgetPWRespond;


	public MessageForget(int messageCode, ForgetPWRespond forgetPWRespond) {
		MessageCode = messageCode;
		mForgetPWRespond = forgetPWRespond;
	}

	public MessageForget(int messageCode, ForgetPW forgetPW) {
		MessageCode = messageCode;
		mForgetPW = forgetPW;
	}

	public int getMessageCode() {
		return MessageCode;
	}

	public void setMessageCode(int messageCode) {
		MessageCode = messageCode;
	}

	public ForgetPW getForgetPW() {
		return mForgetPW;
	}

	public void setForgetPW(ForgetPW forgetPW) {
		mForgetPW = forgetPW;
	}

	public ForgetPWRespond getForgetPWRespond() {
		return mForgetPWRespond;
	}

	public void setForgetPWRespond(ForgetPWRespond forgetPWRespond) {
		mForgetPWRespond = forgetPWRespond;
	}
}
