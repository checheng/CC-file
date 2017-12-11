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

import com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton;
import com.wuxindianqi.administrator.chargingstationapp.R;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageEvent;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageModifyPWD;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ModifyUserPW;
import com.wuxindianqi.administrator.chargingstationapp.utils.Match.MatchPassWord;
import com.wuxindianqi.administrator.chargingstationapp.utils.SharedUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.clearLogin_TOKEN;

public class ChangePassWordActivity extends AppCompatActivity implements View.OnClickListener {

	private Button BackBTN,EnsureBTN;
	private EditText OldPWEdit,NewPWEdit,REPWEdit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_pass_word);
		initView();
		Initialization();
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

	public void initView() {
		BackBTN = (Button) findViewById(R.id.back_button_modidyPW);
		EnsureBTN = (Button) findViewById(R.id.ensure_button_modifyPW);
		OldPWEdit = (EditText) findViewById(R.id.oldpasswprd_edittext_modidyPW);
		NewPWEdit = (EditText) findViewById(R.id.password_edittext_modidyPW);
		REPWEdit = (EditText) findViewById(R.id.re_password_edittext_modidyPW);
	}

	public void Initialization(){
		REPWEdit.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (NewPWEdit.getText().toString().length() <= s.toString().length()) {
					if (!NewPWEdit.getText().toString().equals(s.toString())) {
						Toast.makeText(getApplicationContext(), "两次输入密码不一致", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.back_button_modidyPW:
				onBackPressed();
				break;
			case R.id.ensure_button_modifyPW:
				MatchPassWord matchPassWord = new MatchPassWord(getApplicationContext(), OldPWEdit.getText().toString());
				if (matchPassWord.getResult()) {
					matchPassWord.setPassWord(NewPWEdit.getText().toString());
					if (matchPassWord.getResult()) {
						if (matchPassWord.CheckRePassword(REPWEdit.getText().toString())) {
							ModifyUserPW modifyUserPW= new ModifyUserPW(MyApplicaiton.Login_TOKEN,
									OldPWEdit.getText().toString(),NewPWEdit.getText().toString());
							EventBus.getDefault().post(new MessageModifyPWD(MessageEvent.RequestModifyPassword,modifyUserPW));

						}
					}
				}
				break;
			default:
				break;
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void getBack(MessageModifyPWD modifyPWD){
		Log.i("信息","进入返回");
		if (modifyPWD.getMessageCode() == MessageEvent.AnswerModifyPassword){
			//清除登录TOKEN
			clearLogin_TOKEN();
			//清除持久化密码
			SharedUtils.setIdentityANDPassword(getApplicationContext(), SharedUtils.getIdentity(getApplicationContext()), "");
			Log.i("信息","进入返回");
			Intent intent =  new Intent(this,MainActivity.class).putExtra("SignIn",1);
			startActivityForResult(intent,1047);

		}
	}

}
