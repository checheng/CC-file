package com.wuxindianqi.administrator.chargingstationapp.view.popupwindow;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.Switch;

import com.wuxindianqi.administrator.chargingstationapp.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/11/30.
 */

public class DialogPopup extends BasePopupWindow implements View.OnClickListener {

	private Switch freeSW,DCSW,ACSW;
	private Button allBTN;

	public DialogPopup(Context context) {
		super(context);
		initView();
	}

	private void initView(){
		freeSW = (Switch)findViewById(R.id.free_switch_xianshi);
		DCSW= (Switch)findViewById(R.id.DC_switch_xianshi);
		ACSW= (Switch)findViewById(R.id.AC_switch_xianshi);
		allBTN = (Button)findViewById(R.id.all_button_xianshi);
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	protected Animation initShowAnimation() {
		AnimationSet set=new AnimationSet(false);
		Animation shakeAnima=new RotateAnimation(0,0,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		shakeAnima.setInterpolator(new CycleInterpolator(5));
		shakeAnima.setDuration(400);
		set.addAnimation(getDefaultAlphaAnimation());
		set.addAnimation(shakeAnima);
		return set;
	}

	@Override
	public View getClickToDismissView() {
		return getPopupWindowView();
	}

	@Override
	public View onCreatePopupView() {
		return createPopupById(R.layout.popupwindow_xianshi);
	}

	@Override
	public View initAnimaView() {
		return findViewById(R.id.pop_window_xianshi);
	}
}
