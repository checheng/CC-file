package com.example.administrator.tourguide;

/**
 * Created by Administrator on 2017/3/25.
 */

public class ListContentDB {
	private int mImage;//图片
	private String mLocation;//位置信息
	private String mGrade;//评分
	private String mPrice;//价格
	private String mBriefIntroduction;//简介
	private float mRating;

	public ListContentDB(int image, String location, String grade, String price, String briefIntroduction, float rating) {
		mImage = image;
		mLocation = location;
		mGrade = grade;
		mPrice = price;
		mBriefIntroduction = briefIntroduction;
		mRating = rating;
	}

	public float getRating() {
		return mRating;
	}

	public void setRating(float rating) {
		mRating = rating;
	}

	public int getImage() {
		return mImage;
	}

	public void setImage(int image) {
		mImage = image;
	}

	public String getLocation() {
		return mLocation;
	}

	public void setLocation(String location) {
		mLocation = location;
	}

	public String getGrade() {
		return mGrade;
	}

	public void setGrade(String grade) {
		mGrade = grade;
	}

	public String getPrice() {
		return mPrice;
	}

	public void setPrice(String price) {
		mPrice = price;
	}

	public String getBriefIntroduction() {
		return mBriefIntroduction;
	}

	public void setBriefIntroduction(String briefIntroduction) {
		mBriefIntroduction = briefIntroduction;
	}
}
