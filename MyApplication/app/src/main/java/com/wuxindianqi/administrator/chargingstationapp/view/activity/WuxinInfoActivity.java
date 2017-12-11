package com.wuxindianqi.administrator.chargingstationapp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wuxindianqi.administrator.chargingstationapp.R;

public class WuxinInfoActivity extends AppCompatActivity{

	private TextView WuxinText;
	private Button BackBTN;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wuxin_info);
		initView();
		initOnClick();
	}
	public void initView(){
		WuxinText = (TextView)findViewById(R.id.wuxin_textView);
		BackBTN = (Button)findViewById(R.id.back_button_wuxin);
	}
	public void initOnClick(){
		BackBTN.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}

}
