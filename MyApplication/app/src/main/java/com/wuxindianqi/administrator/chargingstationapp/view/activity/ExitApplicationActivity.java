package com.wuxindianqi.administrator.chargingstationapp.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

public class ExitApplicationActivity extends AppCompatActivity {

	/**
	 * 继承后用于双击退出应用程序
	 *
	 * */
	private MyBaseActiviy_Broad mBroad;
	public static final String EXIT_Application= "exit_application";
	public static final String EXIT_ACTION_Application = "com.wuxindianqi.administrator.chargingstationapp.view.activity.ExitApplicationActivity";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBroad = new MyBaseActiviy_Broad();
		IntentFilter intentFilter = new IntentFilter(EXIT_ACTION_Application);
		registerReceiver(mBroad,intentFilter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mBroad);
	}

	public class MyBaseActiviy_Broad extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {

			if (intent.getStringExtra(EXIT_Application).equals(EXIT_Application)) {
				finish();
			}
		}
	}

	private long firstTime=0;

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		switch (keyCode) {
			case KeyEvent.KEYCODE_BACK:
				long secondTime = System.currentTimeMillis();
				if (secondTime - firstTime > 2000) {
					Toast.makeText(getApplication(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
					firstTime = secondTime;
					return true;
				} else {
					Intent intent = new Intent(EXIT_ACTION_Application).putExtra(EXIT_Application,EXIT_Application);
					sendBroadcast(intent);
				}
				break;
		}
		return super.onKeyUp(keyCode, event);
	}
}
