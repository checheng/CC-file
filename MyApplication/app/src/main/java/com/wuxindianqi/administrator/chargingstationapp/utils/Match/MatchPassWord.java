package com.wuxindianqi.administrator.chargingstationapp.utils.Match;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/12/4.
 */

public class MatchPassWord {
	private Context mContext;
	private String mPassWord;

	public MatchPassWord(Context context, String passWord) {
		mContext = context;
		mPassWord = passWord;
	}

	public String getPassWord() {
		return mPassWord;
	}

	public void setPassWord(String passWord) {
		mPassWord = passWord;
	}

	public boolean getResult(){
		if (mPassWord.equals("")){
			Toast.makeText(mContext,"输入密码为空。",Toast.LENGTH_SHORT).show();
			return false;
		}
		if (mPassWord.length()<6)
		{
			Toast.makeText(mContext,"输入密码长度不足6位。",Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	public boolean CheckRePassword(String rePassWord){
		if (rePassWord.equals("")){
			Toast.makeText(mContext,"输入重复密码为空。",Toast.LENGTH_SHORT).show();
			return false;
		}
		if (mPassWord.length()!=rePassWord.length()){
			Toast.makeText(mContext,"两次密码长度不一致。",Toast.LENGTH_SHORT).show();
			return false;
		}
		if (!mPassWord.equals(rePassWord)){
			Toast.makeText(mContext,"两次密码输入不一致。",Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
}
