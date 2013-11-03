package com.nutriapp_android;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;

import com.nutriapp_android.frgments.InfoRecetasFragment;
import com.nutriapp_android.frgments.MenuDelDiaFragment;
import com.nutriapp_android.frgments.RecetaListItemFragment.InterfazSeleccionReceta;

public class MainActivity extends BaseActivity implements InterfazSeleccionReceta {
	//private boolean RECETAS_MENU = false; 
	
	private Fragment contenido;
	private ProgressDialog progressDialog;
	private Fragment fragment;
	public static MainActivity a;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle(R.string.titulo_actionbar);
		setSlidingActionBarEnabled(true);
		
		a = this;
		if (savedInstanceState != null) {
			contenido = getSupportFragmentManager().getFragment(
					savedInstanceState, "contenido");
		}
		if (contenido == null) {
			contenido = new MenuDelDiaFragment();
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
		fragment = nuevoContenido;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, nuevoContenido).commit();
		getSlidingMenu().showContent();
		if (nuevoContenido instanceof MenuDelDiaFragment) {
			cargarPages();
		}
		
	}

	private void cargarPages() {
		// TODO Auto-generated method stub
		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(false);
		progressDialog.setMessage("Actualizando Recetas");
		progressDialog.show();
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				// acciones que se ejecutan tras los milisegundos
				progressDialog.dismiss();
				MenuDelDiaFragment menu = (MenuDelDiaFragment) fragment;
				menu.initialisePaging();
			}
		}, 1000);
	}

	@Override
	public void recetaSeleccionada(Integer comida) {
		// TODO Auto-generated method stub
		
		fragment = new InfoRecetasFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("comida", comida);
		fragment.setArguments(bundle);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		fragment = new MenuDelDiaFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
		cargarPages();
	}
	
	
}