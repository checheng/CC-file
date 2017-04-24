package com.example.administrator.booklisting.utils;

import com.example.administrator.booklisting.bean.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class JSONAnalysis {
	private List<Book> mBooks = new ArrayList<Book>();
	private Book mBook=  new Book();

	public List<Book> JSONresolution (String jsonString){
		JSONObject jsonObject = null;
		JSONArray jsonBooks = null;

		try {
			jsonObject = new JSONObject(jsonString);
			jsonBooks = jsonObject.optJSONArray("books");

			for (int i = 0; i <jsonBooks.length();i++){
				JSONObject jsonBook = jsonBooks.optJSONObject(i);
				mBook = new Book(jsonBook.optString("title"),jsonBook.optString("author"),
						jsonBook.optString("summary"),jsonBook.optJSONObject("images").getString("medium"));
				mBooks.add(mBook);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return mBooks;
	}
}