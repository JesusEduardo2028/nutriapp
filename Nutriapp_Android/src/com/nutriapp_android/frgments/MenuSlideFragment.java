package com.nutriapp_android.frgments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.nutriapp_android.MainActivity;
import com.nutriapp_android.R;

public class MenuSlideFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.list, null);
	}

	//******Creando items de la lista lateral**************//
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onActivityCreated(savedInstanceState);
		SampleAdapter adapter = new SampleAdapter(getActivity());

		adapter.add(new SampleItem("perfil", android.R.drawable.ic_menu_add));
		adapter.add(new SampleItem("listas", android.R.drawable.ic_menu_add));
		adapter.add(new SampleItem("recomendacion",android.R.drawable.ic_input_get));
		adapter.add(new SampleItem("configuracion",android.R.drawable.ic_menu_preferences));

		setListAdapter(adapter);
	}
	
	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.row, null);

			}
			TextView texto = (TextView) convertView
					.findViewById(R.id.row_title);
			ImageView imagen = (ImageView) convertView
					.findViewById(R.id.row_icon);

			if (getItem(position).tag.equals("perfil")) {
				texto.setText("Ver Dietas");
				imagen.setImageDrawable(getContext().getResources()
						.getDrawable(getItem(position).iconRes));
			} else if (getItem(position).tag.equals("recomendacion")) {
				texto.setText("Recomendaciones");
				imagen.setImageDrawable(getContext().getResources()
						.getDrawable(getItem(position).iconRes));
			} else if (getItem(position).tag.equals("configuracion")) {
				texto.setText("Amigos");
				imagen.setImageDrawable(getContext().getResources()
						.getDrawable(getItem(position).iconRes));
			} else if (getItem(position).tag.equals("listas")) {
				texto.setText("Mis Listas");
				imagen.setImageDrawable(getContext().getResources()
						.getDrawable(getItem(position).iconRes));
			}

			return convertView;
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Fragment nuevoContenido = null;

		switch (position) {
		 case 0:
		 nuevoContenido = new ProfileFragment();
		 switchFragment(nuevoContenido);break;

		// case 1:
		// nuevoContenido = new ContenedorTabla();
		// switchFragment(nuevoContenido);break;
		case 2:
			// nuevoContenido = new RecomendadorFragment();
			// switchFragment(nuevoContenido);break;
		case 3:
			// nuevoContenido = new ContenedorTabla();
			// switchFragment_amigos(nuevoContenido);break;
		}

	}

	private void switchFragment(Fragment nuevoContenido) {
		// TODO Auto-generated method stub
		if (getActivity() == null) {
			return;
		}
		if (getActivity() instanceof MainActivity) {
			MainActivity actividad = (MainActivity) getActivity();
			actividad.switchContent(nuevoContenido);
		}
	}

	private class SampleItem {
		public String tag;
		public int iconRes;

		public SampleItem(String tag, int iconRes) {
			this.tag = tag;
			this.iconRes = iconRes;
		}
	}



}
