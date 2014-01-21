package guiamovil.clases;

import android.graphics.drawable.Drawable;

public class Panoramica {
	private int id;
	private String descripcion;
	private Drawable imagen;
	private Drawable imagen_miniatura;
	private int numero_orden;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Drawable getImagen() {
		return imagen;
	}
	public void setImagen(Drawable imagen) {
		this.imagen = imagen;
	}
	public Drawable getImagenMiniatura() {
		return imagen_miniatura;
	}
	public void setImagenMiniatura(Drawable imagen_miniatura) {
		this.imagen_miniatura = imagen_miniatura;
	}
	public int getNumeroOrden() {
		return numero_orden;
	}
	public void setNumeroOrden(int numero_orden) {
		this.numero_orden = numero_orden;
	}
	
	
}
