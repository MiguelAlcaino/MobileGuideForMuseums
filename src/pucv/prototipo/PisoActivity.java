package pucv.prototipo;

import guiamovil.Parsers.PisoParser;

import guiamovil.clases.Coordenada;
import guiamovil.clases.Piso;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Iterator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

//import android.widget.Toast;

public class PisoActivity extends Activity {
	protected ArrayList<Coordenada> ar_coordenadas;
	private ArrayList<Piso> ar_pisos;
	private ImageView imagen_piso;
	protected int piso;
	protected RelativeLayout rl, rl_activity;
	//protected ArrayList<Panoramica> ar_panoramicas;
	protected TextView tv;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.pisoview);
        rl = (RelativeLayout) findViewById(R.id.RL_PisoView);
        rl_activity = (RelativeLayout) findViewById(R.id.RL_PisoActivity);
//         imagen_piso = (ImageView)findViewById(R.id.ImagenPiso);
//        imagen_piso.setImageResource(R.drawable.primer_piso);
        
//        rl.removeAllViews();
        
//        piso = 1;
        
        Integer piso_integer = new Integer(1);
        
        Task t1 = new Task();
        t1.execute(piso_integer);
    }
    
    private void borrarPantalla(RelativeLayout relative){
//    	relative.removeAllViews();
    	relative.removeAllViewsInLayout();
    }
    
    private void pintarNumerosPisos(){
    	
    	int y = 415;
    	int incremento_x = 50;
    	for(int i=0;i<ar_pisos.size();i++){
    		final int j = i;
    		TextView tv = new TextView(this);
    		tv.setText(String.valueOf(i+1));
    		tv.setTextSize(50);
    		tv.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					borrarPantalla(rl);
					loadViews(j);
					
				}
			});
    		RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    		lp3.setMargins(300+(incremento_x*(i+1)), y, 0, 0);
    		rl_activity.addView(tv, lp3);
    	}
    }
    
    public void loadViews(int id_piso) {
		Piso piso = ar_pisos.get(id_piso);
		
		imagen_piso = new ImageView(this);
		imagen_piso.setImageDrawable(piso.getImagen_piso());
		RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    	lp4.setMargins(0, 0, 0, 0);
    	rl.addView(imagen_piso, lp4);
		
    	Iterator<Coordenada> it = piso.getAr_coordenadas().iterator();
    	
    	while(it.hasNext()){
    		final Coordenada boton_coordenado = it.next();
    		
    		TextView tv = new TextView(this);
    		tv.setText(boton_coordenado.getTexto());
    		
    		ImageView imagen_cordenada = new ImageView(this);
    		imagen_cordenada.setImageResource(R.drawable.cd_16x16);
    		
    		imagen_cordenada.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					Intent intent = new Intent(PisoActivity.this, PanoramicaAct.class); // cambiar nosmbre a la clase correspondiente
					intent.putExtra("id_sala", boton_coordenado.getIdDestino());
					startActivity(intent);
				}
			});
    		LayoutParams lp1 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp1.setMargins(boton_coordenado.getX(), boton_coordenado.getY(), 0, 0);
			rl.addView(imagen_cordenada, lp1);
			
			//para prueba de id destino
			LayoutParams lp2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp2.setMargins(boton_coordenado.getX()-30, boton_coordenado.getY()+5, 0, 0);
			rl.addView(tv, lp2);
			//Toast.makeText(this, String.valueOf(ar_panoramicas.size()), Toast.LENGTH_SHORT).show();
			
    	}
    	
//    	Button b_limpiar = new Button(this);
//    	b_limpiar.setText("Limpia la pantalla");
//    	b_limpiar.setOnClickListener(new View.OnClickListener() {
//			
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				borrarPantalla(rl);
//				loadViews(1);
//			}
//		});
    	
//    	RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//    	lp3.setMargins(400, 400, 0, 0);
//    	rl_activity.addView(b_limpiar, lp3);
		
	}
    
    private class Task extends AsyncTask<Integer, Void, ArrayList<Piso>>{

    	private final ProgressDialog dialogoprogreso = new ProgressDialog(PisoActivity.this);
    	
    	protected void onPreExecute() {
			this.dialogoprogreso.setMessage("Cargando coordenadas...");
			this.dialogoprogreso.setTitle("Espere un momento");
			this.dialogoprogreso.show();
		}
    	
		@Override
		protected ArrayList<Piso>  doInBackground(Integer... piso) {
			// TODO Auto-generated method stub
			PisoParser piso_parser = new PisoParser();
			//SalaParser sala_parser = new SalaParser();
			//PanoramicaParser p_parser = new PanoramicaParser();
			//ar_panoramicas = p_parser.getPanoramicas(1);
			ar_pisos = piso_parser.getPisos();
			for(int i=0;i<ar_pisos.size();i++){
				ar_pisos.get(i).llenarArCoordenadas();
			}
			
			return ar_pisos;
		}
		
		protected void onPostExecute(ArrayList<Piso> pisos){
			ar_pisos = pisos;
			Collections.sort(ar_pisos);
			this.dialogoprogreso.dismiss();
//			Toast.makeText(PisoActivity.this, "Largo ArrayCoordenadas del piso 1: "+ar_pisos.get(0).getAr_coordenadas().size(), Toast.LENGTH_LONG).show();
			loadViews(0); // Carga de piso 1 por defecto
			pintarNumerosPisos();
		}
    	
    }
    
    
}
