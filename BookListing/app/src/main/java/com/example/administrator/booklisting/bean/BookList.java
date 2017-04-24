package com.example.administrator.booklisting.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class BookList {
	private List<Book> mBooks;
	private int position;

	public BookList(Book[] books) {
		mBooks = new ArrayList<Book>();
		mBooks = Arrays.asList(books);
	}

	public BookList(List<Book> books) {
		mBooks = new ArrayList<Book>();
		mBooks = books;
	}

	public BookList(){
		mBooks = new ArrayList<Book>();
	}

	public void addBook(Book book){
		mBooks.add(book);
	}

	public boolean insertBook(Book book, int position) {
		if (mBooks.size() > position) {
			mBooks.set(position+1,book);
			return true;
		} else if (mBooks.size() == position+1) {
			mBooks.add(book);
			return true;
		} else return false;
	}

}
