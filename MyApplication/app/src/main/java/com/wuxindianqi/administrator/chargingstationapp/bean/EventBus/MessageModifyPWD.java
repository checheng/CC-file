package com.wuxindianqi.administrator.chargingstationapp.bean.EventBus;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ModifyUserPW;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ModifyUserPWRespond;

/**
 * Created by Administrator on 2017/12/6.
 */

public class MessageModifyPWD {
	private int MessageCode = 0;
	private ModifyUserPW mModifyUserPWd;
	private ModifyUserPWRespond mModifyUserPWDRespond;

	public MessageModifyPWD(int messageCode, ModifyUserPW modifyUserPWd) {
		MessageCode = messageCode;
		mModifyUserPWd = modifyUserPWd;
	}

	public MessageModifyPWD(int messageCode, ModifyUserPWRespond modifyUserPWDRespond) {
		MessageCode = messageCode;
		mModifyUserPWDRespond = modifyUserPWDRespond;
	}

	public int getMessageCode() {
		return MessageCode;
	}

	public void setMessageCode(int messageCode) {
		MessageCode = messageCode;
	}

	public ModifyUserPW getModifyUserPWd() {
		return mModifyUserPWd;
	}

	public void setModifyUserPWd(ModifyUserPW modifyUserPWd) {
		mModifyUserPWd = modifyUserPWd;
	}

	public ModifyUserPWRespond getModifyUserPWDRespond() {
		return mModifyUserPWDRespond;
	}

	public void setModifyUserPWDRespond(ModifyUserPWRespond modifyUserPWDRespond) {
		mModifyUserPWDRespond = modifyUserPWDRespond;
	}
}
