package com.example.administrator.tourguide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/25.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<Fragment> fragmentsList;
	private String[] mTitles = new String[]{"名声", "公园","餐馆","旅店"};

	public MyFragmentPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragmentsList) {
		super(fm);
		this.fragmentsList = fragmentsList;
	}

	@Override
	public Fragment getItem(int position) {
		return fragmentsList.get(position);
	}

	@Override
	public int getCount() {
		return fragmentsList.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mTitles[position];
	}
}
