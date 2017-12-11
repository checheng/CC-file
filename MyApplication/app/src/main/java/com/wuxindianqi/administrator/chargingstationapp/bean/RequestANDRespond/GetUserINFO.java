package com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond;

import com.wuxindianqi.administrator.chargingstationapp.bean.UserInfo;

/**
 * Created by Administrator on 2017/11/9.
 */

public class GetUserINFO {
	private  int status;
	private UserInfo data;

	public GetUserINFO(int status, UserInfo userInfo) {
		this.status = status;
		data = userInfo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UserInfo getUserInfo() {
		return data;
	}

	public void setUserInfo(UserInfo userInfo) {
		data = userInfo;
	}

	/**
	 *
	 1400 TOKEN_NOT_FOUND 用户token不存在
	 1300 TOKEN_OUT_OF_DATE 用户token已过期
	 1400 USER_NOT_FOUND 用户不存在
	 10 NEED_LOGIN 用户未登录
	 1 SUCCESS 用户信息获取成功
	 * */

	public static final int
			TOKEN_NOT_FOUND = 1400,
			TOKEN_OUT_OF_DATE = 1300,
			USER_NOT_FOUND = 1400,
			NEED_LOGIN = 10,
			SUCCESS = 1;
}
