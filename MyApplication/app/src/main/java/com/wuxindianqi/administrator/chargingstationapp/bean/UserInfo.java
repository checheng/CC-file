package com.wuxindianqi.administrator.chargingstationapp.bean;

/**
 * Created by Administrator on 2017/11/9.
 */

public class UserInfo {
	private String token, mobile, registerDate;
	private int sex, balance;
	private long timeLimit;
	private String nickname, email, avatarUrl, weChatHeadImgUrl;


	public UserInfo(String token, String mobile, String registerDate, int sex, int balance, long timeLimit) {
		this.token = token;
		this.mobile = mobile;
		this.registerDate = registerDate;
		this.sex = sex;
		this.balance = balance;
		this.timeLimit = timeLimit;
		this.nickname = "";
		this.email = "";
		this.avatarUrl = "";
		this.weChatHeadImgUrl = "";
	}

	public UserInfo(String token, String mobile, String registerDate, int sex, int balance,
					long timeLimit, String nickname, String email, String avatarUrl, String weChatHeadImgUrl) {
		this.token = token;
		this.mobile = mobile;
		this.registerDate = registerDate;
		this.sex = sex;
		this.balance = balance;
		this.timeLimit = timeLimit;
		this.nickname = nickname;
		this.email = email;
		this.avatarUrl = avatarUrl;
		this.weChatHeadImgUrl = weChatHeadImgUrl;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public long getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(long timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getWeChatHeadImgUrl() {
		return weChatHeadImgUrl;
	}

	public void setWeChatHeadImgUrl(String weChatHeadImgUrl) {
		this.weChatHeadImgUrl = weChatHeadImgUrl;
	}
}
