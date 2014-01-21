package pucv.prototipo;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import guiamovil.Parsers.PanoramicaParser;
import guiamovil.Parsers.Parser;
import guiamovil.Parsers.RecorridoParser;
import guiamovil.clases.Coordenada;
import guiamovil.clases.Exposicion;
import guiamovil.clases.Panoramica;
import guiamovil.clases.Recorrido;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;


public class RecorriendoActivity extends Activity {

	private int id_sala;
	private int num_orden;
	private ArrayList<Panoramica> arrayPanoramicas;
	public View tabForm;
	private RelativeLayout rl;
	private String ruta_sonido;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		 Bundle recibido = getIntent().getExtras();
			if(recibido!=null){
				id_sala = recibido.getInt("id_sala");
				num_orden = recibido.getInt("num_orden");
			}
			
		rl = new RelativeLayout(RecorriendoActivity.this);
		Task t1 = new Task();
		t1.execute(new Integer(id_sala));
			
	}
	
	public void pintar(){
    	//setContentView(tabForm);
		setContentView(rl);
		//Recorrido.reproducirAudioRecorrido();
    }
	
	public void loadControles(){
		//Inicia reproduccion de Audio al inciiar la actividad
		
		
		int y_para_botones = 430;
		
		ImageView imagen_play = new ImageView(RecorriendoActivity.this);
		imagen_play.setImageResource(R.drawable.play_24x32);
		
		ImageView imagen_avanzar = new ImageView(RecorriendoActivity.this);
		imagen_avanzar.setImageResource(R.drawable.last_32x32);
		
		ImageView imagen_retroceder = new ImageView(RecorriendoActivity.this);
		imagen_retroceder.setImageResource(R.drawable.first_32x32);
		
		
		
		imagen_play.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Recorrido.getSonido_sala_recorrido().start();
				
//				Recorrido.reproducirAudioRecorrido();
			}
		});
		
		imagen_retroceder.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				finish();
				
				if(Recorrido.getSonido_sala_recorrido().isPlaying()){
					Recorrido.getSonido_sala_recorrido().stop();
				}
				
				Intent intent = new Intent(RecorriendoActivity.this, RecorriendoActivity.class);
				intent.putExtra("num_orden", num_orden-1);
				intent.putExtra("id_sala", RecorridoParser.getOrden().get(num_orden-1));
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY );
				startActivity(intent);
			}
		});
		
		imagen_avanzar.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(Recorrido.getSonido_sala_recorrido().isPlaying()){
					Recorrido.getSonido_sala_recorrido().stop();
				}
				
				Intent intent = new Intent(RecorriendoActivity.this, RecorriendoActivity.class);
				intent.putExtra("num_orden", num_orden+1);
				intent.putExtra("id_sala", RecorridoParser.getOrden().get(num_orden+1));
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY );
//				intent.putExtra("id_sala", num_orden+1);
				startActivity(intent);
			}
		});
		
		int size_orden = RecorridoParser.getOrden().size();
		
		if( num_orden-1 != 0){
			RelativeLayout.LayoutParams rlp1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rlp1.setMargins(300, y_para_botones, 0, 0);
			rl.addView(imagen_retroceder, rlp1);
			
//			Recorrido.pararAudioRecorrido();
		}		
		
		if(num_orden != size_orden){
			
			
			
			RelativeLayout.LayoutParams rlp2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rlp2.setMargins(400, y_para_botones, 0, 0);
			rl.addView(imagen_avanzar, rlp2);
			
//			Recorrido.pararAudioRecorrido();
		}
		
		RelativeLayout.LayoutParams rlp3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		rlp3.setMargins(350, y_para_botones, 0, 0);
		rl.addView(imagen_play, rlp3);
		
		
		
		
	}
    
    private ViewGroup crearTabForm() {

    	TabHost tabHost = new TabHost(this);
        tabHost.setLayoutParams(
        		new LinearLayout.LayoutParams(
                        LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        
        // the tabhost needs a tabwidget, that is a container for the visible tabs
        TabWidget tabWidget = new TabWidget(this);
        tabWidget.setId(android.R.id.tabs);
        tabHost.addView(tabWidget, new LinearLayout.LayoutParams(
                  LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT)); 
        
        // the tabhost needs a frame layout for the views associated with each visible tab
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setId(android.R.id.tabcontent);
        frameLayout.setPadding(0, 65, 0, 0);
        tabHost.addView(frameLayout, new LinearLayout.LayoutParams(
                  LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); 
        
        // setup must be called if you are not initialising the tabhost from XML
        tabHost.setup();
        
        Iterator<Panoramica> iter = arrayPanoramicas.iterator();
        
        //Iterator<Integer> iter = arrayIds.iterator();
      	while(iter.hasNext()){
      		final Panoramica pan = iter.next();
//      		Intent intent = new Intent().setClass(this, PanoramicaTab.class);
//      		intent.putExtra("id_panoramica", pan.getId() );
        	TabHost.TabSpec ts = tabHost.newTabSpec("tag"+pan.getId());
            ts.setIndicator(pan.getDescripcion());
            //ts.setContent(intent);
            ts.setContent(new TabHost.TabContentFactory(){
                 public View createTabContent(String tag)
                 {  
                	
					RelativeLayout relative = new RelativeLayout(RecorriendoActivity.this);
					//relative.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
					ImageView img = new ImageView(RecorriendoActivity.this);
					img.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
					img.setImageDrawable(pan.getImagen());
					relative.addView(img);
					
					
					PanoramicaParser paparser = new PanoramicaParser();
					ArrayList<Coordenada> ar_coordenada = paparser.getCoordenadas(pan.getId());
					Iterator<Coordenada> it_coordenada = ar_coordenada.iterator();
					
					
					
					while(it_coordenada.hasNext()){
						//Button b = new Button(RecorriendoActivity.this);
						final Coordenada coordenada = it_coordenada.next();
						ImageView b = new ImageView(RecorriendoActivity.this);
						b.setImageResource(R.drawable.cd_16x16);
						b.setOnClickListener(new View.OnClickListener() {
							
							
							public void onClick(View v) {
								
//								Recorrido.pausarAudioRecorrido();
								
								Intent intent = new Intent(RecorriendoActivity.this, ExposicionActivity.class);
								intent.putExtra("id_exposicion", coordenada.getIdDestino());
								startActivity(intent);
							}
						});
						RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
						lp1.setMargins(coordenada.getX(), coordenada.getY(), 0, 0);
						
						relative.addView(b, lp1);
					}
					
					return relative;
					
                 }         
            });
            tabHost.addTab(ts);
            
        }
        
        //return tabHost;
      	
      	rl.addView(tabHost);
      	return rl;
      	//return new LinearLayout(this);
	}

    private class Task extends AsyncTask<Integer, Void, ArrayList<Panoramica>>{

    	private final ProgressDialog dialogoprogreso = new ProgressDialog(RecorriendoActivity.this);
    	
    	protected void onPreExecute() {
			this.dialogoprogreso.setMessage("Cargando panoramicas...");
			this.dialogoprogreso.setTitle("Espere un momento");
			this.dialogoprogreso.show();
		}
    	
		@Override    	
		protected ArrayList<Panoramica>  doInBackground(Integer... sala) {
			// TODO Auto-generated method stub
			PanoramicaParser panoramicaParser = new PanoramicaParser();
			
			RecorridoParser recorridoParser = new RecorridoParser();
			ruta_sonido = recorridoParser.getRutaSonidoRecorrido(sala[0].intValue()).get(0);
			//arrayPanoramicas = panoramicaParser.getPanoramicas(1);
			//arrayIds = panoramicaParser.getIdsPanoramicas(sala[0].intValue());
			
			Recorrido.setSonido_sala_recorrido(new MediaPlayer());
			Recorrido.getSonido_sala_recorrido().setAudioStreamType(AudioManager.STREAM_MUSIC);
			try {
				Recorrido.getSonido_sala_recorrido().setDataSource(Parser.getPublicPath()+"/uploads/sonidos/"+ruta_sonido);
				Recorrido.getSonido_sala_recorrido().prepare();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
//			
//			MediaPlayer mp = new MediaPlayer();
//			try {
//				mp.setDataSource("http://"+Parser.getIp()+"/"+ruta_sonido);
//				mp.prepare();
//			} catch (IllegalArgumentException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IllegalStateException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			Recorrido.setSonido_sala_recorrido(mp);
			
			return panoramicaParser.getPanoramicas(sala[0].intValue());
		}
		
		protected void onPostExecute(ArrayList<Panoramica> p){
			dialogoprogreso.dismiss();
			arrayPanoramicas = p;
			tabForm = crearTabForm();
			loadControles();
			pintar();
		}
    }
	
	
	
}
