package com.nutriapp_android.object;

public class RecetaObject {
	String id, titulo, imagen, precio, calorias, sodio, fibra;
	int tipo_comida, tipo_receta, calificacion;
	
	String nombre_restaurante; //, dir_restaurante;
	
	public RecetaObject() {
		super();
	}
	
	//public RecetaObject(String id, String titulo, String url, String precio, String calorias, String sodio, String fibra, int tipo_receta, int tipo_comida, int calificacion, String nombre_restaurante, String dir_restaurante) {
	public RecetaObject(String id, String titulo, String url, String precio, String calorias, String sodio, String fibra, int tipo_receta, int tipo_comida, 
			int calificacion, String nombre_restaurante) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.imagen = url;
		this.precio = precio;
		this.calorias = calorias;
		this.sodio = sodio;
		this.fibra = fibra;
		this.tipo_receta = tipo_receta;
		this.tipo_comida = tipo_comida;
		this.calificacion = calificacion;
		this.nombre_restaurante = nombre_restaurante;
		//this.dir_restaurante = dir_restaurante;
	}
	
	public String getIdReceta() {
		return id;
	}
	public void setIdReceta(String id) {
		this.id = id;
	}
	
	public String getTituloReceta() {
		return titulo;
	}
	public void setTituloReceta(String titulo) {
		this.titulo = titulo;
	}
	
	public String getURLImagenReceta() {
		return imagen;
	}
	public void setURLImagenReceta(String urlImagen) {
		this.imagen = urlImagen;
	}
	
	public String getPrecioReceta() {
		return precio;
	}
	public void setPrecioReceta(String precio) {
		this.precio = precio;
	}
	
	public String getCaloriasReceta() {
		return calorias;
	}
	public void setCaloriasReceta(String calorias) {
		this.calorias = calorias;
	}
	
	public String getSodioReceta() {
		return sodio;
	}
	public void setSodioReceta(String sodio) {
		this.sodio = sodio;
	}
	
	public String getFibraReceta() {
		return fibra;
	}
	public void setFibraReceta(String fibra) {
		this.fibra = fibra;
	}
	
	public int getTipoReceta() {
		return tipo_receta;
	}
	public void setTipoReceta(int tipo_receta) {
		this.tipo_receta = tipo_receta;
	}
	
	public int getTipoComida() {
		return tipo_comida;
	}
	public void setTipoComida(int tipo_comida) {
		this.tipo_comida = tipo_comida;
	}
	
	public int getCalificacionReceta() {
		return calificacion;
	}
	public void setCalificacionReceta(int calificacion) {
		this.calificacion = calificacion;
	}
	
	public String getNombreRestaurante() {
		return nombre_restaurante;
	}
	public void setNombreRestaurante(String nombre_restaurante) {
		this.nombre_restaurante = nombre_restaurante;
	}
	
	/*public String getDireccionRestaurante() {
		return dir_restaurante;
	}
	public void setDireccionRestaurante(String direccion) {
		this.dir_restaurante = direccion;
	}*/

}
