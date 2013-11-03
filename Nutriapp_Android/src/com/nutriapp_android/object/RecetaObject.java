package com.nutriapp_android.object;

public class RecetaObject {
	private String id, titulo, calorias, sss, fff;
	int tipo;
	
	public RecetaObject() {
		super();
	}
	
	public RecetaObject(String id, String titulo, int tipo, String calorias, String sss, String fff) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.tipo = tipo;
		this.calorias = calorias;
		this.sss = sss;
		this.fff = fff;
	}
	
	public String getRecetaId() {
		return id;
	}
	public void setRecetaId(String id) {
		this.id = id;
	}
	
	public int getRecetaTipo() {
		return tipo;
	}
	public void setRecetaTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public String getRecetaTitulo() {
		return titulo;
	}
	public void setRecetaTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getRecetacalorias() {
		return calorias;
	}
	public void setRecetacalorias(String calorias) {
		this.calorias = calorias;
	}
	
	public String getRecetasss() {
		return sss;
	}
	public void setRecetasss(String sss) {
		this.sss = sss;
	}
	
	public String getRecetafff() {
		return fff;
	}
	public void setRecetafff(String fff) {
		this.fff = fff;
	}

}
