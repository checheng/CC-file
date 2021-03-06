package com.wuxindianqi.administrator.chargingstationapp.view.fragment;


import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuxindianqi.administrator.chargingstationapp.R;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * A simple {@link Fragment} subclass.
 */
public class PermissionFragment extends Fragment implements EasyPermissions.PermissionCallbacks{


	//需要申请的权限
	private static String[] permissions = new String[]{
			Manifest.permission.WRITE_EXTERNAL_STORAGE,
			Manifest.permission.READ_EXTERNAL_STORAGE,
			Manifest.permission.CAMERA,
			Manifest.permission.ACCESS_FINE_LOCATION
	};


	public PermissionFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_base, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (EasyPermissions.hasPermissions(getActivity(), permissions)) {
			Log.i("Dynamic Permission", "已获取权限");
		} else {
			EasyPermissions.requestPermissions(getActivity(), "必要权限", 0, permissions);
		}

	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,getActivity());
	}

	@Override
	public void onPermissionsGranted(int requestCode, List<String> perms) {
		Log.i("12345678888", "获取成功的权限" + perms);
	}

	@Override
	public void onPermissionsDenied(int requestCode, List<String> perms) {
		Log.i("12345678888", "获取失败的权限" + perms);
	}

}
