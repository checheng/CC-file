package com.wuxindianqi.administrator.chargingstationapp.bean.EventBus;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.SendSMSVerifyCode;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.SendSMSVerifyCodeRespond;

/**
 * Created by Administrator on 2017/12/6.
 */

public class MessageVerifyCode {
	private int MessageCode = 0;
	private SendSMSVerifyCode mVerifyCode;
	private SendSMSVerifyCodeRespond mVerifyCodeRespond;

	public MessageVerifyCode(int messageCode, SendSMSVerifyCode verifyCode) {
		MessageCode = messageCode;
		mVerifyCode = verifyCode;
	}

	public MessageVerifyCode(int messageCode, SendSMSVerifyCodeRespond verifyCodeRespond) {
		MessageCode = messageCode;
		mVerifyCodeRespond = verifyCodeRespond;
	}

	public int getMessageCode() {
		return MessageCode;
	}

	public void setMessageCode(int messageCode) {
		MessageCode = messageCode;
	}

	public SendSMSVerifyCode getVerifyCode() {
		return mVerifyCode;
	}

	public void setVerifyCode(SendSMSVerifyCode verifyCode) {
		mVerifyCode = verifyCode;
	}

	public SendSMSVerifyCodeRespond getVerifyCodeRespond() {
		return mVerifyCodeRespond;
	}

	public void setVerifyCodeRespond(SendSMSVerifyCodeRespond verifyCodeRespond) {
		mVerifyCodeRespond = verifyCodeRespond;
	}
}
