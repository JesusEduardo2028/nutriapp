package com.nutriapp_android.adapter;

import java.util.List;

import com.nutriapp_android.R;
import com.nutriapp_android.object.RecetaObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BusquedaRecetasAdapter extends ArrayAdapter<RecetaObject> {
	
	public BusquedaRecetasAdapter(Context context, int viewRecetas, List<RecetaObject> objects) {
		super(context, viewRecetas, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_search_recipe, null);
            
        }
        
        final RecetaObject i = super.getItem(position);
        
        ImageView image = (ImageView) view.findViewById(R.id.imageRecetaBus);
        TextView titulo = (TextView) view.findViewById(R.id.tituloRecetaBus);
        Button receta = (Button) view.findViewById(R.id.verRecetaBus);
        
        titulo.setText(i.getRecetaTitulo());
		switch(i.getRecetaTipo()) {
			case 1:
				image.setImageResource(R.drawable.desayuno);
				break;
			case 2:
				image.setImageResource(R.drawable.nueve);
				break;
			case 3:
				image.setImageResource(R.drawable.almuerzo);
				break;
			case 4:
				image.setImageResource(R.drawable.once);
				break;
			case 5:
				image.setImageResource(R.drawable.cena);
				break;
	
			default:
				break;
		}
		
		receta.setTag(i.getRecetaId());
		view.setTag(i.getRecetaId());
		
		return view;
	}

}
