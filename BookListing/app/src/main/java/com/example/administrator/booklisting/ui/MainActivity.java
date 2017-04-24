package com.example.administrator.booklisting.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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
	private Button research;
	private List<Book> mBookList;
	private String resultStr = "";
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
		research = (Button)findViewById(R.id.button);
		mListView = (ListView)findViewById(R.id.Bookslist);
	}

	private void init() {
		research.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Thread visitBaiduThread = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(1000);
						}catch (Exception e) {
							e.printStackTrace();
						}
						resultStr = getURLResponse("https://api.douban.com/v2/book/search?q=海贼王&count=15");
					}
				});
				visitBaiduThread.start();
				try {
					visitBaiduThread.join();
					if (!resultStr.equals("")) {
						mBookList = mAnalysis.JSONresolution(resultStr);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mBookListAdapter= new BookListAdapter(mBookList,mContext);
				mListView.setAdapter(mBookListAdapter);
			}
		});

	}

}
