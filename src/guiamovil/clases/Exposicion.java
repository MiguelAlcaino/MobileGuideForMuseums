package guiamovil.clases;

import android.media.MediaPlayer;

public class Exposicion {
	private int id;
	private String nombre;
	private String descripcion;
	private int codigo;
	private static MediaPlayer sonido;
	
	public static MediaPlayer getSonido() {
		return sonido;
	}
	public static void setSonido(MediaPlayer sonido) {
		Exposicion.sonido = sonido;
	}
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
}
