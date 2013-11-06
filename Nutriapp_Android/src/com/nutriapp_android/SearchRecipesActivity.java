package com.nutriapp_android;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;

import com.nutriapp_android.R;
import com.nutriapp_android.adapter.PagerAdapter;
import com.nutriapp_android.frgments.FavoriteRecipesFragment;
import com.nutriapp_android.frgments.SearchRecipesFragment;
import com.nutriapp_android.object.RecetaObject;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SearchRecipesActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {
	private boolean RECETA_ALMACENADA = false;
	
	public static int TIPO_FILTRO;
	public static ArrayList<RecetaObject> ARRAY_RECETAS;
	
	private MenuItem buscar_receta, itemAtras, itemSiguiente;
	private PagerAdapter mPagerAdapter;
	private ViewPager vPager;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe);
        
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle(R.string.titulo_actionbar);
        
        Bundle bundleFiltro = this.getIntent().getExtras();
        if(bundleFiltro.getString("filtro_recetas") != null) {
        	Toast.makeText(this, "filter: "+bundleFiltro.getString("filtro_recetas"), Toast.LENGTH_SHORT).show();
            JSONArray filter;
            try {
            	filter = new JSONArray(bundleFiltro.getString("filtro_recetas"));
            	if(filter.length() == 1) {
            		TIPO_FILTRO = Integer.parseInt(filter.getString(0));
            	} else {
            		refrescarMultipleRecetas(filter);
            	}
    		} catch (JSONException e) {
    			e.printStackTrace();
    		}
        } else {
        	TIPO_FILTRO = bundleFiltro.getInt("filtro_preview");
        	RECETA_ALMACENADA = bundleFiltro.getBoolean("receta_almacenda");
        }
        
        initialisePaging();
	}
	
	private void initialisePaging() {
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, SearchRecipesFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, FavoriteRecipesFragment.class.getName()));
        
        mPagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);
        
        vPager = (ViewPager) super.findViewById(R.id.viewPagesSR);
        vPager.setAdapter(this.mPagerAdapter);
        vPager.setOnPageChangeListener(this);
    }
	
	@Override
	public void onPageScrollStateChanged(int arg0) {  }
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {  }
	
	@Override
	public void onPageSelected(int pos) {
		if(pos == 1) {
			itemAtras.setVisible(true);
			itemSiguiente.setVisible(false);
		} else {
			itemAtras.setVisible(false);
			itemSiguiente.setVisible(true);
		}
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_recipes, menu);
        buscar_receta = menu.findItem(R.id.itemBuscarReceta);
        itemAtras = menu.findItem(R.id.itemAtras);
		itemSiguiente = menu.findItem(R.id.itemSiguiente);
		
		if(this.vPager.getCurrentItem() == 0) {
			itemAtras.setVisible(false);
		}
		
		if(RECETA_ALMACENADA) {
        	this.vPager.setCurrentItem(1);
        }
        
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
	        case R.id.itemBuscarReceta:
	        	expandedSearchView();
	        return true;
	        
	        case R.id.itemAtras:
				if(this.vPager.getCurrentItem() == 1) {
					this.vPager.setCurrentItem(0);
					itemAtras.setVisible(false);
					itemSiguiente.setVisible(true);
				}
			return true;
			
			case R.id.itemSiguiente:
				if(this.vPager.getCurrentItem() == 0) {
					this.vPager.setCurrentItem(1);
					itemAtras.setVisible(true);
					itemSiguiente.setVisible(false);
				}
			return true;
	        
	        default:
	            return super.onOptionsItemSelected(item);
        }
    }
    
    
	public void expandedSearchView() {
    	buscar_receta.setActionView(R.layout.menu_search_recipe);
    	View viewExpand = (View) buscar_receta.getActionView();
    	
    	ArrayAdapter<?> spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.array_busqueda,android.R.layout.simple_spinner_item);
    	spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	
        final Spinner tipo_busqueda = (Spinner) viewExpand.findViewById(R.id.searchInputItem);
        tipo_busqueda.setAdapter(spinnerAdapter);
        tipo_busqueda.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
            	((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
            	TIPO_FILTRO = pos+1;
            	if(pos == 5) {
            		TIPO_FILTRO = 0;
            	}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {  }
        });
        
        final ImageView imgSearch = (ImageView) viewExpand.findViewById(R.id.searchBtnItem);
        imgSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	            Toast.makeText(SearchRecipesActivity.this, "buscar receta "+TIPO_FILTRO, Toast.LENGTH_SHORT).show();
				buscar_receta.setActionView(null);
				refrescarListaRecetas(TIPO_FILTRO);
			}
		});
    }
    
    private void refrescarListaRecetas(int tipo) {
    	Toast.makeText(this, "tipo receta "+tipo, Toast.LENGTH_SHORT).show();
    	
    	/*SearchRecipesFragment fragment = new SearchRecipesFragment();//(SearchRecipesFragment) getSupportFragmentManager().findFragmentById(R.id.relativeBusqueda);
    	if(fragment != null) {
    		Toast.makeText(this, "fragment desplegada", Toast.LENGTH_SHORT).show();
    		fragment.refrescarBusqueda(tipo);
    	} else {
    		Toast.makeText(this, "fragment no desplegadp", Toast.LENGTH_SHORT).show();
    	}*/
    	
    	/*Bundle bundleVideo = new Bundle();
		bundleVideo.putInt("videoId", tipo);
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		SearchRecipesFragment frag = new SearchRecipesFragment();
    	frag.setArguments(bundleVideo);
    	ft.replace(R.id.relativeBusqueda, frag).commit();*/
    	
    	/*ViewGroup view = (ViewGroup) findViewById(R.id.relativeBusqueda);
		view.removeAllViews();*/
    	
    	/*Bundle bundleVideo = new Bundle();
		bundleVideo.putInt("videoId", tipo);
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		SearchRecipesFragment frag = new SearchRecipesFragment();
    	frag.setArguments(bundleVideo);
    	ft.replace(R.id.listaRecetasBusqueda, frag).commit();*/
    	
    	/*if(this.vPager.getCurrentItem() == 0) {
    		Fragment fragment = new SearchRecipesFragment();
    		getSupportFragmentManager().beginTransaction()
    		.replace(R.id.relativeBusqueda, fragment).commit();
		} else {
			Fragment fragment = new FavoriteRecipesFragment();
    		getSupportFragmentManager().beginTransaction()
    		.replace(R.id.relativeFavoritos, fragment).commit();
		}*/
    }
    
    private void refrescarMultipleRecetas(JSONArray option) {
    	
    }

}
