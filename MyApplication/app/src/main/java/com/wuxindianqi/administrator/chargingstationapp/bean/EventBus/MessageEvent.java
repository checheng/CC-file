package com.wuxindianqi.administrator.chargingstationapp.bean.EventBus;

import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ApiToken;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ApiTokenRespond;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.GetUserINFO;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ModifyUserPW;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ModifyUserPWRespond;

/**
 * Created by Administrator on 2017/11/29.
 */

public class MessageEvent {
	private int MessageCode = 0;
	private ApiToken mApiToken;
	private ApiTokenRespond mApiTokenRespond;
	private GetUserINFO mGetUserINFO;
	private ModifyUserPW mModifyUserPWd;
	private ModifyUserPWRespond mModifyUserPWDRespond;

	public static final int RequestAPIKEY = 104501;
	public static final int AnswerAPIKEY = 104401;
	public static final int RequestRegsiter = 104502;
	public static final int AnswerRegsiter = 104402;
	public static final int RequestSignIn = 104503;
	public static final int AnswerSignIn = 104403;
	public static final int ObtainVerifyCode = 104504;
	public static final int AnswerVerifyCode = 104404;
	public static final int RequestUserInfo = 104505;
	public static final int AnswerUserInfo = 104405;
	public static final int RequestModifyUserInfo = 104506;
	public static final int AnswerModifyUserInfo = 104406;
	public static final int RequestModifyPassword = 104507;
	public static final int AnswerModifyPassword = 104407;
	public static final int RequestQuit = 104508;
	public static final int AnswerQuit = 104408;
	public static final int RequestBalance = 104509;
	public static final int AnswerBalance = 104409;
	public static final int RequestForget  = 104510;
	public static final int AnswerForget = 104410;
	public static final int RequestStation = 104511;
	public static final int AnswerStation = 104411;



	public int getMessageCode() {
		return MessageCode;
	}

	public void setMessageCode(int messageCode) {
		MessageCode = messageCode;
	}

	public MessageEvent(int messageCode) {
		MessageCode = messageCode;
	}

	/**
	 * 获取平台token
	 * */
	public MessageEvent(int messageCode, ApiToken apiToken) {
		MessageCode = messageCode;
		mApiToken = apiToken;
	}

	public MessageEvent(int messageCode, ApiTokenRespond apiTokenRespond) {
		MessageCode = messageCode;
		mApiTokenRespond = apiTokenRespond;
	}

	public ApiToken getApiToken() {
		return mApiToken;
	}

	public void setApiToken(ApiToken apiToken) {
		mApiToken = apiToken;
	}

	public ApiTokenRespond getApiTokenRespond() {
		return mApiTokenRespond;
	}

	public void setApiTokenRespond(ApiTokenRespond apiTokenRespond) {
		mApiTokenRespond = apiTokenRespond;
	}

	/**
	 *获取用户信息
	 * */
	public MessageEvent(int messageCode, GetUserINFO getUserINFO) {
		MessageCode = messageCode;
		mGetUserINFO = getUserINFO;
	}

	public GetUserINFO getGetUserINFO() {
		return mGetUserINFO;
	}

	public void setGetUserINFO(GetUserINFO getUserINFO) {
		mGetUserINFO = getUserINFO;
	}

}
