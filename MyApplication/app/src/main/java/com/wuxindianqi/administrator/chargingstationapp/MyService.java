package com.wuxindianqi.administrator.chargingstationapp;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageForget;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageStationList;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ApiTokenRespond;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.BalanceRespond;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageBalance;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageEvent;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageLogin;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageModifyPWD;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageModifyUser;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageQuit;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageRegsiter;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageVerifyCode;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ForgetPWRespond;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.LoginRespond;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ModifyUserINFORespond;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.ModifyUserPWRespond;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.QuitRespond;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.RegisterRespond;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.SendSMSVerifyCodeRespond;
import com.wuxindianqi.administrator.chargingstationapp.bean.RequestANDRespond.StationList;
import com.wuxindianqi.administrator.chargingstationapp.utils.RetrofitInterface.Balance_Interface;
import com.wuxindianqi.administrator.chargingstationapp.utils.Cookies.CookieManger;
import com.wuxindianqi.administrator.chargingstationapp.utils.RetrofitInterface.ForgetPW_Interface;
import com.wuxindianqi.administrator.chargingstationapp.utils.RetrofitInterface.GetToken_Interface;
import com.wuxindianqi.administrator.chargingstationapp.utils.RetrofitInterface.Login_Interface;
import com.wuxindianqi.administrator.chargingstationapp.utils.RetrofitInterface.ModifyPassword_Interface;
import com.wuxindianqi.administrator.chargingstationapp.utils.RetrofitInterface.ModifyUser_Interface;
import com.wuxindianqi.administrator.chargingstationapp.utils.RetrofitInterface.Quit_Interface;
import com.wuxindianqi.administrator.chargingstationapp.utils.RetrofitInterface.Register_Interface;
import com.wuxindianqi.administrator.chargingstationapp.utils.RetrofitInterface.SendSMSVerifyCod_Interface;
import com.wuxindianqi.administrator.chargingstationapp.utils.RetrofitInterface.StationList_interface;
import com.wuxindianqi.administrator.chargingstationapp.utils.SharedUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.ANDROID;
import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.BASE_URL;
import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.LOGIN_MARKER;
import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.Login_TOKEN;
import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.getCacheID;
import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.getCachePWD;
import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.setUserInfo;
import static com.wuxindianqi.administrator.chargingstationapp.view.activity.ExitApplicationActivity.EXIT_ACTION_Application;
import static com.wuxindianqi.administrator.chargingstationapp.view.activity.ExitApplicationActivity.EXIT_Application;

public class MyService extends Service {

	public static final String TAG = "MyService";


	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "onCreate() executed");
		EventBus.getDefault().register(this);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand() executed");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy() executed");
		EventBus.getDefault().unregister(this);
	}

	@Override
	public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
		return super.registerReceiver(receiver, filter);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw null;
	}

	/**
	 * 订阅发来的网络请求
	 */
	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void onRequest(MessageEvent ss) {
		try {
			switch (ss.getMessageCode()) {
				case MessageEvent.RequestAPIKEY:
					//获取平台码
					GetToken_Interface getTokenInterface = theRetrofit().create(GetToken_Interface.class);
					Call<ApiTokenRespond> call = getTokenInterface.getCall(ss.getApiToken().getApiKey(), ANDROID);
					call.enqueue(new Callback<ApiTokenRespond>() {
						@Override
						public void onResponse(Call<ApiTokenRespond> call, Response<ApiTokenRespond> response) {
							try {
//							Log.i("返回信息:", String.valueOf(response.code()));
								if (response.code() == 200) {
									//获取平台token
									if (response.body().getStatus() == 0) {
										Log.i("返回信息data:", response.body().getData());
										MyApplicaiton.API_TOKEN=response.body().getData();
									} else {
										//获取失败退出系统
										Toast.makeText(getApplicationContext(), "错误信息为"
												+ response.body().getStatus() + "//" + response.body().getMsg(), Toast.LENGTH_LONG).show();
										Intent intent = new Intent(EXIT_ACTION_Application).putExtra(EXIT_Application, EXIT_Application);
										sendBroadcast(intent);
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						@Override
						public void onFailure(Call<ApiTokenRespond> call, Throwable t) {
							Toast.makeText(getApplicationContext(), "错误信息为" + t, Toast.LENGTH_SHORT).show();
//						Log.i("错误信息:",t.toString());
							Intent intent = new Intent(EXIT_ACTION_Application).putExtra(EXIT_Application, EXIT_Application);
							sendBroadcast(intent);
						}
					});

					break;
				case MessageEvent.RequestSignIn:
					//发送登陆请求

					break;
				case MessageEvent.RequestRegsiter:

					break;
				default:
					break;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	//登录请求
	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void getLoginBack(MessageLogin ss){
		if (ss.getMessageCode() == MessageEvent.RequestSignIn){
			Login_Interface getLogin = theRetrofit().create(Login_Interface.class);
//				Log.i("登录信息",ss.getLogin().getMobile()+"/***/"+ss.getLogin().getPassword());
			Call<LoginRespond> call1 = getLogin.getRespond(ss.getLogin().getMobile(), ss.getLogin().getPassword(), ANDROID);
			call1.enqueue(new Callback<LoginRespond>() {
				@Override
				public void onResponse(Call<LoginRespond> call, Response<LoginRespond> response) {
					try {
						if (response.code() == 200) {
//								Log.i("111111",""+response.body().getStatus());
							if (response.body().getStatus() == 0) {
								Login_TOKEN = response.body().getData().getToken();
								LOGIN_MARKER = true;
								SharedUtils.setLoginStatus(getApplicationContext(),true);
								//持久化存储账号密码
								if (SharedUtils.getIdentity(getApplicationContext()).length() == 0 && SharedUtils.getIDPassword(getApplicationContext()).length() == 0) {
									SharedUtils.setIdentityANDPassword(getApplicationContext(), getCacheID(), getCachePWD());
								}

								LoginRespond loginRespond = new LoginRespond(response.body().getMsg(), response.body().getStatus(), response.body().getData());
								setUserInfo(loginRespond.getData());
								Log.i("登录信息", loginRespond.getMsg() + "/***//" + loginRespond.getData().getBalance());
								EventBus.getDefault().post(new MessageLogin(MessageEvent.AnswerSignIn, loginRespond));
							} else {
								switch (response.body().getStatus()) {
								}
								Toast.makeText(getApplicationContext(),
										"登录错误代码为" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
							}
						} else Toast.makeText(getApplicationContext(),
								"服务器返回错误类型为" + response.code(), Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "错误代码为" + e, Toast.LENGTH_SHORT).show();
					}

				}

				@Override
				public void onFailure(Call<LoginRespond> call, Throwable t) {
					Log.i("错误信息:", t.toString());
					Toast.makeText(getApplicationContext(), "错误代码为" + t, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	//请求发送验证码
	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void getVerifyBack(MessageVerifyCode ss) {
		if (ss.getMessageCode() == MessageEvent.ObtainVerifyCode) {
			SendSMSVerifyCod_Interface getVerrifyCode = theRetrofit().create(SendSMSVerifyCod_Interface.class);
			Call<SendSMSVerifyCodeRespond> call = getVerrifyCode.getRespond(ss.getVerifyCode().getMobile(),ss.getVerifyCode().getUse(), ANDROID);
			call.enqueue(new Callback<SendSMSVerifyCodeRespond>() {
				@Override
				public void onResponse(Call<SendSMSVerifyCodeRespond> call, Response<SendSMSVerifyCodeRespond> response) {
					try {
						Log.i("信息", "验证码反馈");
						if (response.code() == 200) {
							if (response.body().getStatus() == 0) {
								Toast.makeText(getApplicationContext(), "验证短信已经发送，请注意查收", Toast.LENGTH_LONG).show();
							} else {
								switch (response.body().getStatus()) {
								}
								Toast.makeText(getApplicationContext(), "错误代码为" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
							}
						} else
							Toast.makeText(getApplicationContext(), "服务器返回错误类型为" + response.code(), Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "错误代码为" + e, Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				public void onFailure(Call<SendSMSVerifyCodeRespond> call, Throwable t) {
					Log.i("错误信息:", t.toString());
					Toast.makeText(getApplicationContext(), "错误代码为" + t, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	//注册请求
	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void getRegisterBack(MessageRegsiter ss) {
		if (ss.getMessageCode() == MessageEvent.RequestRegsiter) {
			Register_Interface getRegsiter = theRetrofit().create(Register_Interface.class);
			Call<RegisterRespond> call = getRegsiter.getRespond(
					ss.getRegisterRequest().getMobile(),
					ss.getRegisterRequest().getPassword(),
					ss.getRegisterRequest().getVerifyCode(),
					ss.getRegisterRequest().getPlatformCompanyCode(),
					ANDROID);

			call.enqueue(new Callback<RegisterRespond>() {
				@Override
				public void onResponse(Call<RegisterRespond> call, Response<RegisterRespond> response) {
					try {
						if (response.code() == 200) {
							if (response.body().getStatus() == 0) {
								//注册请求
								EventBus.getDefault().post(new MessageRegsiter(MessageEvent.AnswerRegsiter, response.body()));
								SharedUtils.setIdentityANDPassword(getApplicationContext(),getCacheID(),getCachePWD());
								Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
							} else {
								switch (response.body().getStatus()) {
								}
								Toast.makeText(getApplicationContext(),
										"错误代码为" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
							}
						} else
							Toast.makeText(getApplicationContext(), "服务器返回错误类型为" + response.code(), Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "错误代码为" + e, Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				public void onFailure(Call<RegisterRespond> call, Throwable t) {
					Log.i("错误信息:", t.toString());
					Toast.makeText(getApplicationContext(), "错误代码为" + t, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	//退出请求
	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void getQuitBack(MessageQuit ss){
		if (ss.getMessageCode() == MessageEvent.RequestQuit) {
			Quit_Interface getQuit = theRetrofit().create(Quit_Interface.class);
			Call<QuitRespond> call = getQuit.getResond(ANDROID);
			call.enqueue(new Callback<QuitRespond>() {
				@Override
				public void onResponse(Call<QuitRespond> call, Response<QuitRespond> response) {
					try {
						if (response.code() == 200) {
							if (response.body().getStatus() == 0) {
								//退出请求
								MyApplicaiton.clearLogin_TOKEN();
								SharedUtils.setLoginStatus(getApplicationContext(),false);
								Toast.makeText(getApplicationContext(), "退出成功", Toast.LENGTH_SHORT).show();
							} else {
								switch (response.body().getStatus()) {
								}
								Toast.makeText(getApplicationContext(),
										"错误代码为" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
							}
						} else
							Toast.makeText(getApplicationContext(), "服务器返回错误类型为" + response.code(), Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "错误代码为" + e, Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				public void onFailure(Call<QuitRespond> call, Throwable t) {
					Log.i("错误信息:", t.toString());
					Toast.makeText(getApplicationContext(), "错误代码为" + t, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	//修改用户信息
	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void getModifyUser(MessageModifyUser modifyUser){
		if (modifyUser.getMessageCode() == MessageEvent.RequestModifyUserInfo){
			ModifyUser_Interface getModify =  theRetrofit().create(ModifyUser_Interface.class);
			Call<ModifyUserINFORespond> call = getModify.getRespond(
					modifyUser.getModifyUserINFO().getToken(),
					modifyUser.getModifyUserINFO().getNickname(),
					modifyUser.getModifyUserINFO().getEmail(),
					modifyUser.getModifyUserINFO().getSex(),
					ANDROID
			);

			call.enqueue(new Callback<ModifyUserINFORespond>() {
				@Override
				public void onResponse(Call<ModifyUserINFORespond> call, Response<ModifyUserINFORespond> response) {
					try {
						if (response.code() == 200) {
							if (response.body().getStatus() == 0) {
								Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
								EventBus.getDefault().post(new MessageModifyUser(MessageEvent.AnswerModifyUserInfo,response.body()));
							} else {
								switch (response.body().getStatus()) {
								}
								Toast.makeText(getApplicationContext(),
										"错误代码为" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
							}
						} else
							Toast.makeText(getApplicationContext(), "服务器返回错误类型为" + response.code(), Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "错误代码为" + e, Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				public void onFailure(Call<ModifyUserINFORespond> call, Throwable t) {
					Log.i("错误信息:", t.toString());
					Toast.makeText(getApplicationContext(), "错误代码为" + t, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	//修改用户密码
	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void getModifyPw(MessageModifyPWD ss){
		if (ss.getMessageCode() == MessageEvent.RequestModifyPassword){
			ModifyPassword_Interface getThePW = theRetrofit().create(ModifyPassword_Interface.class);
			Call<ModifyUserPWRespond> call = getThePW.getRespond(ss.getModifyUserPWd().getToken(),
					ss.getModifyUserPWd().getPasswordOld(),ss.getModifyUserPWd().getPasswordNew(),ANDROID);
			call.enqueue(new Callback<ModifyUserPWRespond>() {
				@Override
				public void onResponse(Call<ModifyUserPWRespond> call, Response<ModifyUserPWRespond> response) {
					try {
						if (response.code() == 200) {
							if (response.body().getStatus() == 0) {
								Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();

								Log.i("信息",response.body().getStatus()+"///"+response.body().getMsg());
								EventBus.getDefault().post(new MessageModifyPWD(MessageEvent.AnswerModifyPassword,response.body()));

							} else {
								switch (response.body().getStatus()) {
								}
								Toast.makeText(getApplicationContext(),
										"错误代码为" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
							}
						} else
							Toast.makeText(getApplicationContext(), "服务器返回错误类型为" + response.code(), Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "错误代码为" + e, Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				public void onFailure(Call<ModifyUserPWRespond> call, Throwable t) {
					Log.i("错误信息:", t.toString());
					Toast.makeText(getApplicationContext(), "错误代码为" + t, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	//请求余额
	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void getBalance(MessageBalance ss){
		if (ss.getMessageCode() == MessageEvent.RequestBalance){
			Balance_Interface getBalance = theRetrofit().create(Balance_Interface.class);
			Call<BalanceRespond> call = getBalance.getCall(Login_TOKEN,ANDROID);
			call.enqueue(new Callback<BalanceRespond>() {
				@Override
				public void onResponse(Call<BalanceRespond> call, Response<BalanceRespond> response) {
					try {
						if (response.code() == 200) {
							if (response.body().getStatus() == 0) {
								Toast.makeText(getApplicationContext(), "获取余额", Toast.LENGTH_SHORT).show();
								Log.i("信息",response.body().getStatus()+"///"+response.body().getMsg());
								EventBus.getDefault().post(new MessageBalance(MessageEvent.AnswerBalance,response.body()));
							} else {
								switch (response.body().getStatus()) {
								}
								Toast.makeText(getApplicationContext(),
										"错误代码为" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
							}
						} else
							Toast.makeText(getApplicationContext(), "服务器返回错误类型为" + response.code(), Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "错误代码为" + e, Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				public void onFailure(Call<BalanceRespond> call, Throwable t) {
					Log.i("错误信息:", t.toString());
					Toast.makeText(getApplicationContext(), "错误代码为" + t, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	//忘记密码
	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void getForgetPW (MessageForget ss){
		if (ss.getMessageCode() == MessageEvent.RequestForget){
			ForgetPW_Interface getFPW = theRetrofit().create(ForgetPW_Interface.class);
			Call<ForgetPWRespond> call = getFPW.getRespond(
					ss.getForgetPW().getMobile(),
					ss.getForgetPW().getPasswordNew(),
					ss.getForgetPW().getVerifyCode(),
					ANDROID
			);
			call.enqueue(new Callback<ForgetPWRespond>() {
				@Override
				public void onResponse(Call<ForgetPWRespond> call, Response<ForgetPWRespond> response) {
					try {
						if (response.code() == 200) {
							if (response.body().getStatus() == 0) {
								Toast.makeText(getApplicationContext(), "修改密码成功", Toast.LENGTH_SHORT).show();
								Log.i("信息",response.body().getStatus()+"///"+response.body().getMsg());
								EventBus.getDefault().post(new MessageForget(MessageEvent.AnswerForget,response.body()));
							} else {
								switch (response.body().getStatus()) {
								}
								Toast.makeText(getApplicationContext(),
										"错误代码为" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
							}
						} else
							Toast.makeText(getApplicationContext(), "服务器返回错误类型为" + response.code(), Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "错误代码为" + e, Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				public void onFailure(Call<ForgetPWRespond> call, Throwable t) {
					Log.i("错误信息:", t.toString());
					Toast.makeText(getApplicationContext(), "错误代码为" + t, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	//请求电站list
	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void getStationList(MessageStationList ss){
		if (ss.getMessageCode() == MessageEvent.RequestStation){
			Log.i("收到电站请求信息", "1233455");
			StationList_interface getStation = theRetrofit().create(StationList_interface.class);
			Call<StationList> call = getStation.getCall(ANDROID);
			call.enqueue(new Callback<StationList>() {
				@Override
				public void onResponse(Call<StationList> call, Response<StationList> response) {
					try {
						if (response.code() == 200) {
							if (response.body().getStatus() == 0) {
								Log.i("电站信息：",response.body().getStatus()+"///"+response.body().getMsg());
								EventBus.getDefault().post(new MessageStationList(response.body()));
							} else {
								switch (response.body().getStatus()) {
								}
								Toast.makeText(getApplicationContext(), "错误代码为" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
							}
						} else
							Toast.makeText(getApplicationContext(), "服务器返回错误类型为" + response.code(), Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "错误代码为" + e, Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				public void onFailure(Call<StationList> call, Throwable t) {
					Log.i("错误信息:", t.toString());
					Toast.makeText(getApplicationContext(), "错误代码为" + t, Toast.LENGTH_SHORT).show();
				}
			});
		}

	}



	//开源HTTP网络框架初始化
	public Retrofit theRetrofit() {
		Retrofit retrofit = new Retrofit.Builder().
				baseUrl(BASE_URL).//添加URL抬头
				client(OkHttpC()).//添加OKHTTP框架用于拦截Cookies
				addConverterFactory(GsonConverterFactory.create()).//添加Gson解析框架
				build();
		return retrofit;
	}

	/**
	 * 拦截器用于获取cookie
	 */
	public OkHttpClient OkHttpC() {

		OkHttpClient.Builder builder = new OkHttpClient.Builder().cookieJar(new CookieManger(this));

		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.cookieJar(new CookieManger(this))
				.connectTimeout(5000, TimeUnit.SECONDS)
				.build();
		return okHttpClient;
	}


}
