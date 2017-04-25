package com.example.administrator.booklisting.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.booklisting.R;
import com.example.administrator.booklisting.adapter.BookListAdapter;
import com.example.administrator.booklisting.bean.Book;
import com.example.administrator.booklisting.http.HttpConnectionMethod;
import com.example.administrator.booklisting.utils.JSONAnalysis;

import java.util.List;

import static com.example.administrator.booklisting.http.HttpConnectionMethod.getURLResponse;

public class MainActivity extends AppCompatActivity {
	private HttpConnectionMethod mHttpConnectionMethod;
	private JSONAnalysis mAnalysis = new JSONAnalysis();
	private Button research_btn;
	private List<Book> mBookList;
	private EditText research;
	private ListView mListView;
	private BookListAdapter mBookListAdapter;
	private Context mContext = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initview();
		init();


	}
	private void initview(){
		research = (EditText)findViewById(R.id.research);
		research_btn = (Button)findViewById(R.id.research_BTN);
		mListView = (ListView)findViewById(R.id.Bookslist);
	}

	private void init() {
		research_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (research.getText().toString().trim().equals("")) {
					Toast toast = Toast.makeText(getApplicationContext(), "请输入查询关键字!", Toast.LENGTH_SHORT);
					toast.show();
				} else {
					StringBuilder  m =new StringBuilder();
					m.append("https://api.douban.com/v2/book/search?q=").append(research.getText().toString().trim()).append("&count=15");
					new DownImgAsyncTask().execute(m.toString());
				}
			}
		});

	}

	//获取内容
	class DownImgAsyncTask extends AsyncTask<String, Integer, String> {

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected String doInBackground(String... params) {
			String urlString = getURLResponse(params[0]);
			return urlString;
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			if (s != null) {
				if (s.substring(0,4).equals("错误代码")){
					Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
				}else {
					mBookList = mAnalysis.JSONresolution(s);
					mBookListAdapter = new BookListAdapter(mBookList, mContext);
					mListView.setAdapter(mBookListAdapter);
				}
			}
			mBookListAdapter.notifyDataSetChanged();
		}
	}



}