package com.nutriapp_android;

import static com.nutriapp_android.config.ConstantsClient.ARRAY_COMIDA;

import org.json.JSONArray;
import org.json.JSONException;

import com.nutriapp_android.adapter.MultiSpinnerAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class SearchFilterSRActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter_sr);
        
        final MultiSpinnerAdapter estado_salud = (MultiSpinnerAdapter) findViewById(R.id.inputFiltroReceta);  
        estado_salud.setItems(ARRAY_COMIDA);

        ImageButton searchBtn = (ImageButton) findViewById(R.id.botonFiltro);
		searchBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean ALL = false;
				Intent intent = new Intent(SearchFilterSRActivity.this, SearchRecipesActivity.class);
				JSONArray jSon = estado_salud.getSelectedJsonIndicies();
				
				try {
					if(jSon.length() > 0) {
						for(int i=0; i<jSon.length(); i++) {
							if(jSon.getString(i).equals("5")) {		ALL = true;		}
						}
						
						if(ALL) {
							intent.putExtra("filtro_recetas", "[0]");
						} else {
							intent.putExtra("filtro_recetas", jSon.toString());
						}
					} else {
						intent.putExtra("filtro_recetas", "[1]");
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				startActivity(intent);
				finish();
			}
		});
        
	}

}
