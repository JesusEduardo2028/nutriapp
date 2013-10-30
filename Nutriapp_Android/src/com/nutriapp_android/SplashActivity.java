package com.nutriapp_android;

import com.nutriapp_android.internal_storage.SharedPreferencesHelper;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivityManager.getActiveNetworkInfo();
		
		if (info == null || !info.isConnected() || !info.isAvailable()) {
			Toast.makeText(this,"Network is not there? Please check...",Toast.LENGTH_LONG).show();
			finish();
		} else {
			Handler handler = new Handler();
			handler.postDelayed(getMyRunnable(), 4000);
		}
	}
	
	private Runnable getMyRunnable() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				SharedPreferencesHelper session = new SharedPreferencesHelper(SplashActivity.this);
				Intent activityIntent = null;
				
				if(session.isExistKeyShared("configuracion_inicial") && session.getStringShared("configuracion_inicial").equals("true")) {
					if(session.getStringShared("configuracion_tipo").equals("dieta_personalizada")) {
						if(session.isExistKeyShared("user_id")) {
							//activityIntent = new Intent(SplashActivity.this, MainActivity.class);
						} else {
							activityIntent = new Intent(SplashActivity.this, LoginActivity.class);
						}
					} else {
						//activityIntent = new Intent(SplashActivity.this, SearchRecipesActivity.class);
					}
				} else {
					activityIntent = new Intent(SplashActivity.this, InitialMenuActivity.class); //LoginActivity
				}
				
				startActivity(activityIntent);
				finish();
			}
		};
		
		return runnable;
	}

}
