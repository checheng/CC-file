package com.wuxindianqi.administrator.chargingstationapp.bean.EventBus;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ModifyUserINFO;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ModifyUserINFORespond;

/**
 * Created by Administrator on 2017/12/6.
 */

public class MessageModifyUser {
	private int MessageCode = 0;
	private ModifyUserINFO mModifyUserINFO;
	private ModifyUserINFORespond mModifyUserINFORespond;

	public MessageModifyUser(int messageCode, ModifyUserINFO modifyUserINFO) {
		MessageCode = messageCode;
		mModifyUserINFO = modifyUserINFO;
	}

	public MessageModifyUser(int messageCode, ModifyUserINFORespond modifyUserINFORespond) {
		MessageCode = messageCode;
		mModifyUserINFORespond = modifyUserINFORespond;
	}

	public int getMessageCode() {
		return MessageCode;
	}

	public void setMessageCode(int messageCode) {
		MessageCode = messageCode;
	}

	public ModifyUserINFO getModifyUserINFO() {
		return mModifyUserINFO;
	}

	public void setModifyUserINFO(ModifyUserINFO modifyUserINFO) {
		mModifyUserINFO = modifyUserINFO;
	}

	public ModifyUserINFORespond getModifyUserINFORespond() {
		return mModifyUserINFORespond;
	}

	public void setModifyUserINFORespond(ModifyUserINFORespond modifyUserINFORespond) {
		mModifyUserINFORespond = modifyUserINFORespond;
	}
}
