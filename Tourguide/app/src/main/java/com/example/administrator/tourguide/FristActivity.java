package com.example.administrator.tourguide;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class FristActivity extends AppCompatActivity {
	//存放Fragment
	private ArrayList<Fragment> fragmentArrayList;
	//管理Fragment
	private FragmentManager fragmentManager;
	//页面管理器
	private ViewPager mViewPager;
	private TabLayout mTabLayout;
	public Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        InitFragment();
		InitViewPager();
	}

	private void InitViewPager() {
		mViewPager = (ViewPager) findViewById(R.id.myPager);
		mViewPager.setAdapter(new MyFragmentPagerAdapter(fragmentManager,fragmentArrayList));
		mViewPager.setCurrentItem(0);
		mTabLayout = (TabLayout)findViewById(R.id.myTablayout);
		mTabLayout.setupWithViewPager(mViewPager);

	}

    /**
     * 初始化Fragment，并添加到ArrayList中
     */
    private void InitFragment(){
        fragmentArrayList = new ArrayList<Fragment>();
        fragmentArrayList.add(new HistoricalSitesFragment());
        fragmentArrayList.add(new ParkFragment());
        fragmentArrayList.add(new RestaurantFragment());
        fragmentArrayList.add(new HotelsFragment());
        fragmentManager = getSupportFragmentManager();
    }
}
