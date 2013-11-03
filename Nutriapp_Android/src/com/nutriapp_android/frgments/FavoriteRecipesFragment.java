package com.nutriapp_android.frgments;

import com.nutriapp_android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class FavoriteRecipesFragment extends Fragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_recipes, container, false);
        
        RelativeLayout relativeFavoritos = (RelativeLayout) view.findViewById(R.id.relativeFavoritos);
        
        /*if() {
						
		} else {*/
			relativeFavoritos.removeAllViews();
			TextView result = new TextView(getActivity());
			result.setText("NO HAY RECETAS ALMACENADAS POR EL USUARIO !!!");
			result.setTextSize(16);
			result.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
			LayoutParams params = new LayoutParams(300, 200);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			relativeFavoritos.addView(result,params);
		//}
        
        return view;
    }
	
	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }

}
