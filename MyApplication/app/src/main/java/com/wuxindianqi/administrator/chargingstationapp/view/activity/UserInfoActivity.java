package com.wuxindianqi.administrator.chargingstationapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wuxindianqi.administrator.chargingstationapp.R;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageEvent;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageModifyUser;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ModifyUserINFO;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.sUserInfo;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener{

	private TextView NickNameText,PhoneText,EmailText;
	private Button BackBTN,FinishedBTN,ModifyBTN;
	private SwitchCompat SexSwitch;
	private int sex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Initializtion();
	}

	private void initView() {
		NickNameText = (TextView) findViewById(R.id.nick_name_text);
		PhoneText = (TextView) findViewById(R.id.phone_text);
		EmailText = (TextView) findViewById(R.id.email_text);

		BackBTN = (Button) findViewById(R.id.back_button);
		FinishedBTN = (Button) findViewById(R.id.finished_button);
		ModifyBTN = (Button) findViewById(R.id.modifypassword_button);

		SexSwitch = (SwitchCompat)findViewById(R.id.sc_switch_compat);
		SexSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)sex = 2;
				else sex = 1;
			}
		});
	}

	private void Initializtion(){
		NickNameText.setText(sUserInfo.getNickname());
		PhoneText.setText(sUserInfo.getMobile());
		EmailText.setText(sUserInfo.getEmail());
		if (sUserInfo.getSex() != 2) {
			SexSwitch.setChecked(false);
		}else SexSwitch.setChecked(true);
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void getback(MessageModifyUser ss){
		if (ss.getMessageCode() == MessageEvent.AnswerModifyUserInfo){
			Intent intent = new Intent(this,MainActivity.class);
			intent.putExtra("SignIn",2);
			startActivityForResult(intent,1045);
		}
	}



	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.back_button:
				onBackPressed();
				break;
			case R.id.finished_button:
				//若有修改
				Log.i("对比信息",sex +"///"+ sUserInfo.getSex());
				Log.i("对比信息",EmailText.getText().toString() +"///"+ sUserInfo.getEmail());
				Log.i("对比信息",NickNameText.getText().toString() +"///"+ sUserInfo.getNickname());
				if (sex != sUserInfo.getSex()
						|| (!EmailText.getText().toString().equals(sUserInfo.getEmail()))
						|| (!NickNameText.getText().toString().equals(sUserInfo.getNickname()))) {
					sUserInfo.setSex(sex);
					sUserInfo.setEmail(EmailText.getText().toString());
					sUserInfo.setNickname(NickNameText.getText().toString());
					ModifyUserINFO modifyUserINFO = new ModifyUserINFO(
							sUserInfo.getToken(),
							sUserInfo.getNickname(),
							sUserInfo.getEmail(),
							sUserInfo.getSex()
					);
					Log.i("修改信息", sUserInfo.getNickname() + "///" + sUserInfo.getSex());
					EventBus.getDefault().post(new MessageModifyUser(MessageEvent.RequestModifyUserInfo, modifyUserINFO));
					break;

				}
				Toast.makeText(getApplicationContext(), "无修改信息", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(this,MainActivity.class);
				intent.putExtra("SignIn",2);
				startActivityForResult(intent,1045);
				break;
			case R.id.modifypassword_button:
				startActivity(new Intent(this,ChangePassWordActivity.class));
				break;
			default:break;
		}
	}
}
