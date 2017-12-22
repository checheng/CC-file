package com.wuxindianqi.administrator.chargingstationapp.bean.RecyclerViewItem;

/**
 * Created by Administrator on 2017/11/27.
 */

public class LoginViewItem {
	private String title;
	private int resId;

	public LoginViewItem(String title, int resId) {
		this.title = title;
		this.resId = resId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}
}
