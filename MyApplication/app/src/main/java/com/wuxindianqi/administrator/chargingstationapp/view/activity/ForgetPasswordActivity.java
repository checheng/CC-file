package com.wuxindianqi.administrator.chargingstationapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wuxindianqi.administrator.chargingstationapp.R;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageEvent;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageForget;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageVerifyCode;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ForgetPW;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.SendSMSVerifyCode;
import com.wuxindianqi.administrator.chargingstationapp.utils.Match.MatchPassWord;
import com.wuxindianqi.administrator.chargingstationapp.utils.Match.MatchPhone;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener{

	private Button BackBTN,VerifyBTN,SignInPWBTN,EnsureBTN;
	private EditText PhoneNumEdit,PasswordEdit,RepassWordEdit,VerifyCodeEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget_password);
		initMyView();
		initMyIput();
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

	public void initMyView() {
		BackBTN = (Button) findViewById(R.id.back_button_forget);
		VerifyBTN = (Button) findViewById(R.id.obtain_verifycode_button_regsiter);
		SignInPWBTN = (Button) findViewById(R.id.signin_button_forget);
		EnsureBTN = (Button) findViewById(R.id.ensure_button);
		PhoneNumEdit = (EditText) findViewById(R.id.phone_number_edittext_forget);
		PasswordEdit = (EditText) findViewById(R.id.password_edittext_forget);
		RepassWordEdit = (EditText) findViewById(R.id.re_password_edittext_forget);
		VerifyCodeEdit = (EditText) findViewById(R.id.verifycode_edittext_forget);
	}

	public void initMyIput(){
		RepassWordEdit.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
//				Log.i("输入内容",PasswordEdit.getText().toString().length()+"////" +s.toString().length());
				if (PasswordEdit.getText().toString().length() <= s.toString().length()) {
					if (!PasswordEdit.getText().toString().equals(s.toString())) {
						Toast.makeText(getApplicationContext(), "两次输入密码不一致", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.back_button_forget:
				onBackPressed();
				break;
			case R.id.signin_button_forget:
				startActivity(new Intent(getApplicationContext(),LoginActivity.class));
				break;
			case R.id.obtain_verifycode_button_forget:
				if (new MatchPhone(getApplicationContext(), PhoneNumEdit.getText().toString()).getResult()) {
					SendSMSVerifyCode sendSMSVerifyCode = new SendSMSVerifyCode(PhoneNumEdit.getText().toString(), "password");
					Log.i("信息",""+sendSMSVerifyCode.getMobile());
					EventBus.getDefault().post(new MessageVerifyCode(MessageEvent.ObtainVerifyCode, sendSMSVerifyCode));
				}
				break;
			case R.id.ensure_button:
				if (new MatchPhone(getApplicationContext(), PhoneNumEdit.getText().toString()).getResult()) {
					if (new MatchPassWord(getApplicationContext(),PasswordEdit.getText().toString()).getResult()){
						if (VerifyCodeEdit.getText().toString().length()==6){
							ForgetPW forgetPW = new ForgetPW(PhoneNumEdit.getText().toString(),PasswordEdit.getText().toString(),VerifyCodeEdit.getText().toString());
							EventBus.getDefault().post(new MessageForget(MessageEvent.RequestForget,forgetPW));
							Log.i("请求修改密码",PasswordEdit.getText().toString());
						}else Toast.makeText(getApplicationContext(),"验证码长度不正确",Toast.LENGTH_SHORT).show();
					}
				}
				break;
			default:
				break;
		}
	}

	//确认收到短信验证码
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void getVerifyCode(MessageVerifyCode ss){
		if (ss.getMessageCode()==MessageEvent.AnswerVerifyCode){
			Log.i("信息",""+ss.getVerifyCode().getMobile());
		}
	}

	//确认修改完成
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void getBack(MessageForget ss) {
		Log.i("信息", "收到确认信息");
		if (ss.getMessageCode()== MessageEvent.AnswerForget){
			if (ss.getForgetPWRespond().getStatus()==0){
				Intent intent = new Intent(this,MainActivity.class);
				intent.putExtra("SignIn",1);
				startActivityForResult(intent,1047);
			}
		}
	}

}
