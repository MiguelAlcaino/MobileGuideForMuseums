package guiamovil.clases;

import android.graphics.drawable.Drawable;

public class Sala {
	
	private int id;
	private String nombre;
	private int sala_numero;
	private int numero_panoramicas;
	private Drawable miniatura;
	private int num_panoramica_principal;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getSala_numero() {
		return sala_numero;
	}
	public void setSalaNumero(int sala_numero) {
		this.sala_numero = sala_numero;
	}
	public int getNumeroPanoramicas() {
		return numero_panoramicas;
	}
	public void setNumeroPanoramicas(int numero_panoramicas) {
		this.numero_panoramicas = numero_panoramicas;
	}
	public Drawable getMiniatura() {
		return miniatura;
	}
	public void setMiniatura(Drawable miniatura) {
		this.miniatura = miniatura;
	}
	public int getNumPanoramicaPrincipal() {
		return num_panoramica_principal;
	}
	public void setNumPanoramicaPrincipal(int num_panoramica_principal) {
		this.num_panoramica_principal = num_panoramica_principal;
	}
	
	
}
