package com.wuxindianqi.administrator.chargingstationapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton;
import com.wuxindianqi.administrator.chargingstationapp.R;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageEvent;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageLogin;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.Login;
import com.wuxindianqi.administrator.chargingstationapp.utils.SharedUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.getCacheID;
import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.getCachePWD;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

	private Button BackBTN,RegisterBTN,ForgetPWBTN,SignInBTN;
	private EditText PhoneNumEdit,PasswordEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initMyView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		EventBus.getDefault().register(this);
		PhoneNumEdit.setText(SharedUtils.getIdentity(getApplicationContext()));
		PasswordEdit.setText(SharedUtils.getIDPassword(getApplicationContext()));
	}



	@Override
	protected void onPause() {
		super.onPause();
		EventBus.getDefault().unregister(this);
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void getBack(MessageLogin messageEvent){
		try {
			if (messageEvent.getMessageCode()==MessageEvent.AnswerSignIn){
				if (messageEvent.getLoginRespond().getStatus()==0){
					//成功登陆
					Intent intent = new Intent(this,MainActivity.class);
					intent.putExtra("SignIn",2);
					startActivityForResult(intent,1045);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initMyView() {
		BackBTN = (Button) findViewById(R.id.back_button_signin);
		RegisterBTN = (Button) findViewById(R.id.register_button_signin);
		ForgetPWBTN = (Button) findViewById(R.id.forget_password_button_signin);
		SignInBTN = (Button) findViewById(R.id.signin_button_for_signin);
		PhoneNumEdit = (EditText) findViewById(R.id.phone_number_edittext_signin);
		PasswordEdit = (EditText) findViewById(R.id.password_edittext_signin);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.back_button_signin:
				onBackPressed();
				break;
			case R.id.register_button_signin:
				startActivity(new Intent(this, RegisterActivity.class));
				break;
			case R.id.forget_password_button_signin:
				startActivity(new Intent(this, ForgetPasswordActivity.class));
				break;
			case R.id.signin_button_for_signin:
				Login login = new Login(PhoneNumEdit.getText().toString(), PasswordEdit.getText().toString());
//				Log.i("存储   信息",login.getMobile()+"//"+ login.getPassword());

				if (SharedUtils.setIdentityANDPassword(getApplicationContext(), login.getMobile(), login.getPassword())) {
					MyApplicaiton.setCacheID(PhoneNumEdit.getText().toString());
					MyApplicaiton.setCachePWD(PasswordEdit.getText().toString());
					Log.i("缓存信息", getCacheID() + "//" + getCachePWD());
					EventBus.getDefault().post(new MessageLogin(MessageEvent.RequestSignIn, login));
//					SharedUtils.getIdentity(getApplicationContext());
//					SharedUtils.getIDPassword(getApplicationContext());
				}
				break;
			default:
				break;
		}
	}
}
