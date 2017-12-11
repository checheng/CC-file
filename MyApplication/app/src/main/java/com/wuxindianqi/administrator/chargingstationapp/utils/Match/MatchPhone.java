package com.wuxindianqi.administrator.chargingstationapp.utils.Match;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Administrator on 2017/12/4.
 */

public class MatchPhone {
	private Context mContext;
	private String mPhoneNum;

	public MatchPhone(Context context, String PhoneNum) {
		mContext = context;
		mPhoneNum = PhoneNum;
	}

	public boolean getResult(){
		if (mPhoneNum==""){
			Toast.makeText(mContext,"输入电话号码为空。",Toast.LENGTH_SHORT).show();
			return false;
		}
		if (mPhoneNum.length()!=11){
			Toast.makeText(mContext,"输入手机号码位数有误。",Toast.LENGTH_SHORT).show();
			return false;
		}
		if (!isChinaPhoneLegal(mPhoneNum)){
			Toast.makeText(mContext,"输入手机号码格式不符。",Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	/**
	 * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
	 * 此方法中前三位格式有：
	 * 13+任意数
	 * 15+除4的任意数
	 * 18+除1和4的任意数
	 * 17+除9的任意数
	 * 147
	 */
	private boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
		String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
}
