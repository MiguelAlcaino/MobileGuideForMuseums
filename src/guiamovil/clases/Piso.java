package guiamovil.clases;

import guiamovil.Parsers.SalaParser;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

public class Piso implements Comparable<Piso> {
	
	private Drawable imagen_piso;
	private int numero_piso; // clave primaria
	private ArrayList<Coordenada> ar_coordenadas;
	
	
	public ArrayList<Coordenada> getAr_coordenadas() {
		return ar_coordenadas;
	}
	public void setAr_coordenadas(ArrayList<Coordenada> ar_coordenadas) {
		this.ar_coordenadas = ar_coordenadas;
	}
	
	public Drawable getImagen_piso() {
		return imagen_piso;
	}
	public void setImagen_piso(Drawable imagen_piso) {
		this.imagen_piso = imagen_piso;
	}
	public int getNumero_piso() {
		return numero_piso;
	}
	public void setNumero_piso(int numero_piso) {
		this.numero_piso = numero_piso;
	}
	
	public void llenarArCoordenadas(){
		SalaParser sala_parser = new SalaParser();
		ar_coordenadas = sala_parser.getCoordenadas(numero_piso);
	}
	public int compareTo(Piso o) {
		// TODO Auto-generated method stub
		
		if(this.numero_piso < o.getNumero_piso())
			return -1;
		else if(this.numero_piso == o.getNumero_piso())
				return 0;
			else
				return 1;
		
	}
	
	
}
