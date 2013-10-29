package com.nutriapp_android;

import java.util.List;
import java.util.Vector;

import com.nutriapp_android.adapter.PagerAdapter;
import com.nutriapp_android.internal_storage.SharedPreferencesHelper;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ConfigurationProfileActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {
	private SharedPreferencesHelper sharePreference;
	private PagerAdapter mPagerAdapter;
	private MenuItem itemPerfil, itemAtras, itemSiguiente;
	
	private ViewPager vPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configuration);
		
		ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle(R.string.titulo_actionbar);
        
        sharePreference = new SharedPreferencesHelper(this);
		initialisePaging();
	}
	
    private void initialisePaging() {
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, UserProfilePage.class.getName()));
        fragments.add(Fragment.instantiate(this, ActivityLevelPage.class.getName()));
        
        mPagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);

        vPager = (ViewPager)super.findViewById(R.id.viewPagesConfig);
        vPager.setAdapter(this.mPagerAdapter);
        vPager.setOnPageChangeListener(this);
    }
    
	@Override
	public void onPageScrollStateChanged(int arg0) {  }
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {  }

	@Override
	public void onPageSelected(int pos) {
		Toast.makeText(this, "view "+pos, Toast.LENGTH_SHORT).show();
		if(pos == 1) {
			itemAtras.setVisible(true);
			itemPerfil.setVisible(true);
			itemSiguiente.setVisible(false);
		} else {
			itemPerfil.setVisible(false);
			itemAtras.setVisible(false);
			itemSiguiente.setVisible(true);
		}
	}
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.configuration, menu);
		itemPerfil = menu.findItem(R.id.itemGuardarPerfil);
		itemAtras = menu.findItem(R.id.itemAtras);
		itemSiguiente = menu.findItem(R.id.itemSiguiente);
		
		if(this.vPager.getCurrentItem() == 0) {
			itemAtras.setVisible(false);
			itemPerfil.setVisible(false);
		}
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.itemGuardarPerfil:
				guardarPerfil();
				/*Intent registerIntent = new Intent(this, ConfigurationProfileActivity.class);
		    	startActivity(registerIntent);
		    	finish();*/
				break;
				
			case R.id.itemAtras:
				if(this.vPager.getCurrentItem() == 1) {
					this.vPager.setCurrentItem(0);
					itemPerfil.setVisible(false);
					itemAtras.setVisible(false);
					itemSiguiente.setVisible(true);
				}
				return true;
				
			case R.id.itemSiguiente:
				if(this.vPager.getCurrentItem() == 0) {
					this.vPager.setCurrentItem(1);
					itemAtras.setVisible(true);
					itemPerfil.setVisible(true);
					itemSiguiente.setVisible(false);
				}
				return true;
		}
		
		return false;
	}
	
	private void guardarPerfil() {
		sharePreference.writeStringShared("peso_actual", UserProfilePage.peso_actual.getText().toString());
		sharePreference.writeStringShared("peso_meta", UserProfilePage.peso_meta.getText().toString());
		sharePreference.writeStringShared("estatura", UserProfilePage.estatura.getText().toString());
		sharePreference.writeStringShared("fecha_nacimiento", UserProfilePage.fecha_nacimiento.getText().toString());
		
		if(UserProfilePage.sexo.isChecked()) {
			sharePreference.writeStringShared("sexo", "M");
		} else {
			sharePreference.writeStringShared("sexo", "H");
		}
		
		switch(ActivityLevelPage.actividades.getCheckedRadioButtonId()) {
			case R.id.radioSendentario:
				sharePreference.writeStringShared("actividad", "sedentario");
				break;
			case R.id.radioBaja:
				sharePreference.writeStringShared("actividad", "bajo");
				break;
			case R.id.radioActivo:
				sharePreference.writeStringShared("actividad", "activo");
				break;
			case R.id.radioMuyActivo:
				sharePreference.writeStringShared("actividad", "muy_activo");
				break;
		}
		
		switch(ActivityLevelPage.objetivos.getCheckedRadioButtonId()) {
			case R.id.radioPerderLentamente:
				sharePreference.writeStringShared("objetivo", "perder_lento");
				break;
			case R.id.radioPerderRapidamente:
				sharePreference.writeStringShared("objetivo", "perder_rapido");
				break;
			case R.id.radioGanarLentamente:
				sharePreference.writeStringShared("objetivo", "ganar_lento");
				break;
			case R.id.radioGanarRapidamente:
				sharePreference.writeStringShared("objetivo", "ganar_rapido");
				break;
			case R.id.radioMantener:
				sharePreference.writeStringShared("objetivo", "mantener_peso");
				break;
		}
	}

}
