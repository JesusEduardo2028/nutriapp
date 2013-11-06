package com.nutriapp_android.internal_storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nutriapp_android.object.RecetaObject;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import static com.nutriapp_android.config.ConstantsClient.LISTA_RECETAS;

public class RecipesHelper {
	Context context;
	File file;
	
	String estado;
	
	public RecipesHelper(Context context) {
		this.context = context;
		estado = Environment.getExternalStorageState();		// Comprobamos el estado de la memoria externa (tarjeta SD)
		file = new File(context.getExternalFilesDir(null), LISTA_RECETAS);
	}
	
	public boolean existeJsonRecetas() {
    	if (file.exists()) {
    		return true;
    	} else {
    		return false;
    	}
	}
	
	public String getJsonRecetas() {
		String json = null;
    	if (estado.equals(Environment.MEDIA_MOUNTED)) {
    		try {
    		    BufferedReader fopen = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    		    json = fopen.readLine();
    		    fopen.close();
    		} catch (Exception ex) {
    			Log.i("recomHelper", "-> Error al leer fichero de la tarjeta SD");
    		}
    	}
		
		return json; 
	}
	
	public void writeJsonRecetas(String jArray) {
    	if(estado.equals(Environment.MEDIA_MOUNTED)) {
    		try {
    		    OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(file));
    		    fout.write(jArray);
    		    fout.close();
    		} catch (Exception ex) {
    			Log.i("recomHelper", "-> Error al escribir fichero a tarjeta SD");
    		}
    	}
	}
	
	public void updateJsonReceta(String id, JSONObject newReceta) {
		try {
			JSONArray recetas = new JSONArray();
			
			JSONArray json = new JSONArray(getJsonRecetas());
			for(int i=0; i<json.length(); i++) {
				if(json.getJSONObject(i).getString("_id").equals(id)) {
					recetas.put(newReceta);
				} else {
					recetas.put(json.getJSONObject(i));
				}
			}
			
			writeJsonRecetas(recetas.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<RecetaObject> getArrayTodasRecetas() {
		ArrayList<RecetaObject> array = new ArrayList<RecetaObject>();
		
		try {
			JSONArray recetas = new JSONArray(getJsonRecetas());
			for(int i=0; i<recetas.length(); i++) {
				JSONObject jReceta = recetas.getJSONObject(i);
				RecetaObject receta = new RecetaObject();
				
				Log.i("helper", "--> ***** id "+jReceta.getString("_id"));
				Log.i("helper", "--> ***** receta "+jReceta.getString("titulo"));
				
				receta.setIdReceta(jReceta.getString("_id"));
				receta.setTituloReceta(jReceta.getString("titulo")); 
				receta.setURLImagenReceta(jReceta.getString("imagen"));
				
				if(!jReceta.optString("precio").isEmpty() && !jReceta.optString("precio").equals("null")) {
					Log.i("helper", "-> hay data ");		receta.setPrecioReceta(jReceta.getString("precio"));
				} else {
					Log.i("helper", "-> no hay data2 ");	receta.setPrecioReceta("");
				}
				
				receta.setCaloriasReceta(jReceta.getString("calorias"));
				receta.setSodioReceta(jReceta.getString("sodio"));
				receta.setFibraReceta(jReceta.getString("fibra"));
				
				receta.setTipoReceta(jReceta.getInt("tipo_receta"));
				receta.setTipoComida(jReceta.getInt("tipo_comida"));
				receta.setCalificacionReceta(jReceta.getInt("calificacion"));
	        	
				if(!jReceta.optString("restaurante").isEmpty() && !jReceta.optString("restaurante").equals("null")) {
					Log.i("helper", "-> hay data ");		receta.setNombreRestaurante(jReceta.getString("restaurante"));
				} else {
					Log.i("helper", "-> no hay data2 ");	receta.setNombreRestaurante("");
				}
				
				/*if(!jReceta.optString("direccion").equals("null")) {
					Log.i("helper", "-> hay data ");		receta.setDireccionRestaurante(jReceta.getString("direccion"));
				} else {
					Log.i("helper", "-> no hay data2 ");	receta.setDireccionRestaurante("");
				}*/
				
				/*if(!jReceta.optString("telefono").equals("null")) {
					Log.i("helper", "-> hay data ");		receta.setTelefonoRestaurante(jReceta.getString("telefono"));
				} else {
					Log.i("helper", "-> no hay data2 ");	receta.setTelefonoRestaurante("");
				}
				
				if(!jReceta.optString("latitude").isEmpty()) {
					Log.i("helper", "-> * latitude: "+jReceta.getDouble("latitude"));
					receta.setLatitudeRestaurante(jReceta.getDouble("latitude"));
					receta.setLongitudeRestaurante(jReceta.getDouble("longitude"));
				} else{
					Log.i("helper", "-> **no hay coordenadas");
					receta.setLatitudeRestaurante(0.0);		receta.setLongitudeRestaurante(0.0);
				}
				
				if(!jReceta.optString("direccion").equals("null")) {
					Log.i("helper", "-> hay data ");		receta.setDireccionRestaurante(jReceta.getString("direccion"));
				} else {
					Log.i("helper", "-> no hay data2 ");	receta.setDireccionRestaurante("");
				}
				
				if(!jReceta.optString("direccion").equals("null")) {
					Log.i("helper", "-> hay data ");		receta.setDireccionRestaurante(jReceta.getString("direccion"));
				} else {
					Log.i("helper", "-> no hay data2 ");	receta.setDireccionRestaurante("");
				}
				
	        	jReceta.put("preparacion_receta", null);
	        	jReceta.put("descripcion_receta", "La ensalada d*/
				
				array.add(receta);
			}
		} catch (JSONException e) {		
			e.printStackTrace();
		}
		
		return array;
	}
	
	public ArrayList<RecetaObject> getArrayTipoRecetas(int tipo) {
		ArrayList<RecetaObject> array = new ArrayList<RecetaObject>();
		
		try {
			JSONArray recetas = new JSONArray(getJsonRecetas());
			for(int i=0; i<recetas.length(); i++) {
				JSONObject jReceta = recetas.getJSONObject(i);
				if(jReceta.getInt("tipo_comida") == tipo) {
					RecetaObject receta = new RecetaObject();
					
					Log.i("helper", "--> ***** id "+jReceta.getString("_id"));
					Log.i("helper", "--> ***** receta "+jReceta.getString("titulo"));
					
					receta.setIdReceta(jReceta.getString("_id"));
					receta.setTituloReceta(jReceta.getString("titulo"));
					receta.setURLImagenReceta(jReceta.getString("imagen"));
					
					if(!jReceta.optString("precio").isEmpty() && !jReceta.optString("precio").equals("null")) {
						Log.i("helper", "-> hay precio ");		receta.setPrecioReceta(jReceta.getString("precio"));
					} else {
						Log.i("helper", "-> no hay precio ");	receta.setPrecioReceta("");
					}
					
					receta.setCaloriasReceta(jReceta.getString("calorias"));
					receta.setSodioReceta(jReceta.getString("sodio"));
					receta.setFibraReceta(jReceta.getString("fibra"));
					
					receta.setTipoReceta(jReceta.getInt("tipo_receta"));
					receta.setTipoComida(jReceta.getInt("tipo_comida"));
					receta.setCalificacionReceta(jReceta.getInt("calificacion"));
		        	
					if(!jReceta.optString("restaurante").isEmpty() && !jReceta.optString("restaurante").equals("null")) {
						Log.i("helper", "-> hay data ");		receta.setNombreRestaurante(jReceta.getString("restaurante"));
					} else {
						Log.i("helper", "-> no hay data2 ");	receta.setNombreRestaurante("");
					}
					
					/*if(!jReceta.optString("direccion").equals("null")) {
						Log.i("helper", "-> hay data ");		receta.setDireccionRestaurante(jReceta.getString("direccion"));
					} else {
						Log.i("helper", "-> no hay data2 ");	receta.setDireccionRestaurante("");
					}*/
					
					/*if(!jReceta.optString("telefono").equals("null")) {
						Log.i("helper", "-> hay data ");		receta.setTelefonoRestaurante(jReceta.getString("telefono"));
					} else {
						Log.i("helper", "-> no hay data2 ");	receta.setTelefonoRestaurante("");
					}
					
					if(!jReceta.optString("latitude").isEmpty()) {
						Log.i("helper", "-> * latitude: "+jReceta.getDouble("latitude"));
						receta.setLatitudeRestaurante(jReceta.getDouble("latitude"));
						receta.setLongitudeRestaurante(jReceta.getDouble("longitude"));
					} else{
						Log.i("helper", "-> **no hay coordenadas");
						receta.setLatitudeRestaurante(0.0);		receta.setLongitudeRestaurante(0.0);
					}
					
					if(!jReceta.optString("direccion").equals("null")) {
						Log.i("helper", "-> hay data ");		receta.setDireccionRestaurante(jReceta.getString("direccion"));
					} else {
						Log.i("helper", "-> no hay data2 ");	receta.setDireccionRestaurante("");
					}
					
					if(!jReceta.optString("direccion").equals("null")) {
						Log.i("helper", "-> hay data ");		receta.setDireccionRestaurante(jReceta.getString("direccion"));
					} else {
						Log.i("helper", "-> no hay data2 ");	receta.setDireccionRestaurante("");
					}
					
		        	jReceta.put("preparacion_receta", null);
		        	jReceta.put("descripcion_receta", "La ensalada d*/
					
					array.add(receta);
				}
			}
		} catch (JSONException e) {		
			e.printStackTrace();
		}
		
		return array;
	}
	
	public JSONObject getDatosReceta(String id) {
		JSONObject receta = new JSONObject();
		
		try {
			JSONArray jARecetas = new JSONArray(getJsonRecetas());
			for(int i=0; i<jARecetas.length(); i++) {
				JSONObject jOReceta = jARecetas.getJSONObject(i);
				if(jOReceta.getString("_id").equals(id)) {
					receta = jOReceta;
					break;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return receta;
	}
	
	public void removeJsonReceta(String id) {
		JSONArray jRecetas = new JSONArray();
		
		try {
			JSONArray json = new JSONArray(getJsonRecetas());
			for(int i=0; i<json.length(); i++) {
				if(!id.equals(json.getJSONObject(i).getString("_id"))) {
					jRecetas.put(json.getJSONObject(i));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		writeJsonRecetas(jRecetas.toString());
	}
	
	public void removeJsonRecetas() {
		if (file.exists()) {
			file.delete();
		}
	}

}
