package com.nutriapp_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.nutriapp_android.frgments.MenuSlideFragment;

public class BaseActivity extends SlidingFragmentActivity {

	protected Fragment mFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// Colocamos lo que va atras del slide
		setBehindContentView(R.layout.menu_frame);

		if (savedInstanceState == null) {
			FragmentTransaction t = this.getSupportFragmentManager()
					.beginTransaction();
			mFragment = new MenuSlideFragment();
			t.replace(R.id.menu_frame, mFragment);
			t.commit();
		} else {
			mFragment = (Fragment) this.getSupportFragmentManager()
					.findFragmentById(R.id.menu_frame);
		}

		// personalizamos nuestro SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidth(5);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffset(70);
		sm.setFadeDegree(0.35f);

		// Se selecciona el modo de deslizar el menu
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

}