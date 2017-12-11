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
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageRegsiter;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageVerifyCode;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.Login;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.RegisterRequest;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.SendSMSVerifyCode;
import com.wuxindianqi.administrator.chargingstationapp.utils.Match.MatchPassWord;
import com.wuxindianqi.administrator.chargingstationapp.utils.Match.MatchPhone;
import com.wuxindianqi.administrator.chargingstationapp.utils.SharedUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

	private Button BackBTN,SignInBTN,VerifyBTN,RegisterBTN;
	private EditText PhoneNumEdit,PasswordEdit,VerifyCodeEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regsiter);
		initMyView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		EventBus.getDefault().register(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		EventBus.getDefault().unregister(this);
	}

	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void getRegisterBack(MessageRegsiter messageEvent){
		try {
			if (messageEvent.getMessageCode()==MessageEvent.AnswerRegsiter){
				if (messageEvent.getRegisterRespond().getStatus()==0){
					//成功注册成功
					Login login = new Login(SharedUtils.getIdentity(getApplicationContext()),SharedUtils.getIDPassword(getApplicationContext()));
					Log.i("注册信息",SharedUtils.getIdentity(getApplicationContext())+"///"+SharedUtils.getIDPassword(getApplicationContext()));
					//发送登录请求
					EventBus.getDefault().post(new MessageLogin(MessageEvent.RequestSignIn,login));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void getSignInBack(MessageLogin messageEvent) {
		try {
			if (messageEvent.getMessageCode() == MessageEvent.AnswerSignIn) {
				if (messageEvent.getLoginRespond().getStatus() == 0) {
					Intent intent = new Intent(this,MainActivity.class);
					intent.putExtra("SignIn",2);
					startActivityForResult(intent,1045);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void initMyView (){
		BackBTN = (Button) findViewById(R.id.back_button_register);
		SignInBTN = (Button) findViewById(R.id.signin_button_register);
		VerifyBTN = (Button)findViewById(R.id.obtain_verifycode_button_regsiter);
		RegisterBTN = (Button) findViewById(R.id.register_button);
		PhoneNumEdit = (EditText) findViewById(R.id.phone_number_edittext_register);
		PasswordEdit = (EditText) findViewById(R.id.password_edittext_register);
		VerifyCodeEdit = (EditText) findViewById(R.id.verifycode_edittext_register);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.back_button_register:
				onBackPressed();
				break;
			case R.id.signin_button_register:
				startActivity(new Intent(this, LoginActivity.class));
				break;
			case R.id.obtain_verifycode_button_regsiter:
				if (new MatchPhone(this,PhoneNumEdit.getText().toString()).getResult()){
//					Log.i("",PhoneNumEdit.getText().toString());
					SendSMSVerifyCode verifyCode = new SendSMSVerifyCode(PhoneNumEdit.getText().toString());
					EventBus.getDefault().post(new MessageVerifyCode(MessageEvent.ObtainVerifyCode,verifyCode));
				}
				break;
			case R.id.register_button:
				if (new MatchPhone(this, PhoneNumEdit.getText().toString()).getResult()) {
					if (new MatchPassWord(this, PasswordEdit.getText().toString()).getResult()) {
						if (VerifyCodeEdit.getText().toString().length() == 6) {
							Log.i("", PhoneNumEdit.getText().toString() + "//" + PasswordEdit.getText().toString());
							RegisterRequest registerRequest = new RegisterRequest(
									PhoneNumEdit.getText().toString(),
									PasswordEdit.getText().toString(),
									VerifyCodeEdit.getText().toString());
							EventBus.getDefault().post(new MessageRegsiter(MessageEvent.RequestRegsiter, registerRequest));
							//缓存
							MyApplicaiton.setCacheID(PhoneNumEdit.getText().toString());
							MyApplicaiton.setCachePWD(PasswordEdit.getText().toString());
						}
					}
				}
				break;
			default:
				break;
		}

	}
}
