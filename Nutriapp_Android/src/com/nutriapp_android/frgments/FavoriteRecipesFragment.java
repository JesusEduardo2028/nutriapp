package com.nutriapp_android.frgments;

import java.util.ArrayList;

import com.nutriapp_android.R;
import com.nutriapp_android.RecipeDetailsActivity;
import com.nutriapp_android.adapter.BusquedaRecetasAdapter;
import com.nutriapp_android.internal_storage.FavoriteRecipesHelper;
import com.nutriapp_android.object.RecetaObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class FavoriteRecipesFragment extends Fragment {
	ArrayList<RecetaObject> ARRAY_FAVORITE = null;
	
	FavoriteRecipesHelper favoriteHelper;
	
	ListView lista_recetas;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_recipes, container, false);
        
        favoriteHelper = new FavoriteRecipesHelper(getActivity());
        
        if(favoriteHelper.existeJsonRFavoritas()) {
        	ARRAY_FAVORITE = favoriteHelper.getArrayTodasRFavoritas();
        	
        	BusquedaRecetasAdapter searchAdapter = new BusquedaRecetasAdapter(getActivity(), R.layout.row_search_recipe, ARRAY_FAVORITE);
			lista_recetas = (ListView) view.findViewById(R.id.listaRecetasFavoritas);
	        lista_recetas.setAdapter(searchAdapter);
	        lista_recetas.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View item, int pos, long arg3) {
					Toast.makeText(getActivity(), "--- receta: "+item.getTag(), Toast.LENGTH_SHORT).show();
					Intent intentDetails = new Intent(getActivity(), RecipeDetailsActivity.class);
					intentDetails.putExtra("id_receta", ARRAY_FAVORITE.get(pos).getIdReceta());
					intentDetails.putExtra("receta_almacenda", true);
					startActivity(intentDetails);
					getActivity().finish();
				}
			});
		} else {
			RelativeLayout relativeFavoritos = (RelativeLayout) view.findViewById(R.id.relativeFavoritos);
			relativeFavoritos.removeAllViews();
			TextView result = new TextView(getActivity());
			result.setText("NO HAY RECETAS ALMACENADAS POR EL USUARIO !!!");
			result.setTextSize(16);
			result.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
			LayoutParams params = new LayoutParams(300, 200);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			relativeFavoritos.addView(result,params);
		}
        
        return view;
    }
	
	public void refrescarFavoritos(int tipo) {
		Toast.makeText(getActivity(), "-- tipo: "+tipo, Toast.LENGTH_SHORT).show();
    	switch(tipo) {
			case 1:
				ARRAY_FAVORITE = favoriteHelper.getArrayTipoRFavoritas(1);
				break;
			case 2:
				ARRAY_FAVORITE = favoriteHelper.getArrayTipoRFavoritas(2);
				break;
			case 3:
				ARRAY_FAVORITE = favoriteHelper.getArrayTipoRFavoritas(3);
				break;
			case 4:
				ARRAY_FAVORITE = favoriteHelper.getArrayTipoRFavoritas(4);
				break;
			case 5:
				ARRAY_FAVORITE = favoriteHelper.getArrayTipoRFavoritas(5);
				break;
			case 6:
				ARRAY_FAVORITE = favoriteHelper.getArrayTodasRFavoritas();
				break;
			default: break;
		}
    	
    	/*BusquedaRecetasAdapter searchAdapter = new BusquedaRecetasAdapter(getActivity(), R.layout.row_search_recipe, ARRAY_SEARCH);
        lista_recetas.setAdapter(searchAdapter);
        lista_recetas.requestLayout();*/
    }
	
	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }

}
