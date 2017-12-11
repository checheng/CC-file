package com.wuxindianqi.administrator.chargingstationapp.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wuxindianqi.administrator.chargingstationapp.R;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageBalance;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.sUserInfo;

public class BalanceActivity extends AppCompatActivity implements View.OnClickListener{

	private Button BackBTN,RechargeBTN;
	private TextView BalanceText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_balance);
		initView();
	}
	public void initView(){
		BackBTN = (Button)findViewById(R.id.back_button_recharge);
		RechargeBTN =  (Button)findViewById(R.id.recharge_button);
		BalanceText = (TextView)findViewById(R.id.balance_text_balance);
	}

	@SuppressLint("SetTextI18n")
	protected void onResume() {
		super.onResume();
		EventBus.getDefault().post(new MessageBalance());
		Log.i("信息",sUserInfo.getBalance()+"");
		BalanceText.setText(sUserInfo.getBalance()+"元");
	}

	@SuppressLint("SetTextI18n")
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void getBack(MessageBalance ss){
		if (ss.getMessageCode()== MessageEvent.AnswerBalance){
			BalanceText.setText(ss.getBalance().getData()+"元");
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.back_button_recharge:
				onBackPressed();
				break;
			case R.id.recharge_button:
				break;
			default:
				break;
		}
	}
}
