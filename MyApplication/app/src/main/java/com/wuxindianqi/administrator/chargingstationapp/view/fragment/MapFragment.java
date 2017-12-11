package com.wuxindianqi.administrator.chargingstationapp.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.wuxindianqi.administrator.chargingstationapp.R;
import com.wuxindianqi.administrator.chargingstationapp.view.popupwindow.DialogPopup;

/**
 * 用于继承的mapfragment
 */
public class MapFragment extends BaseMapFragment {

	private Button ShowBTN,CollectBTN,ListBTN;
	private EditText UserInputEdit;

		@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_map, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		theinitView();
		initOnClick();
	}

	public void theinitView() {
		ShowBTN = (Button) getView().findViewById(R.id.show_button);
		CollectBTN = (Button) getView().findViewById(R.id.add_button);
		ListBTN = (Button) getView().findViewById(R.id.list_button);
		UserInputEdit = (EditText) getView().findViewById(R.id.user_input);
	}

	public void initOnClick(){
		ShowBTN.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogPopup dialogPopup = new DialogPopup(getContext());

				dialogPopup.showPopupWindow();
			}
		});
		CollectBTN.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
		ListBTN.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}

}
