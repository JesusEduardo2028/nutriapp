package com.nutriapp_android.frgments;

import com.nutriapp_android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SearchRecipesFragment extends Fragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_recipe, container, false);
        
        return view;
    }
	
	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }
	
	/*int TIPO_TEMP;
	ArrayList<RecetaObject> ARRAY_RECETAS;
	
	MenuItem buscar_receta;
	
	ListView lista_recetas;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe);
        
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle(R.string.titulo_actionbar);
        
        Bundle extraFiltro = this.getIntent().getExtras();
        extraFiltro.getString("filtro_recetas");
        
        llenar_datos();
        
        lista_recetas = (ListView) findViewById(R.id.listaRecetasBusq);
        BusquedaRecetasAdapter videosAdapter = new BusquedaRecetasAdapter(this, R.layout.row_search_recipe, ARRAY_RECETAS);
        lista_recetas.setAdapter(videosAdapter);
        lista_recetas.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View videoItem, int pos, long arg3) {
				
			}
		});
	}
	
	private void llenar_datos() {
		ARRAY_RECETAS = new ArrayList<RecetaObject>();
		
		RecetaObject receta1 = new RecetaObject("1","desayuno 1",1,"100","200","300");
		ARRAY_RECETAS.add(receta1);
		RecetaObject receta2 = new RecetaObject("2","desayuno 2",1,"100","200","300");
		ARRAY_RECETAS.add(receta2);
		RecetaObject receta3 = new RecetaObject("3","cena 1",5,"100","200","300");
		ARRAY_RECETAS.add(receta3);
		RecetaObject receta4 = new RecetaObject("4","cena 2",5,"100","200","300");
		ARRAY_RECETAS.add(receta4);
		RecetaObject receta5 = new RecetaObject("5","nueves 1",2,"100","200","300");
		ARRAY_RECETAS.add(receta5);
		RecetaObject receta6 = new RecetaObject("6","onces 1",4,"100","200","300");
		ARRAY_RECETAS.add(receta6);
		RecetaObject receta7 = new RecetaObject("7","almuerzo 1",3,"100","200","300");
		ARRAY_RECETAS.add(receta7);
	}
	
	private void llenar_datosDesayuno() {
		ARRAY_RECETAS = new ArrayList<RecetaObject>();
		
		RecetaObject receta1 = new RecetaObject("1","desayuno 1",1,"100","200","300");
		ARRAY_RECETAS.add(receta1);
		RecetaObject receta2 = new RecetaObject("2","desayuno 2",1,"100","200","300");
		ARRAY_RECETAS.add(receta2);
	}
	
	private void llenar_datosNueves() {
		ARRAY_RECETAS = new ArrayList<RecetaObject>();
		
		RecetaObject receta5 = new RecetaObject("5","nueves 1",2,"100","200","300");
		ARRAY_RECETAS.add(receta5);
	}
	
	private void llenar_datosAlmuerzo() {
		ARRAY_RECETAS = new ArrayList<RecetaObject>();
		
		RecetaObject receta7 = new RecetaObject("7","almuerzo 1",3,"100","200","300");
		ARRAY_RECETAS.add(receta7);
	}
	
	private void llenar_datosOnces() {
		ARRAY_RECETAS = new ArrayList<RecetaObject>();
		
		RecetaObject receta6 = new RecetaObject("6","onces 1",4,"100","200","300");
		ARRAY_RECETAS.add(receta6);
	}
	
	private void llenar_datosCena() {
		ARRAY_RECETAS = new ArrayList<RecetaObject>();
		
		RecetaObject receta3 = new RecetaObject("3","cena 1",5,"100","200","300");
		ARRAY_RECETAS.add(receta3);
		RecetaObject receta4 = new RecetaObject("4","cena 2",5,"100","200","300");
		ARRAY_RECETAS.add(receta4);
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
	            Toast.makeText(SearchRecipesFragment.this, "buscar receta "+TIPO_TEMP, Toast.LENGTH_SHORT).show();
				buscar_receta.setActionView(null);
				refrescarBusqueda(TIPO_TEMP);
			}
		});
    }
    
    private void refrescarBusqueda(int tipo) {
    	switch(tipo) {
			case 1:
				llenar_datosDesayuno();
				break;
			case 2:
				llenar_datosNueves();
				break;
			case 3:
				llenar_datosAlmuerzo();
				break;
			case 4:
				llenar_datosOnces();
				break;
			case 5:
				llenar_datosCena();
				break;
			case 6:
				llenar_datos();
				break;
			default: break;
		}
    	
    	BusquedaRecetasAdapter videosAdapter = new BusquedaRecetasAdapter(this, R.layout.row_search_recipe, ARRAY_RECETAS); 
    	lista_recetas.setAdapter(videosAdapter);
    	lista_recetas.requestLayout();
    }*/

}
