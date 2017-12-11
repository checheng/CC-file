package com.wuxindianqi.administrator.chargingstationapp.bean.EventBus;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.RegisterRequest;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.RegisterRespond;

/**
 * Created by Administrator on 2017/12/6.
 */

public class MessageRegsiter {
	private int MessageCode = 0;
	private RegisterRequest mRegisterRequest;
	private RegisterRespond mRegisterRespond;

	public MessageRegsiter(int messageCode, RegisterRequest registerRequest) {
		MessageCode = messageCode;
		mRegisterRequest = registerRequest;
	}

	public MessageRegsiter(int messageCode, RegisterRespond registerRespond) {
		MessageCode = messageCode;
		mRegisterRespond = registerRespond;
	}

	public int getMessageCode() {
		return MessageCode;
	}

	public void setMessageCode(int messageCode) {
		MessageCode = messageCode;
	}

	public RegisterRequest getRegisterRequest() {
		return mRegisterRequest;
	}

	public void setRegisterRequest(RegisterRequest registerRequest) {
		mRegisterRequest = registerRequest;
	}

	public RegisterRespond getRegisterRespond() {
		return mRegisterRespond;
	}

	public void setRegisterRespond(RegisterRespond registerRespond) {
		mRegisterRespond = registerRespond;
	}
}
