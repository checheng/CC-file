package com.wuxindianqi.administrator.chargingstationapp.bean.EventBus;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.BalanceRespond;

/**
 * Created by Administrator on 2017/12/7.
 */

public class MessageBalance {
	private int MessageCode = 0;
	private BalanceRespond mBalance;

	public MessageBalance() {
		MessageCode = MessageEvent.RequestBalance;
	}

	public MessageBalance(int messageCode, BalanceRespond balance) {
		MessageCode = messageCode;
		mBalance = balance;
	}

	public int getMessageCode() {
		return MessageCode;
	}

	public void setMessageCode(int messageCode) {
		MessageCode = messageCode;
	}

	public BalanceRespond getBalance() {
		return mBalance;
	}

	public void setBalance(BalanceRespond balance) {
		mBalance = balance;
	}
}
