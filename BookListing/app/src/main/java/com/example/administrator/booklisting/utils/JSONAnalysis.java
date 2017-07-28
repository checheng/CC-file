package com.example.administrator.booklisting.utils;

import com.example.administrator.booklisting.bean.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.booklisting.utils.JSONAnalysis.StringWord.THE_AUTHR;
import static com.example.administrator.booklisting.utils.JSONAnalysis.StringWord.THE_BOOK;
import static com.example.administrator.booklisting.utils.JSONAnalysis.StringWord.THE_IMAGES;
import static com.example.administrator.booklisting.utils.JSONAnalysis.StringWord.THE_MEDIUM;
import static com.example.administrator.booklisting.utils.JSONAnalysis.StringWord.THE_SUMMARY;
import static com.example.administrator.booklisting.utils.JSONAnalysis.StringWord.THE_TITLE;


/**
 * Created by Administrator on 2017/4/24.
 */

public class JSONAnalysis {

	public class StringWord {
		public static final String THE_BOOK = "books";
		public static final String THE_AUTHR = "author";
		public static final String THE_TITLE = "title";
		public static final String THE_SUMMARY= "summary";
		public static final String THE_IMAGES = "images";
		public static final String THE_MEDIUM = "medium";
	}
	private List<Book> mBooks = new ArrayList<Book>();
	private Book mBook=  new Book();

	public List<Book> JSONresolution (String jsonString){
		mBooks.clear();
		JSONObject jsonObject = null;
		JSONArray jsonBooks = null;

		try {
			jsonObject = new JSONObject(jsonString);
			jsonBooks = jsonObject.optJSONArray(THE_BOOK);

			for (int i = 0; i < jsonBooks.length(); i++) {
				JSONObject jsonBook = jsonBooks.optJSONObject(i);
				StringBuilder myauthor = new StringBuilder();
				for (int m = 0; m < jsonBook.optJSONArray(THE_AUTHR).length(); m++) {
					if (m != 0) myauthor.append("|");
					myauthor.append(jsonBook.optJSONArray(THE_AUTHR).opt(m));
				}
				mBook = new Book(jsonBook.optString(THE_TITLE), myauthor.toString(),
						jsonBook.optString(THE_SUMMARY), jsonBook.optJSONObject(THE_IMAGES).getString(THE_MEDIUM));
				mBooks.add(mBook);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return mBooks;
	}




}
