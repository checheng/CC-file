package com.wuxindianqi.administrator.chargingstationapp.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.wuxindianqi.administrator.chargingstationapp.R;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageEvent;
import com.wuxindianqi.administrator.chargingstationapp.view.activity.LoginActivity;
import com.wuxindianqi.administrator.chargingstationapp.view.activity.RegisterActivity;
import com.wuxindianqi.administrator.chargingstationapp.view.activity.WuxinInfoActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserLogoutFragment extends Fragment {


	private Button LoginBTN,RegisterBTN,CheckUpdataBTN,VersionBTN,GuanyuBtn;


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
	}

	@Subscribe(threadMode = ThreadMode.BACKGROUND)
	public void miaomiaomiao(MessageEvent ssssssss){

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_user_logout, container, false);
	}


	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		initOnClick();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	public void initView() {
		LoginBTN = (Button) getView().findViewById(R.id.login_user_button);
		RegisterBTN = (Button) getView().findViewById(R.id.register_user_button);
		CheckUpdataBTN = (Button) getView().findViewById(R.id.enter_check_updata_button);
		VersionBTN = (Button) getView().findViewById(R.id.enter_version_info_button);
		GuanyuBtn = (Button) getView().findViewById(R.id.enter_guanyu_info_button);
	}

	public void initOnClick(){
		LoginBTN.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*toUserLoginFragment();
				MyApplicaiton.LOGIN_MARKER = true;*/
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
		});
		RegisterBTN.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), RegisterActivity.class));
			}
		});
		CheckUpdataBTN.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(),"当前是最新版本。",Toast.LENGTH_SHORT).show();
			}
		});
		VersionBTN.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(),"暂时无版本信息。",Toast.LENGTH_SHORT).show();
			}
		});
		GuanyuBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), WuxinInfoActivity.class));
			}
		});

	}

	//同一个ACTIVTY内跳转fragment
	private void toUserLoginFragment(){
		Toast.makeText(getActivity(),"点击",Toast.LENGTH_SHORT).show();
		FragmentManager fm = getActivity().getSupportFragmentManager();
		UserLoginFragment userLoginFragment = new UserLoginFragment();
		FragmentTransaction ft  = fm.beginTransaction();
		ft.replace(R.id.fl_main,userLoginFragment);
		ft.commit();
	}
}
