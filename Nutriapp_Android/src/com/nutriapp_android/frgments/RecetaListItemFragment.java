package com.nutriapp_android.frgments;

import com.nutriapp_android.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RecetaListItemFragment extends Fragment {

	private Activity a;
	private TextView textView_nombre_receta;
	private ImageView imageView_receta;
	Button botonReceta;
	private InterfazSeleccionReceta interfaz;
	
	
	public RecetaListItemFragment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		 interfaz = (InterfazSeleccionReceta)activity;
		a = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final Integer comida = getArguments().getInt("comida");

		View view = inflater.inflate(R.layout.layout_fragment_receta_item, container,
				false);

		textView_nombre_receta = (TextView) view.findViewById(R.id.textView1);
		imageView_receta = (ImageView) view.findViewById(R.id.imageView2);
		botonReceta = (Button) view.findViewById(R.id.button1);
		
		botonReceta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				interfaz.recetaSeleccionada(comida);
				
			}
		});
		
		switch (comida) {
		case 1:
			textView_nombre_receta.setText("nombre desa");
			imageView_receta.setImageResource(R.drawable.desayuno);
			break;
		case 2:
			textView_nombre_receta.setText("nombre nueve");
			imageView_receta.setImageResource(R.drawable.nueve);
			break;
		case 3:
			textView_nombre_receta.setText("nombre almuer");
			imageView_receta.setImageResource(R.drawable.almuerzo);
			break;
		case 4:
			textView_nombre_receta.setText("nombre once");
			imageView_receta.setImageResource(R.drawable.once);
			break;
		case 5:
			textView_nombre_receta.setText("nombre cena");
			imageView_receta.setImageResource(R.drawable.cena);
			break;

		default:
			break;
		}

		// TODO Auto-generated method stub
		return view;
	}

	public interface InterfazSeleccionReceta{
		public void recetaSeleccionada(Integer comida);
	}
	
}
