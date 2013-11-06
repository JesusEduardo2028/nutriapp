package com.nutriapp_android;

import static com.nutriapp_android.config.ConstantsClient.ARRAY_COMIDA;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nutriapp_android.adapter.MultiSpinnerAdapter;
import com.nutriapp_android.internal_storage.RecipesHelper;

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
        
        llenar_datos();
        
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
	
	private void llenar_datos() {
    	try {
    		JSONArray jsonArray = new JSONArray();
    		
    		// receta 1
    		JSONObject jReceta1 = new JSONObject();
    		jReceta1.put("_id", "523e2495078c00544e00000");
    		jReceta1.put("titulo", "ensalada de verduras");
    		jReceta1.put("imagen", "http://t0.gstatic.com/images?q=tbn:ANd9GcTg2Cw4DdPQnjOZeUuFWXPEDKU_nF62NOk4XXKFo5xFNsOUnQ3H3eIBZGhe");
        	jReceta1.put("precio", "8000");
        	jReceta1.put("calorias", "150");
        	jReceta1.put("sodio", "150");
        	jReceta1.put("fibra", "150");
        	jReceta1.put("tipo_receta", 1);
        	jReceta1.put("tipo_comida", 1);
        	jReceta1.put("calificacion", 4);
        	
        	jReceta1.put("restaurante", "ITÍNERANTE RESTAURANTE");
        	jReceta1.put("direccion", "Cl 18 N 8-90 L 3");
        	jReceta1.put("latitude", 2.4325084);
        	jReceta1.put("longitude", -76.6119746);
        	jReceta1.put("telefono", "28204151");
        	
        	jReceta1.put("preparacion_receta", null);
        	jReceta1.put("descripcion_receta", "La ensalada de lechuga y pollo es una opción perfecta para comenzar una comida o una cena. Y es que, esta ensalada templada resulta irresistible.");
        	jsonArray.put(jReceta1);
        	
        	// receta 2
        	JSONObject jReceta2 = new JSONObject();
        	jReceta2.put("_id", "523e2495078c00544e00001");
        	jReceta2.put("titulo", "ensalada de verduras");
        	jReceta2.put("imagen", "http://t0.gstatic.com/images?q=tbn:ANd9GcTg2Cw4DdPQnjOZeUuFWXPEDKU_nF62NOk4XXKFo5xFNsOUnQ3H3eIBZGhe");
        	jReceta2.put("precio", "8000");
        	jReceta2.put("calorias", "150");
        	jReceta2.put("sodio", "150");
        	jReceta2.put("fibra", "150");
        	jReceta2.put("tipo_receta", 1);
        	jReceta2.put("tipo_comida", 2);
        	jReceta2.put("calificacion", 1);
        	
        	jReceta2.put("restaurante", "ITÍNERANTE RESTAURANTE");
        	jReceta2.put("direccion", "Cl 18 N 8-90 L 3");
        	jReceta2.put("latitude", 2.4325084);
        	jReceta2.put("longitude", -76.6119746);
        	jReceta2.put("telefono", "28204151");
        	
        	jReceta2.put("preparacion_receta", null);
        	jReceta2.put("descripcion_receta", "La ensalada de lechuga y pollo es una opción perfecta para comenzar una comida o una cena. Y es que, esta ensalada templada resulta irresistible.");
        	jsonArray.put(jReceta2);
        	
        	// receta 3
        	JSONObject jReceta3 = new JSONObject();
        	jReceta3.put("_id", "523e2495078c00544e00002");
        	jReceta3.put("titulo", "Pargo rojo caribeño");
        	jReceta3.put("imagen", "http://wasabicary.net/wp-content/uploads/Crazy-Red-snapper.jpg");
        	jReceta3.put("precio", null);
        	jReceta3.put("calorias", "220");
        	jReceta3.put("sodio", "0");
        	jReceta3.put("fibra", "2");
        	jReceta3.put("tipo_receta", 2);
        	jReceta3.put("tipo_comida", 4);
        	jReceta3.put("calificacion", 3);
        	
        	jReceta3.put("restaurante", null);
        	jReceta3.put("direccion", null);
        	jReceta3.put("latitude", null);
        	jReceta3.put("longitude", null);
        	jReceta3.put("telefono", null);
        	
        	JSONArray jPreparacion3 = new JSONArray();
        	jPreparacion3.put("Ingredientes: \n * 1⁄4 de pargo rojo \n * 1⁄2 taza de verduras");
        	jPreparacion3.put("1. En una sartén grande, caliente el aceite de oliva a fuego medio. Añada la cebo- lla, el pimentón rojo, las zanahorias y el ajo; sofría por 10 minutos. Añada el vino y deje hervir. Mueva las verduras a un lado de la sartén.");
        	jPreparacion3.put("2. Coloque los filetes en el centro de la sar- tén en una sola capa. Tape y cocine por 5 minutos.");
        	jPreparacion3.put("3. Añada el tomate y las aceitunas, y al fi- nal cubra con el queso. Tape y cocine por 3 minutos o hasta que el pescado se vea firme pero jugoso.");
        	jPreparacion3.put("4. Pase el pescado a la bandeja de servir. Adorne con los vegetales y los jugos que quedaron en la sartén.");
        	jReceta3.put("preparacion_receta", jPreparacion3);
        	
        	jReceta3.put("descripcion_receta", null);
        	jsonArray.put(jReceta3);
        	
        	// receta 4
        	JSONObject jReceta4 = new JSONObject();
        	jReceta4.put("_id", "523e2495078c00544e00003");
        	jReceta4.put("titulo", "ensalada de verduras");
        	jReceta4.put("imagen", "http://t0.gstatic.com/images?q=tbn:ANd9GcTg2Cw4DdPQnjOZeUuFWXPEDKU_nF62NOk4XXKFo5xFNsOUnQ3H3eIBZGhe");
        	jReceta4.put("precio", "8000");
        	jReceta4.put("calorias", "150");
        	jReceta4.put("sodio", "150");
        	jReceta4.put("fibra", "150");
        	jReceta4.put("tipo_receta", 1);
        	jReceta4.put("tipo_comida", 5);
        	jReceta4.put("calificacion", 4);
        	
        	jReceta4.put("restaurante", "ITÍNERANTE RESTAURANTE");
        	jReceta4.put("direccion", "Cl 18 N 8-90 L 3");
        	jReceta4.put("latitude", 2.4325084);
        	jReceta4.put("longitude", -76.6119746);
        	jReceta4.put("telefono", "28204151");
        	
        	jReceta4.put("preparacion_receta", null);
        	jReceta4.put("descripcion_receta", "La ensalada de lechuga y pollo es una opción perfecta para comenzar una comida o una cena. Y es que, esta ensalada templada resulta irresistible.");
        	jsonArray.put(jReceta4);
        	
        	// receta 5
        	JSONObject jReceta5 = new JSONObject();
        	jReceta5.put("_id", "523e2495078c00544e00004");
        	jReceta5.put("titulo", "Pargo rojo caribeño");
        	jReceta5.put("imagen", "http://wasabicary.net/wp-content/uploads/Crazy-Red-snapper.jpg");
        	jReceta5.put("precio", null);
        	jReceta5.put("calorias", "220");
        	jReceta5.put("sodio", "0");
        	jReceta5.put("fibra", "2");
        	jReceta5.put("tipo_receta", 2);
        	jReceta5.put("tipo_comida", 1);
        	jReceta5.put("calificacion", 4);
        	
        	jReceta5.put("restaurante", null);
        	jReceta5.put("direccion", null);
        	jReceta5.put("latitude", null);
        	jReceta5.put("longitude", null);
        	jReceta5.put("telefono", null);
        	
        	JSONArray jPreparacion5 = new JSONArray();
        	jPreparacion5.put("Ingredientes: \n * 1⁄4 de pargo rojo \n * 1⁄2 taza de verduras");
        	jPreparacion5.put("1. En una sartén grande, caliente el aceite de oliva a fuego medio. Añada la cebo- lla, el pimentón rojo, las zanahorias y el ajo; sofría por 10 minutos. Añada el vino y deje hervir. Mueva las verduras a un lado de la sartén.");
        	jPreparacion5.put("2. Coloque los filetes en el centro de la sar- tén en una sola capa. Tape y cocine por 5 minutos.");
        	jPreparacion5.put("3. Añada el tomate y las aceitunas, y al fi- nal cubra con el queso. Tape y cocine por 3 minutos o hasta que el pescado se vea firme pero jugoso.");
        	jPreparacion5.put("4. Pase el pescado a la bandeja de servir. Adorne con los vegetales y los jugos que quedaron en la sartén.");
        	jReceta5.put("preparacion_receta", jPreparacion5);
        	
        	jReceta5.put("descripcion_receta", null);
        	jsonArray.put(jReceta5);
        	
        	// receta 6
        	JSONObject jReceta6 = new JSONObject();
        	jReceta6.put("_id", "523e2495078c00544e00005");
        	jReceta6.put("titulo", "Pargo rojo caribeño");
        	jReceta6.put("imagen", "http://wasabicary.net/wp-content/uploads/Crazy-Red-snapper.jpg");
        	jReceta6.put("precio", null);
        	jReceta6.put("calorias", "220");
        	jReceta6.put("sodio", "0");
        	jReceta6.put("fibra", "2");
        	jReceta6.put("tipo_receta", 2);
        	jReceta6.put("tipo_comida", 1);
        	jReceta6.put("calificacion", 3);
        	
        	jReceta6.put("restaurante", null);
        	jReceta6.put("direccion", null);
        	jReceta6.put("latitude", null);
        	jReceta6.put("longitude", null);
        	jReceta6.put("telefono", null);
        	
        	JSONArray jPreparacion6 = new JSONArray();
        	jPreparacion6.put("Ingredientes: \n * 1⁄4 de pargo rojo \n * 1⁄2 taza de verduras");
        	jPreparacion6.put("1. En una sartén grande, caliente el aceite de oliva a fuego medio. Añada la cebo- lla, el pimentón rojo, las zanahorias y el ajo; sofría por 10 minutos. Añada el vino y deje hervir. Mueva las verduras a un lado de la sartén.");
        	jPreparacion6.put("2. Coloque los filetes en el centro de la sar- tén en una sola capa. Tape y cocine por 5 minutos.");
        	jPreparacion6.put("3. Añada el tomate y las aceitunas, y al fi- nal cubra con el queso. Tape y cocine por 3 minutos o hasta que el pescado se vea firme pero jugoso.");
        	jPreparacion6.put("4. Pase el pescado a la bandeja de servir. Adorne con los vegetales y los jugos que quedaron en la sartén.");
        	jReceta6.put("preparacion_receta", jPreparacion6);
        	
        	jReceta6.put("descripcion_receta", null);
        	jsonArray.put(jReceta6);
        	
        	RecipesHelper recipesHelper = new RecipesHelper(this);
        	recipesHelper.writeJsonRecetas(jsonArray.toString());
    	} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
