package com.wuxindianqi.administrator.chargingstationapp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wuxindianqi.administrator.chargingstationapp.R;

public class ConsumptionRecordActivity extends AppCompatActivity {

	private  Button BackRecordBTN;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consumption_record);
		initView();
		initOnClick();
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	private void initView(){
		BackRecordBTN = (Button)findViewById(R.id.back_button_record);
	}
	private void initOnClick(){
		BackRecordBTN.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}
}
