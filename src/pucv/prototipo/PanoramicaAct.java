package pucv.prototipo;

import java.util.ArrayList;
import java.util.Iterator;



import guiamovil.Parsers.PanoramicaParser;

import guiamovil.clases.Coordenada;
import guiamovil.clases.Panoramica;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;


import android.widget.LinearLayout.LayoutParams;


public class PanoramicaAct extends Activity {

	protected ArrayList<Panoramica> arrayPanoramicas;
	protected ArrayList<Integer> arrayIds;
	public View tabForm;
	protected int id_sala;
	/** Called when the activity is first created. */
	
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
		Bundle recibido = getIntent().getExtras();
		if(recibido!=null){
			id_sala = recibido.getInt("id_sala");
		}
		
        Task t1 = new Task();
        t1.execute(Integer.valueOf(id_sala));
        
    }
    
    public void pintar(){
    	setContentView(tabForm);
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
                	
					RelativeLayout relative = new RelativeLayout(PanoramicaAct.this);
					//relative.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
					ImageView img = new ImageView(PanoramicaAct.this);
					img.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
					img.setImageDrawable(pan.getImagen());
					relative.addView(img);
					
					
					PanoramicaParser paparser = new PanoramicaParser();
					ArrayList<Coordenada> ar_coordenada = paparser.getCoordenadas(pan.getId());
					Iterator<Coordenada> it_coordenada = ar_coordenada.iterator();
					
					while(it_coordenada.hasNext()){
						//Button b = new Button(PanoramicaAct.this);
						final Coordenada coordenada = it_coordenada.next();
						ImageView b = new ImageView(PanoramicaAct.this);
						b.setImageResource(R.drawable.cd_16x16);
						b.setOnClickListener(new View.OnClickListener() {
							
							
							public void onClick(View v) {
								Intent intent = new Intent(PanoramicaAct.this, ExposicionActivity.class);
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
        
        return tabHost;
      	//return new LinearLayout(this);
	}

    private class Task extends AsyncTask<Integer, Void, ArrayList<Panoramica>>{

    	private final ProgressDialog dialogoprogreso = new ProgressDialog(PanoramicaAct.this);
    	
    	protected void onPreExecute() {
			this.dialogoprogreso.setMessage("Cargando panoramicas...");
			this.dialogoprogreso.setTitle("Espere un momento");
			this.dialogoprogreso.show();
		}
    	
		@Override    	
		protected ArrayList<Panoramica>  doInBackground(Integer... sala) {
			// TODO Auto-generated method stub
			PanoramicaParser panoramicaParser = new PanoramicaParser();
			//arrayPanoramicas = panoramicaParser.getPanoramicas(1);
			//arrayIds = panoramicaParser.getIdsPanoramicas(sala[0].intValue());
			return panoramicaParser.getPanoramicas(sala[0].intValue());
		}
		
		protected void onPostExecute(ArrayList<Panoramica> p){
			dialogoprogreso.dismiss();
			arrayPanoramicas = p;
//			Toast.makeText(PanoramicaAct.this, String.valueOf(arrayPanoramicas.size()), Toast.LENGTH_LONG).show();
			tabForm = crearTabForm();
			pintar();
		}
    }
    
//    private class TaskCoordenadas extends AsyncTask<Integer, Void, ArrayList<Coordenada>>{
//
//		@Override
//		protected ArrayList<Coordenada> doInBackground(Integer... params) {
//			// TODO Auto-generated method stub
//			PanoramicaParser paparser = new PanoramicaParser();
//			
//			return paparser.getCoordenadas(params[0].intValue());
//		}
//		protected void onPostExecute(ArrayList<Coordenada> c){
//			
//		}
//    	
//    }
    
   /* private class tab extends Activity{
    	@Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
    		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            
            Task t1 = new Task();
            t1.execute(Integer.valueOf(1));
        }
    	
        private class Task extends AsyncTask<Integer, Void, ArrayList<Panoramica>>{

        	private final ProgressDialog dialogoprogreso = new ProgressDialog(PanoramicaAct.this);
        	
        	protected void onPreExecute() {
    			this.dialogoprogreso.setMessage("Cargando panoramicas...");
    			this.dialogoprogreso.setTitle("Espere un momento");
    			this.dialogoprogreso.show();
    		}
        	
    		@Override    	
    		protected ArrayList<Panoramica>  doInBackground(Integer... piso) {
    			// TODO Auto-generated method stub
    			PanoramicaParser panoramicaParser = new PanoramicaParser();
    			arrayPanoramicas = panoramicaParser.getPanoramicas(1);
    			return panoramicaParser.getPanoramicas(1);
    		}
    		
    		protected void onPostExecute(ArrayList<Panoramica> p){
    			dialogoprogreso.dismiss();
    			Toast.makeText(PanoramicaAct.this, String.valueOf(arrayPanoramicas.size()), Toast.LENGTH_LONG).show();
    			tabForm = crearTabForm();
    			pintar();
    		}
        }
    }*/
}