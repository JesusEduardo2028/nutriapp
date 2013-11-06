package com.nutriapp_android.adapter;

import java.util.List;

import com.nutriapp_android.R;
import com.nutriapp_android.object.RecetaObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
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
        
        ImageView image_receta = (ImageView) view.findViewById(R.id.imagenReceta);
        ImageView tipo_receta = (ImageView) view.findViewById(R.id.imagenTipo);
        TextView titulo = (TextView) view.findViewById(R.id.tituloReceta);
        TextView restaurante = (TextView) view.findViewById(R.id.nombreRestaurante);
        TextView calorias = (TextView) view.findViewById(R.id.caloriasReceta);
        TextView sodio = (TextView) view.findViewById(R.id.sodioReceta);
        TextView fibra = (TextView) view.findViewById(R.id.fibraReceta);
        RatingBar puntaje = (RatingBar) view.findViewById(R.id.puntajeReceta);
        TextView precio = (TextView) view.findViewById(R.id.precioReceta);
        
        titulo.setText(i.getTituloReceta());
        calorias.setText("C: "+i.getCaloriasReceta());
        sodio.setText("S: "+i.getSodioReceta());
        fibra.setText("F: "+i.getFibraReceta());
        puntaje.setRating((float) i.getCalificacionReceta());
        
        switch(i.getTipoReceta()) {
			case 1:
				tipo_receta.setImageResource(R.drawable.desayuno);
				restaurante.setVisibility(View.VISIBLE);
				restaurante.setText(i.getNombreRestaurante());
				precio.setVisibility(View.VISIBLE);
				precio.setText("$ "+i.getPrecioReceta());
				break;
			case 2:
				tipo_receta.setImageResource(R.drawable.cena);
				restaurante.setVisibility(View.GONE);
				precio.setVisibility(View.GONE);
				break;
		}
        
		switch(i.getTipoComida()) {
			case 1:
				image_receta.setImageResource(R.drawable.desayuno);
				break;
			case 2:
				image_receta.setImageResource(R.drawable.nueve);
				break;
			case 3:
				image_receta.setImageResource(R.drawable.almuerzo);
				break;
			case 4:
				image_receta.setImageResource(R.drawable.once);
				break;
			case 5:
				image_receta.setImageResource(R.drawable.cena);
				break;
		}
		
		view.setTag(i.getIdReceta());
		
		return view;
	}

}
