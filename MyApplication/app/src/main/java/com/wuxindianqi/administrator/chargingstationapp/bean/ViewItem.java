package com.wuxindianqi.administrator.chargingstationapp.bean;

/**
 * Created by Administrator on 2017/11/27.
 */

public class ViewItem {
	private String title;
	private int resId;

	public ViewItem(String title, int resId) {
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
