package com.nutriapp_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.nutriapp_android.frgments.ProfileFragment;

public class MainActivity extends BaseActivity {

	private Fragment contenido;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setSlidingActionBarEnabled(true);

		if (savedInstanceState != null) {
			contenido = getSupportFragmentManager().getFragment(
					savedInstanceState, "contenido");
		}
		if (contenido == null) {
			contenido = new ProfileFragment();
		}
		
		setContentView(R.layout.layout_activity_main);
		
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, contenido).commit();

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager()
				.putFragment(outState, "mContent", contenido);
	}

	public void switchContent(Fragment nuevoContenido) {
		// TODO Auto-generated method stub

		contenido = nuevoContenido;
		Bundle args = new Bundle();
		args.putString("tag_tab", "listas");
		contenido.setArguments(args);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, nuevoContenido).commit();
		getSlidingMenu().showContent();

	}

}
