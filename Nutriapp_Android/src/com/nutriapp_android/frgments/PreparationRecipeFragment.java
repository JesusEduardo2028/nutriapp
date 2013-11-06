package com.nutriapp_android.frgments;

import com.nutriapp_android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PreparationRecipeFragment extends Fragment {
	private TextView preparacion;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_preparation_recipe, container, false);
		
		String pasos = getArguments().getString("pasos_receta");
		
		preparacion = (TextView) view.findViewById(R.id.pasosPreparacion);
		preparacion.setText(pasos);
		
		return view;
	}
	
	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }
	
}
