package com.nutriapp_android;

import com.nutriapp_android.internal_storage.SharedPreferencesHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class InitialMenuActivity extends Activity {
	CheckBox config_inicial;
	
	SharedPreferencesHelper shared_preference;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initial_menu);
		
		config_inicial = (CheckBox) findViewById(R.id.checkBoxConfigInicial);
		shared_preference = new SharedPreferencesHelper(this);
	}
	
	public void dietaPersonalizada(View v) {
		if(config_inicial.isChecked()) {
			shared_preference.writeStringShared("configuracion_inicial", "true");
			shared_preference.writeStringShared("configuracion_tipo", "dieta_personalizada");
		}
		
		Intent registerIntent = new Intent(this, LoginActivity.class);
    	startActivity(registerIntent);
    	finish();
	}
	
	public void botonRecetas(View v) {		
		if(config_inicial.isChecked()) {
			shared_preference.writeStringShared("configuracion_inicial", "true");
			shared_preference.writeStringShared("configuracion_tipo", "buscar_receta");
		}
	}

	
}
