package com.nutriapp_android.internal_storage;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
	SharedPreferences settings;
	Context context;

	public SharedPreferencesHelper(Context context) {
		this.context = context;
		settings = context.getSharedPreferences("nutriapp_perfil", Context.MODE_PRIVATE);
	}
	
	public boolean isExistKeyShared(String val) {
		/* retorna F si no existe el valor*/
		String value = settings.getString(val, null);
		if (value == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public String getStringShared(String val) {
		/* retorna el contenido del parametro val sacado del SharedPreferences */
		String valor = settings.getString(val, null);
		return valor;
	}
	
	public void writeStringShared(String clave, String valor) {
		/* almacenar un valor string en el SharedPreferences */
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(clave, valor);
		editor.commit();
	}
	
	/*public void removeValueShared(String clave) {
		// eliminar una clave del SharedPreferences
		SharedPreferences.Editor editor = settings.edit();
		editor.remove(clave);
		editor.commit();
	}*/
	
	public void removeAllShared() {
		/* eliminar una clave del SharedPreferences */
		SharedPreferences.Editor editor = settings.edit();
		editor.clear();
		editor.commit();
	}

}
