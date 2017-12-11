package com.wuxindianqi.administrator.chargingstationapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.wuxindianqi.administrator.chargingstationapp.utils.Match.MatchPassWord;
import com.wuxindianqi.administrator.chargingstationapp.utils.Match.MatchPhone;

/**
 * Created by Administrator on 2017/12/5.
 */

public class SharedUtils {


	//持久化账号密码
	private static SharedPreferences initShared (Context context){
		SharedPreferences share  = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
		return  share;
	}
	private static SharedPreferences.Editor initEditor(Context context){
		SharedPreferences share  = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = share.edit();
		return editor;
	}

	public static boolean setIdentityANDPassword(Context context,String identity,String password){
		Log.i("缓存信息",identity + "///"+ password);
		SharedPreferences.Editor editor = initEditor(context);
		MatchPhone matchPhone = new MatchPhone(context,identity);
		MatchPassWord matchPassWord =new MatchPassWord(context,password);
		if (matchPhone.getResult()){
			if (matchPassWord.getResult()){
				editor.putString("PhoneNumber",identity);
				editor.putString("PassWord",password);
				editor.commit();
				return true;
			}
			return false;
		}
		return false;
	}

	public static boolean ChangePassword(Context context, String password) {
		if (setIdentityANDPassword(context, SharedUtils.getIdentity(context), password)) {
			return true;
		} else
			return false;
	}

	public static boolean setLoginStatus(Context context,boolean status){
		initEditor(context).putBoolean("LoginStatus",status).commit();
//		Log.i("登录状态信息",getStatus(context)+"");
		return false;
	}

	public static String getIdentity(Context context) {
//		Log.i("提取账号密码",initShared(context).getString("PhoneNumber",""));
		return initShared(context).getString("PhoneNumber","");
	}

	public static String getIDPassword(Context context) {
//		Log.i("提取账号密码",initShared(context).getString("PassWord",""));
		return initShared(context).getString("PassWord","");
	}
	public static boolean getStatus(Context context) {
//		Log.i("提取登录状态",""+initShared(context).getBoolean("LoginStatus",false));
		return initShared(context).getBoolean("LoginStatus",false);
	}

	public static void clearContent(Context context){
		initEditor(context).clear().commit();
	}

}
