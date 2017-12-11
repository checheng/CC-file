package com.wuxindianqi.administrator.chargingstationapp.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.wuxindianqi.administrator.chargingstationapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChargingFragment extends PermissionFragment {

	private Button Sacn;
	/**
	 * 扫描跳转Activity RequestCode
	 */
	public static final int REQUEST_CODE = 1211;
	/**
	 * 选择系统图片Request Code
	 */
	public static final int REQUEST_IMAGE = 1412;

	public ChargingFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_charging, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();

	}

	private void initView() {
		Sacn  = (Button)getView().findViewById(R.id.scan);
		Sacn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("启动相机","dwafdaefefeasf");
				Intent intent  = new Intent(getActivity(), CaptureActivity.class);
				startActivityForResult(intent,REQUEST_CODE);
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE) {
			if (data != null) {
				Bundle bundle = data.getExtras();
				if (bundle == null) return;
				if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
					String result = bundle.getString(CodeUtils.RESULT_STRING);
					Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
				} else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
					Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
				}
			}
		}
	}
}
