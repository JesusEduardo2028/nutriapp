package com.nutriapp_android;

import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nutriapp_android.R;
import com.nutriapp_android.adapter.PagerAdapter;
import com.nutriapp_android.frgments.FavoriteRecipesFragment;
import com.nutriapp_android.frgments.PreparationRecipeFragment;
import com.nutriapp_android.frgments.RecetaListItemFragment;
import com.nutriapp_android.frgments.SearchRecipesFragment;
import com.nutriapp_android.internal_storage.FavoriteRecipesHelper;
import com.nutriapp_android.internal_storage.RecipesHelper;

import android.app.ActionBar;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeDetailsActivity extends FragmentActivity {
	boolean ESTADO_RECETA = false;
	int FILTRO_PREVIEW = 1;
	JSONObject jReceta = null;
	
	RecipesHelper recipesHelper;
	FavoriteRecipesHelper favoriteHelper;
	
	ViewPager viewPreparacion;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle(R.string.titulo_actionbar);
        
        recipesHelper =  new RecipesHelper(this);
        favoriteHelper = new FavoriteRecipesHelper(this);
        
        ImageView imagen = (ImageView) findViewById(R.id.imagenDReceta);
        TextView titulo = (TextView) findViewById(R.id.tituloDReceta);
        TextView restaurante = (TextView) findViewById(R.id.nombreDRestaurante);
        TextView calorias = (TextView) findViewById(R.id.caloriasDReceta);
        TextView sodio = (TextView) findViewById(R.id.sodioDReceta);
        TextView fibra = (TextView) findViewById(R.id.fibraDReceta);
        TextView tipo_comida = (TextView) findViewById(R.id.tipoDRecetas);
        TextView precio = (TextView) findViewById(R.id.precioDReceta);
        RatingBar calificacion = (RatingBar) findViewById(R.id.puntajeDReceta);
        
        TextView preparacion = (TextView) findViewById(R.id.tituloDPreparacion);
        TextView descripcion = (TextView) findViewById(R.id.descripcionDReceta);
        viewPreparacion = (ViewPager) super.findViewById(R.id.viewPagesPreparacion);
        
        Button favoritos = (Button) findViewById(R.id.botonDFavoritos);
        Button compartir = (Button) findViewById(R.id.botonDCompartir);
        Button localizacion = (Button) findViewById(R.id.botonDLocalizacion);
        Button llamar_pedido = (Button) findViewById(R.id.botonDLLamada);
        Button productos = (Button) findViewById(R.id.botonDProductos);
        
        Bundle bundleDetalles = this.getIntent().getExtras();
        if(bundleDetalles.getBoolean("receta_almacenda")) {
        	ESTADO_RECETA = true;
        	jReceta = favoriteHelper.getDatosRFavorita(bundleDetalles.getString("id_receta"));
        	favoritos.setEnabled(false);
        } else {
        	jReceta = recipesHelper.getDatosReceta(bundleDetalles.getString("id_receta"));
        }
        
        //llenar_datos();
        
        try {
        	titulo.setText(jReceta.getString("titulo"));
        	calorias.setText("C: "+jReceta.getString("calorias"));
	        sodio.setText("S: "+jReceta.getString("sodio"));
	        fibra.setText("F: "+jReceta.getString("fibra"));
	        
	        calificacion.setRating((float) jReceta.getInt("calificacion"));
	        calificacion.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
				@Override
				public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
					Toast.makeText(RecipeDetailsActivity.this, "pop rating", Toast.LENGTH_SHORT).show();
				}
			});
	        
	        switch(jReceta.getInt("tipo_comida")) {
				case 1:
					tipo_comida.setText("Tipo: Desayuno");
				break;
				case 2:
					tipo_comida.setText("Tipo: Nueves");
				break;
				case 3:
					tipo_comida.setText("Tipo: Almuerzo");
				break;
				case 4:
					tipo_comida.setText("Tipo: Onces");
				break;
				case 5:
					tipo_comida.setText("Tipo: Cena");
				break;

				default:	break;
			}
	        
			if(jReceta.getInt("tipo_receta") == 1) {
				productos.setEnabled(false);
				
				restaurante.setText(jReceta.getString("restaurante"));
				precio.setText("$ "+jReceta.getString("precio"));
				preparacion.setText("DESCRIPCION RECETA:");
				descripcion.setText(jReceta.getString("descripcion_receta"));
				
				viewPreparacion.setVisibility(View.GONE);
			} else {
				localizacion.setEnabled(false);
				llamar_pedido.setEnabled(false);
				
				restaurante.setVisibility(View.GONE);
				precio.setVisibility(View.GONE);
				descripcion.setVisibility(View.GONE);
				
				preparacion.setText("PREPARACION RECETA:");
				desplegarPreparacion(jReceta.getJSONArray("preparacion_receta"));
			}
			
			favoritos.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					favoriteHelper.addJsonRFavorita(jReceta.toString());
					Toast.makeText(RecipeDetailsActivity.this, "Receta almacenada!!!", Toast.LENGTH_SHORT).show();
				}
			});
			
	        compartir.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
				}
			});
	        
	        if(!jReceta.optString("latitude").isEmpty() && !jReceta.optString("latitude").equals("null")) {
	        	final String n_restaurante = jReceta.getString("restaurante");
	        	final String direccion = jReceta.getString("direccion");
	        	final double latitud = jReceta.getDouble("latitude");
	        	final double longitud = jReceta.getDouble("longitude");
	        	
	        	localizacion.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						rutaRestaurante(n_restaurante, direccion, latitud, longitud);
					}
				});
			} else {
				llamar_pedido.setEnabled(false);
			}
	        
	        if(!jReceta.optString("telefono").isEmpty() && !jReceta.optString("telefono").equals("null")) {
	        	final String telefono = jReceta.getString("telefono");
	        	llamar_pedido.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						callDial(telefono);
					}
				});
			} else {
				llamar_pedido.setEnabled(false);
			}
	        
	        productos.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
				}
			});
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private void desplegarPreparacion(JSONArray pasos) {
		List<Fragment> fragments = new Vector<Fragment>();
		
        for(int i=0; i<pasos.length(); i++){
			Fragment fragment = new PreparationRecipeFragment();
			Bundle bundle = new Bundle();
			
			try {
				bundle.putString("pasos_receta", pasos.getString(i));
			} catch (JSONException e) {		e.printStackTrace();	}
			
			fragment.setArguments(bundle);
			fragments.add(fragment);
		}
        
        PagerAdapter mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragments);
        viewPreparacion.setAdapter(mPagerAdapter);
	}
	
	private void llenar_datos() {
		/*try {
    		jReceta = new JSONObject();
        	jReceta.put("_id", "523e2495078c00544e000055");
        	jReceta.put("titulo", "ensalada de verduras");
        	jReceta.put("imagen", "http://t0.gstatic.com/images?q=tbn:ANd9GcTg2Cw4DdPQnjOZeUuFWXPEDKU_nF62NOk4XXKFo5xFNsOUnQ3H3eIBZGhe");
        	jReceta.put("precio", "8000");
        	jReceta.put("calorias", "150");
        	jReceta.put("sodio", "150");
        	jReceta.put("fibra", "150");
        	jReceta.put("tipo_receta", 1);
        	jReceta.put("tipo_comida", 5);
        	jReceta.put("calificacion", 2);
        	
        	jReceta.put("restaurante", "ITÍNERANTE RESTAURANTE");
        	jReceta.put("direccion", "Cl 18 N 8-90 L 3");
        	jReceta.put("latitude", 2.4325084);
        	jReceta.put("longitude", -76.6119746);
        	jReceta.put("telefono", "28204151");
        	
        	jReceta.put("preparacion_receta", null);
        	jReceta.put("descripcion_receta", "La ensalada de lechuga y pollo es una opción perfecta para comenzar una comida o una cena. Y es que, esta ensalada templada resulta irresistible.");
		} catch (JSONException e) {
			e.printStackTrace();
		}*/
		
		try {			
    		jReceta = new JSONObject();
        	jReceta.put("_id", "523e2495078c00544e000055");
        	jReceta.put("titulo", "Pargo rojo caribeño");
        	jReceta.put("imagen", "http://wasabicary.net/wp-content/uploads/Crazy-Red-snapper.jpg");
        	jReceta.put("precio", null);
        	jReceta.put("calorias", "220");
        	jReceta.put("sodio", "0");
        	jReceta.put("fibra", "2");
        	jReceta.put("tipo_receta", 2);
        	jReceta.put("tipo_comida", 3);
        	jReceta.put("calificacion", 4);
        	
        	jReceta.put("restaurante", null);
        	jReceta.put("direccion", null);
        	jReceta.put("latitude", null);
        	jReceta.put("longitude", null);
        	jReceta.put("telefono", null);
        	
        	JSONArray jPreparacion = new JSONArray();
        	jPreparacion.put("Ingredientes: \n * 1⁄4 de pargo rojo \n * 1⁄2 taza de verduras");
        	jPreparacion.put("1. En una sartén grande, caliente el aceite de oliva a fuego medio. Añada la cebo- lla, el pimentón rojo, las zanahorias y el ajo; sofría por 10 minutos. Añada el vino y deje hervir. Mueva las verduras a un lado de la sartén.");
        	jPreparacion.put("2. Coloque los filetes en el centro de la sar- tén en una sola capa. Tape y cocine por 5 minutos.");
        	jPreparacion.put("3. Añada el tomate y las aceitunas, y al fi- nal cubra con el queso. Tape y cocine por 3 minutos o hasta que el pescado se vea firme pero jugoso.");
        	jPreparacion.put("4. Pase el pescado a la bandeja de servir. Adorne con los vegetales y los jugos que quedaron en la sartén.");
        	jReceta.put("preparacion_receta", jPreparacion);
        	
        	jReceta.put("descripcion_receta", null);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private void rutaRestaurante(String restaurante, String direccion, double latitud, double longitud) {
		Toast.makeText(this, "desplegando ruta", Toast.LENGTH_SHORT).show();
	}
	
	private void callDial(String numberToCall) {
		try {
			Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+numberToCall));
			startActivity(Intent.createChooser(intentCall, "Call"));
		} catch (ActivityNotFoundException activityException) {
			Toast.makeText(this, "Call failed... !!!", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public void onBackPressed() {
		//super.onBackPressed();
		Intent intentRecetas = new Intent(this, SearchRecipesActivity.class);
		intentRecetas.putExtra("receta_almacenda", ESTADO_RECETA);
		intentRecetas.putExtra("filtro_preview", FILTRO_PREVIEW);
		startActivity(intentRecetas);
		finish();
	}

}
