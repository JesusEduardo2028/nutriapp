package com.nutriapp_android.frgments;

import java.util.ArrayList;

import com.nutriapp_android.R;
import com.nutriapp_android.RecipeDetailsActivity;
import com.nutriapp_android.SearchRecipesActivity;
import com.nutriapp_android.adapter.BusquedaRecetasAdapter;
import com.nutriapp_android.internal_storage.RecipesHelper;
import com.nutriapp_android.object.RecetaObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SearchRecipesFragment extends Fragment {
	ArrayList<RecetaObject> ARRAY_SEARCH = null;
	
	RecipesHelper recipeHelper;
	
	ListView lista_recetas;
	
	/*@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle argBundle = this.getArguments();
		if(argBundle != null) {
			Toast.makeText(getActivity(), "bundle: "+argBundle.getInt("videoId"), Toast.LENGTH_SHORT).show();
			refrescarBusqueda(argBundle.getInt("videoId"));
		}
	}*/
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_recipe, container, false);
        
        recipeHelper = new RecipesHelper(getActivity());
        refrescarBusqueda(SearchRecipesActivity.TIPO_FILTRO);
        
        BusquedaRecetasAdapter searchAdapter = new BusquedaRecetasAdapter(getActivity(), R.layout.row_search_recipe, ARRAY_SEARCH);
        lista_recetas = (ListView) view.findViewById(R.id.listaRecetasBusqueda);
        lista_recetas.setAdapter(searchAdapter);
        lista_recetas.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View item, int pos, long arg3) {
				Toast.makeText(getActivity(), "--- busqueda receta: "+item.getTag(), Toast.LENGTH_SHORT).show();
				Intent intentDetails = new Intent(getActivity(), RecipeDetailsActivity.class);
				intentDetails.putExtra("id_receta", ARRAY_SEARCH.get(pos).getIdReceta());
				intentDetails.putExtra("receta_almacenda", false);
				startActivity(intentDetails);
				getActivity().finish();
			}
		});
        
        return view;
    }
	
	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }
	
	public void refrescarBusqueda(int tipo) {
		Toast.makeText(getActivity(), "-- tipo: "+tipo, Toast.LENGTH_SHORT).show();
    	switch(tipo) {
			case 1:
				ARRAY_SEARCH = recipeHelper.getArrayTipoRecetas(1);
				break;
			case 2:
				ARRAY_SEARCH = recipeHelper.getArrayTipoRecetas(2);
				break;
			case 3:
				ARRAY_SEARCH = recipeHelper.getArrayTipoRecetas(3);
				break;
			case 4:
				ARRAY_SEARCH = recipeHelper.getArrayTipoRecetas(4);
				break;
			case 5:
				ARRAY_SEARCH = recipeHelper.getArrayTipoRecetas(5);
				break;
			case 6:
				ARRAY_SEARCH = recipeHelper.getArrayTodasRecetas();
				break;
			default: break;
		}
    	
    	/*BusquedaRecetasAdapter searchAdapter = new BusquedaRecetasAdapter(getActivity(), R.layout.row_search_recipe, ARRAY_SEARCH);
        lista_recetas.setAdapter(searchAdapter);
        lista_recetas.requestLayout();*/
    }

}
