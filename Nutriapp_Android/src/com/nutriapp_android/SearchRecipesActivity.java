package com.nutriapp_android;

import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;

import com.nutriapp_android.adapter.PagerAdapter;
import com.nutriapp_android.frgments.FavoriteRecipesFragment;
import com.nutriapp_android.frgments.SearchRecipesFragment;

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
	int TIPO_TEMP;
	
	private MenuItem buscar_receta;
	
	private PagerAdapter mPagerAdapter;
	private ViewPager vPager;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe);
        
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle(R.string.titulo_actionbar);
        
        Bundle bundleFiltro = this.getIntent().getExtras();
        Toast.makeText(this, "filter: "+bundleFiltro.getString("filtro_recetas"), Toast.LENGTH_SHORT).show();
        JSONArray filter;
        try {
        	filter = new JSONArray(bundleFiltro.getString("filtro_recetas"));
        	if(filter.length() == 1) {
        		refrescarBusqueda(Integer.parseInt(filter.getString(0)));
        	} else {
        		for(int i=0; i<filter.length(); i++) {
            		
            	}
        	}
		} catch (JSONException e) {
			e.printStackTrace();
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
		
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_recipes, menu);
        buscar_receta = menu.findItem(R.id.itemBuscarReceta);
        
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
	        case R.id.itemBuscarReceta:
	        	expandedSearchView();
	        return true;
	        
	        default:
	            return super.onOptionsItemSelected(item);
        }
    }
    
    @SuppressWarnings("rawtypes")
	public void expandedSearchView() {
    	buscar_receta.setActionView(R.layout.menu_search_recipe);
    	View viewExpand = (View) buscar_receta.getActionView();
    	
    	ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.array_busqueda,android.R.layout.simple_spinner_item);
    	spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	
        final Spinner tipo_busqueda = (Spinner) viewExpand.findViewById(R.id.searchInputItem);
        tipo_busqueda.setAdapter(spinnerAdapter);
        tipo_busqueda.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
            	((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
            	//TIPO_TEMP = tipo_busqueda.getItemAtPosition(pos).toString();
            	TIPO_TEMP = pos+1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {  }
        });
        
        final ImageView imgSearch = (ImageView) viewExpand.findViewById(R.id.searchBtnItem);
        imgSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	            Toast.makeText(SearchRecipesActivity.this, "buscar receta "+TIPO_TEMP, Toast.LENGTH_SHORT).show();
				buscar_receta.setActionView(null);
				refrescarBusqueda(TIPO_TEMP);
			}
		});
    }
    
    private void refrescarBusqueda(int tipo) {
    	switch(tipo) {
    		case 0:
			
			break;
    		case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
				
				break;
			
			default: break;
		}
    }

}
