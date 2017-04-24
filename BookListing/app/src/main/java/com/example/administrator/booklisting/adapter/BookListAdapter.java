package com.example.administrator.booklisting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.booklisting.R;
import com.example.administrator.booklisting.bean.Book;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class BookListAdapter extends BaseAdapter{

	private List<Book> mBookList;
	private Context mContext;

	public BookListAdapter(List<Book> bookList, Context context) {
		mBookList = bookList;
		mContext = context;
	}

	@Override
	public int getCount() {
		return mBookList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		try {
			ViewHolder viewHolder;
			if (convertView == null){
				viewHolder = new ViewHolder();
				convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
				viewHolder.author = (TextView)convertView.findViewById(R.id.book_author);
				viewHolder.title = (TextView)convertView.findViewById(R.id.book_title);
				viewHolder.summary = (TextView)convertView.findViewById(R.id.book_summary);
				viewHolder.images_medium = (ImageView)convertView.findViewById((R.id.book_images));
				convertView.setTag(viewHolder);
			}
			else {
				viewHolder = (ViewHolder)convertView.getTag();
			}
			viewHolder.title.setText(mBookList.get(position).getTitle());
			viewHolder.author.setText(mBookList.get(position).getAuthor());
			viewHolder.summary.setText(mBookList.get(position).getSummary());

		}catch (Exception e){
			e.printStackTrace();
		}
		return convertView;
	}

	private   class  ViewHolder{
		private TextView title, author,summary;
		private ImageView images_medium;
	}
}
