package com.wuxindianqi.administrator.chargingstationapp.bean.EventBus;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.QuitRespond;

/**
 * Created by Administrator on 2017/12/6.
 */

public class MessageQuit {
	private int MessageCode = 0;
	private QuitRespond mQuitRespond;

	public MessageQuit(int messageCode) {
		MessageCode = messageCode;
	}

	public MessageQuit(int messageCode, QuitRespond quitRespond) {
		MessageCode = messageCode;
		mQuitRespond = quitRespond;
	}

	public int getMessageCode() {
		return MessageCode;
	}

	public void setMessageCode(int messageCode) {
		MessageCode = messageCode;
	}

	public QuitRespond getQuitRespond() {
		return mQuitRespond;
	}

	public void setQuitRespond(QuitRespond quitRespond) {
		mQuitRespond = quitRespond;
	}
}
