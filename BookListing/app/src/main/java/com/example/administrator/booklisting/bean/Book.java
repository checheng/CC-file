package com.example.administrator.booklisting.bean;

/**
 * Created by Administrator on 2017/4/24.
 */

public class Book {
	private String title = "";
	private String author = "";
	private String summary = "";
	private String images_medium = "";

	public Book(){

	}

	public Book(String title, String author, String summary, String images_medium) {
		this.title = title;
		this.author = author;
		this.summary = summary;
		this.images_medium = images_medium;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getImages_medium() {
		return images_medium;
	}

	public void setImages_medium(String images_medium) {
		this.images_medium = images_medium;
	}
}
