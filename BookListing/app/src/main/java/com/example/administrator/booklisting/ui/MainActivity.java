package com.example.administrator.booklisting.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.booklisting.R;
import com.example.administrator.booklisting.bean.Book;
import com.example.administrator.booklisting.http.HttpConnectionMethod;
import com.example.administrator.booklisting.utils.JSONAnalysis;

import java.util.List;

import static com.example.administrator.booklisting.http.HttpConnectionMethod.getURLResponse;

public class MainActivity extends AppCompatActivity {
	private HttpConnectionMethod mHttpConnectionMethod;
	private JSONAnalysis mAnalysis = new JSONAnalysis();
	private Button test;
	private TextView mTextView;
	private List<Book> mBookList;
	private String resultStr = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initview();
		init();


	}
	private void initview(){
		test = (Button)findViewById(R.id.button);
		mTextView = (TextView)findViewById(R.id.textView);
	}

	private  void init(){
		test.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Thread visitBaiduThread = new Thread(new Runnable() {
					@Override
					public void run() {
						String data = getURLResponse("https://api.douban.com/v2/book/search?q=python&count=3");
						resultStr = data;
					}
				});
				visitBaiduThread.start();
				try {
					visitBaiduThread.join();
					if(!resultStr.equals("")){
						Log.w("123",resultStr);
						mBookList = mAnalysis.JSONresolution(resultStr);
						Log.w("1123",mBookList.get(0).getSummary());
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
