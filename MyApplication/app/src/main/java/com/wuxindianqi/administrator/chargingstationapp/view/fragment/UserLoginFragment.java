package com.wuxindianqi.administrator.chargingstationapp.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.wuxindianqi.administrator.chargingstationapp.R;
import com.wuxindianqi.administrator.chargingstationapp.adapter.LgoinRecyclerViewAdapter;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageEvent;
import com.wuxindianqi.administrator.chargingstationapp.bean.EventBus.MessageQuit;
import com.wuxindianqi.administrator.chargingstationapp.bean.RecyclerViewItem.LoginViewItem;
import com.wuxindianqi.administrator.chargingstationapp.view.activity.BalanceActivity;
import com.wuxindianqi.administrator.chargingstationapp.view.activity.ConsumptionRecordActivity;
import com.wuxindianqi.administrator.chargingstationapp.view.activity.GuanyuActivity;
import com.wuxindianqi.administrator.chargingstationapp.view.activity.UserInfoActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static com.wuxindianqi.administrator.chargingstationapp.MyApplicaiton.clearLogin_TOKEN;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserLoginFragment extends Fragment {

	private Button QuitUser;
	private RecyclerView mRecyclerView;
	private List<LoginViewItem> mItems = new ArrayList<LoginViewItem>();


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_user_login, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	public void initView (){
		QuitUser = (Button)getView().findViewById(R.id.quit_user);
		QuitUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EventBus.getDefault().post(new MessageQuit(MessageEvent.RequestQuit));
				toUserLogoutFragment();
			}
		});
		mRecyclerView = (RecyclerView)getView().findViewById(R.id.my_recyclerview);
		setTitle();
		LgoinRecyclerViewAdapter lgoinRecyclerViewAdapter = new LgoinRecyclerViewAdapter(mItems);
		lgoinRecyclerViewAdapter.setOnItemClickLitener(new LgoinRecyclerViewAdapter.OnItemClickLitener() {
			@Override
			public void onItemClick(View view, int position) {
//				Toast.makeText(getActivity(), position + " click",
//						Toast.LENGTH_SHORT).show();
			switch (position) {
					case 0://个人设置
						Intent intent = new Intent(getActivity(), UserInfoActivity.class);
						startActivity(intent);
						break;
					case 1://我的余额
						startActivity(new Intent(getActivity(), BalanceActivity.class));
						break;
					case 2://消费记录
						startActivity(new Intent(getActivity(), ConsumptionRecordActivity.class));
						break;
					case 3://申请建桩
						Toast.makeText(getActivity(),"暂未开通建桩功能。",Toast.LENGTH_SHORT).show();
						break;
					case 4://建桩共享
						Toast.makeText(getActivity(),"暂未开通建桩功能。",Toast.LENGTH_SHORT).show();
						break;
					case 5://关于
						startActivity(new Intent(getActivity(), GuanyuActivity.class));
						break;
					default:
						break;
				}
			}
		});
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
		mRecyclerView.setAdapter(lgoinRecyclerViewAdapter);
	}

	public void setTitle(){
		LoginViewItem[]itemList = new LoginViewItem[6];
	    itemList[0] = new LoginViewItem("个人设置",0);
		itemList[1] = new LoginViewItem("我的余额",0);
		itemList[2] = new LoginViewItem("消费记录",0);
		itemList[3] = new LoginViewItem("申请建桩",0);
		itemList[4] = new LoginViewItem("建桩共享",0);
		itemList[5] = new LoginViewItem("关于",0);
		for (int i = 0;i<6;i++){
			mItems.add(itemList[i]);
		}
	}

	private void toUserLogoutFragment(){
		Toast.makeText(getActivity(),"退出登录",Toast.LENGTH_SHORT).show();
		clearLogin_TOKEN();
		FragmentManager fm = getActivity().getSupportFragmentManager();
		UserLogoutFragment userLoginFragment = new UserLogoutFragment();
		FragmentTransaction ft  = fm.beginTransaction();
		ft.replace(R.id.fl_main,userLoginFragment);
		ft.commit();
	}
}
