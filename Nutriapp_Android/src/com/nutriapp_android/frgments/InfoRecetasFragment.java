package com.nutriapp_android.frgments;

import com.nutriapp_android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class InfoRecetasFragment extends Fragment {

	
	
	private ImageView imageView_receta;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_info_fragment, container, false);
        
        final Integer comida = getArguments().getInt("comida");
        
		imageView_receta = (ImageView) view.findViewById(R.id.imageView1);
		
		switch (comida) {
		case 1:
	
		imageView_receta.setImageResource(R.drawable.desayuno);
			break;
		case 2:
		
			imageView_receta.setImageResource(R.drawable.nueve);
			break;
		case 3:
		
			imageView_receta.setImageResource(R.drawable.almuerzo);
			break;
		case 4:
		
			imageView_receta.setImageResource(R.drawable.once);
			break;
		case 5:
			
			imageView_receta.setImageResource(R.drawable.cena);
			break;
		}
		
        return view;
    }
	
	
	
	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }
	
	

}
