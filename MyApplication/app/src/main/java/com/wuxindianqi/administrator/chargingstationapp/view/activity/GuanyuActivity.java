package com.wuxindianqi.administrator.chargingstationapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wuxindianqi.administrator.chargingstationapp.R;

public class GuanyuActivity extends AppCompatActivity implements View.OnClickListener{

	private Button BackBTN,AboutWoostarBTN,VersionBTN,AdviceBTN,CheckUpadataBTN;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guanyu);
		initView();
	}

	public void initView(){
		BackBTN = (Button) findViewById(R.id.back_button_wuxin);
		AboutWoostarBTN = (Button) findViewById(R.id.about_guanyu_button);
		VersionBTN = (Button) findViewById(R.id.about_guanyu_button);
		AdviceBTN = (Button) findViewById(R.id.about_advice_button);
		CheckUpadataBTN = (Button) findViewById(R.id.about_check_updata_button);
	}
/*	public void initOnClick(){
		BackBTN.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}*/

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.back_button_wuxin:
				onBackPressed();
				break;
			case R.id.about_guanyu_button:
				startActivity(new Intent(this,WuxinInfoActivity.class));
				break;
			case R.id.about_version_button:
				Toast.makeText(this,"暂时无版本信息。",Toast.LENGTH_SHORT).show();
				break;
			case R.id.about_advice_button:
				break;
			case R.id.about_check_updata_button:
				Toast.makeText(this,"当前是最新版本。",Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
	}
}
