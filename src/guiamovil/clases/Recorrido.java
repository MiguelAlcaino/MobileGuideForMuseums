package guiamovil.clases;

import android.media.MediaPlayer;

public class Recorrido {
	private String nombre;
	private int id_destino;
	
	private static MediaPlayer sonido_sala_recorrido;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId_destino() {
		return id_destino;
	}
	public void setId_destino(int id_destino) {
		this.id_destino = id_destino;
	}
	public static MediaPlayer getSonido_sala_recorrido() {
		return sonido_sala_recorrido;
	}
	public static void setSonido_sala_recorrido(MediaPlayer sonido_sala_recorrido) {
		Recorrido.sonido_sala_recorrido = sonido_sala_recorrido;
	}
	
	public static void reproducirAudioRecorrido(){
		Recorrido.getSonido_sala_recorrido().start();
	}
	
	public static void pausarAudioRecorrido(){
		Recorrido.getSonido_sala_recorrido().pause();
	}
	
	public static void pararAudioRecorrido(){
		Recorrido.getSonido_sala_recorrido().stop();
	}
	
}
