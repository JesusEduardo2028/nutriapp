package com.nutriapp_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
        tabs.setup();
        
        TabHost.TabSpec spec = tabs.newTabSpec("log_in");
        spec.setContent(R.id.tab1).setIndicator("LOGIN");
        tabs.addTab(spec);
        
        spec=tabs.newTabSpec("register");
        spec.setContent(R.id.tab2).setIndicator("REGISTER");
        tabs.addTab(spec);
        
        tabs.setCurrentTab(0);
        
        TextView labelForgot = (TextView) findViewById(R.id.textForgot);
        labelForgot.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(LoginActivity.this, "forgot password", Toast.LENGTH_SHORT).show();
				
			}
		});
	}
	
	public void nutriappLogin(View v) {
		//EditText inputUserName = (EditText) findViewById(R.id.inputUserRegister);
		
		Intent registerIntent = new Intent(this, ConfigurationProfileActivity.class);
    	startActivity(registerIntent);
    	finish();
	}
	
	public void nutriappRegister(View v) {
		//EditText inputUserName = (EditText) findViewById(R.id.inputUserRegister);
		
		Intent registerIntent = new Intent(this, ConfigurationProfileActivity.class);
    	startActivity(registerIntent);
    	finish();
	}

}
