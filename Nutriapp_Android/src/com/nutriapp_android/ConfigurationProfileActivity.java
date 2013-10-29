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

public class ConfigurationProfileActivity extends FragmentActivity {
	private SharedPreferencesHelper sharePreference;
	private PagerAdapter mPagerAdapter;
	private MenuItem itemAtras, itemSiguiente;
	
	ViewPager vPager;
	
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
	
	/** Initialise the fragments to be paged */
    private void initialisePaging() {
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, UserProfilePage.class.getName()));
        fragments.add(Fragment.instantiate(this, ActivityLevelPage.class.getName()));
        fragments.add(Fragment.instantiate(this, GoalsLevelPage.class.getName()));
        fragments.add(Fragment.instantiate(this, HealthLevelPage.class.getName()));
        
        mPagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);

        vPager = (ViewPager)super.findViewById(R.id.viewPagesConfig);
        vPager.setAdapter(this.mPagerAdapter);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.configuration, menu);
		itemAtras = menu.findItem(R.id.itemAtras);
		itemSiguiente = menu.findItem(R.id.itemSiguiente);
		
		if(this.vPager.getCurrentItem() == 0) {
			itemAtras.setVisible(false);
		}
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.itemAtras:
				guardarPerfilTemp1();
				return true;
			case R.id.itemSiguiente:
				guardarPerfilTemp2();
				return true;
		}
		
		return false;
	}
	
	private void guardarPerfilTemp1() {
		Toast.makeText(this, "value peso: "+(this.vPager.getCurrentItem()-1), Toast.LENGTH_SHORT).show();
		switch(this.vPager.getCurrentItem()) {
			case 0:
				/*sharePreference.writeStringShared("peso_actual", UserProfilePage.peso_actual.getText().toString());
				sharePreference.writeStringShared("peso_meta", UserProfilePage.peso_meta.getText().toString());
				sharePreference.writeStringShared("estatura", UserProfilePage.estatura.getText().toString());
				sharePreference.writeStringShared("fecha_nacimiento", UserProfilePage.fecha_nacimiento.getText().toString());
				if(UserProfilePage.sexo.isChecked()) {
					sharePreference.writeStringShared("sexo", "M");
				} else {
					sharePreference.writeStringShared("sexo", "H");
				}*/
				break;
			case 1:
				
				this.vPager.setCurrentItem(0);
				itemAtras.setVisible(false);
				break;
			case 2:
				
				this.vPager.setCurrentItem(1);
				break;
			case 3:
				
				this.vPager.setCurrentItem(2);
				itemSiguiente.setVisible(true);
				break;
		}
		//Toast.makeText(this, "value peso: "+UserProfilePage.peso_actual.getText().toString(), Toast.LENGTH_SHORT).show();
	}
	
	private void guardarPerfilTemp2() {
		Toast.makeText(this, "value peso: "+(this.vPager.getCurrentItem()+1), Toast.LENGTH_SHORT).show();
		switch(this.vPager.getCurrentItem()) {
			case 0:
				/*sharePreference.writeStringShared("peso_actual", UserProfilePage.peso_actual.getText().toString());
				sharePreference.writeStringShared("peso_meta", UserProfilePage.peso_meta.getText().toString());
				sharePreference.writeStringShared("estatura", UserProfilePage.estatura.getText().toString());
				sharePreference.writeStringShared("fecha_nacimiento", UserProfilePage.fecha_nacimiento.getText().toString());
				if(UserProfilePage.sexo.isChecked()) {
					sharePreference.writeStringShared("sexo", "M");
				} else {
					sharePreference.writeStringShared("sexo", "H");
				}*/
				
				this.vPager.setCurrentItem(1);
				itemAtras.setVisible(true);
				break;
			case 1:
				
				this.vPager.setCurrentItem(2);
				break;
			case 2:
				
				this.vPager.setCurrentItem(3);
				itemSiguiente.setVisible(false);
				break;
			case 3:
				/*sharePreference.writeStringShared("peso_actual", UserProfilePage.peso_actual.getText().toString());
				sharePreference.writeStringShared("peso_meta", UserProfilePage.peso_meta.getText().toString());
				sharePreference.writeStringShared("estatura", UserProfilePage.estatura.getText().toString());
				sharePreference.writeStringShared("fecha_nacimiento", UserProfilePage.fecha_nacimiento.getText().toString());
				if(UserProfilePage.sexo.isChecked()) {
					sharePreference.writeStringShared("sexo", "M");
				} else {
					sharePreference.writeStringShared("sexo", "H");
				}*/
				break;
		}
	}

}
