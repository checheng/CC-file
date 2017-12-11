package com.wuxindianqi.administrator.chargingstationapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wuxindianqi.administrator.chargingstationapp.MyService;
import com.wuxindianqi.administrator.chargingstationapp.R;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ApiToken;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageEvent;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageLogin;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.Login;
import com.wuxindianqi.administrator.chargingstationapp.utils.Cookies.CookieManger;
import com.wuxindianqi.administrator.chargingstationapp.utils.SharedUtils;
import com.wuxindianqi.administrator.chargingstationapp.view.fragment.ChargingFragment;
import com.wuxindianqi.administrator.chargingstationapp.view.fragment.MapFragment;
import com.wuxindianqi.administrator.chargingstationapp.view.fragment.UserLoginFragment;
import com.wuxindianqi.administrator.chargingstationapp.view.fragment.UserLogoutFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.API_KEY;
import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.API_TOKEN;
import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.LOGIN_MARKER;
import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.Login_TOKEN;

public class MainActivity extends ExitApplicationActivity implements View.OnClickListener{

	private MapFragment mMapFragment;
	private UserLoginFragment mUserLoginFragment;
	private ChargingFragment mChargingFragment;
	private UserLogoutFragment mUserLogoutFragment;
	private Button btnMap;
	private Button btnCharging;
	private Button btnINFO;

	private FragmentTransaction mFragmentTransaction;
	private FragmentManager mFragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main );
		initview();
		initFragment();
		EventBus.getDefault().register(this);
		startService(new Intent(this, MyService.class));


		if (API_TOKEN.length() == 0) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					EventBus.getDefault().post(new MessageEvent(MessageEvent.RequestAPIKEY, new ApiToken(API_KEY)));
				}
			}, 100);
		}

		if (SharedUtils.getStatus(getApplicationContext())) {
			if (SharedUtils.getIdentity(getApplicationContext()).length() > 0 && (SharedUtils.getIDPassword(getApplicationContext()).length() > 0)) {
				if (Login_TOKEN.length() == 0) {
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							Login login = new Login(SharedUtils.getIdentity(getApplicationContext()), SharedUtils.getIDPassword(getApplicationContext()));
							EventBus.getDefault().post(new MessageLogin(MessageEvent.RequestSignIn, login));
						}
					}, 100);
				}
			}
		}


//		requestToken();
//		requestRegister();
//		requestPHONE();
//		requestLogin();
//		requestOUT();
//		requestUserInfo();
	}

	@Override
	protected void onResume() {
		//登陆成功后返回到我的信息页
		int id = getIntent().getIntExtra("SignIn", 0);
//		Log.i("反馈队列ID", "" + id);
		if (id == 2) {
			replaceFragment(mUserLoginFragment);
//			Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
		}
		if (id== 1){
			replaceFragment(mUserLogoutFragment);
		}
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		stopService(new Intent(this, MyService.class));
		EventBus.getDefault().unregister(this);
		Log.e("关闭acitvity","关闭acitvity");
	}

	/**
	 * 订阅发来的网络请求
	 * */
	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void onRequest(MessageEvent messageEvent) {
		if (messageEvent.getMessageCode()==MessageEvent.AnswerSignIn){
			Log.i("信息","收到反馈登录");
		}
	}

	//fortest
	private boolean AA = true;
	private String theToken;

	private void initview() {
		btnMap = (Button) findViewById(R.id.btn_main_one);
		btnMap.setOnClickListener(this);
		btnCharging = (Button) findViewById(R.id.btn_main_two);
		btnCharging.setOnClickListener(this);
		btnINFO = (Button) findViewById(R.id.btn_main_three);
		btnINFO.setOnClickListener(this);
	}

	private void initFragment() {
		mMapFragment = new MapFragment();
		mChargingFragment = new ChargingFragment();
		mUserLoginFragment = new UserLoginFragment();
		mUserLogoutFragment = new UserLogoutFragment();
		mFragmentManager = getSupportFragmentManager();
		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.add(R.id.fl_main, mMapFragment, mMapFragment.getClass().getName());
		mFragmentTransaction.commit();
	}


	private void replaceFragment (Fragment fragment){
		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.setCustomAnimations(R.anim.slide_in_bottom,R.anim.slide_out_bottom);
		mFragmentTransaction.replace(R.id.fl_main,fragment,fragment.getClass().getName());
		mFragmentTransaction.addToBackStack(null);
		mFragmentTransaction.commit();
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_main_one:
				replaceFragment(mMapFragment);
				break;
			case R.id.btn_main_two:
				replaceFragment(mChargingFragment);
				break;
			case R.id.btn_main_three:
				if (LOGIN_MARKER) {
					replaceFragment(mUserLoginFragment);
				} else replaceFragment(mUserLogoutFragment);
				break;
		}
	}



	public OkHttpClient OkHttpC(){

		OkHttpClient.Builder builder = new OkHttpClient.Builder().cookieJar(new CookieManger(this));

		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.cookieJar(new CookieManger(this))
				.connectTimeout(5000, TimeUnit.SECONDS)
				.build();
		return okHttpClient;
	}

}
