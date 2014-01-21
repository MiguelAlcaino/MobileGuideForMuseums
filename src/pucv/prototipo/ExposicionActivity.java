package pucv.prototipo;
import guiamovil.Parsers.ExposicionParser;
import guiamovil.Parsers.Parser;
import guiamovil.clases.Exposicion;
import guiamovil.clases.ImageAdapter;
import java.io.IOException;
import java.util.ArrayList;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class ExposicionActivity extends Activity{
	
	protected ArrayList<Drawable> ar_imagenes;
	protected Exposicion exposicion;
	protected int id_exposicion;
	protected String ruta_sonido;
	protected Gallery ga;
	protected ImageView iv_imagen;
	protected TextView text_descripcion;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.exposicionview);
        
        Bundle recibido = getIntent().getExtras();
		if(recibido!=null){
			id_exposicion = recibido.getInt("id_exposicion");
		}
		
		Task t1 = new Task();
		t1.execute(new Integer(id_exposicion));
    }
	
	private void loadViews(){
		text_descripcion =(TextView) findViewById(R.id.textExposicionDescripcion);
		text_descripcion.setText(exposicion.getDescripcion());
		
		iv_imagen = (ImageView) findViewById(R.id.imagenExposicionImagen);
		iv_imagen.setLayoutParams(new LayoutParams(320, 240));
		iv_imagen.setScaleType(ImageView.ScaleType.FIT_XY);
		iv_imagen.setImageDrawable(ar_imagenes.get(0));
		
		ga = (Gallery) findViewById(R.id.gallery1);
		ga.setAdapter(new ImageAdapter(this, ar_imagenes));
		ga.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				iv_imagen.setImageDrawable(ar_imagenes.get(arg2));
			}

		});
		//Toast.makeText(this, String.valueOf(ar_imagenes.size()), Toast.LENGTH_LONG).show();
		
//		Button volver_home = (Button) findViewById(R.id.volverHome);
		
//		volver_home.setOnClickListener(new View.OnClickListener() {
//			
//			public void onClick(View v) {
//				// 
//				Intent intent = new Intent(ExposicionActivity.this, GuiaActivity.class);
//				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivity(intent);
//			}
//		});
		
		
        
        
        ImageButton boton_play = (ImageButton) findViewById(R.id.botonPlaySonidoExposicion);
//        ImageButton boton_stop = (ImageButton) findViewById(R.id.botonStopSonidoExposicion);
        ImageButton boton_pause = (ImageButton) findViewById(R.id.botonPauseSonidoExposicion);
        
        if(ruta_sonido==""){
        	boton_play.setVisibility(View.INVISIBLE);
        	boton_pause.setVisibility(View.INVISIBLE);
        	Toast.makeText(ExposicionActivity.this, "Esta exposición no tiene un sonido asociado.", Toast.LENGTH_LONG).show();
        }else{
        	boton_play.setVisibility(View.VISIBLE);
        	boton_pause.setVisibility(View.VISIBLE);
        }
        
        boton_play.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Exposicion.getSonido().start();
			}
		});
        
		
//        boton_stop.setOnClickListener(new View.OnClickListener() {
//			
//			
//			public void onClick(View v) {
//				Exposicion.getSonido().stop();
//				
//			}
//		});
        
        boton_pause.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {
				Exposicion.getSonido().pause();
				
			}
		});
	}
	
	 private class Task extends AsyncTask<Integer, Void, Exposicion>{

	    	private final ProgressDialog dialogoprogreso = new ProgressDialog(ExposicionActivity.this);
	    	
	    	protected void onPreExecute() {
				this.dialogoprogreso.setMessage("Cargando Exposicion");
				this.dialogoprogreso.setTitle("Espere un momento");
				this.dialogoprogreso.show();
			}
	    	
			@Override
			protected Exposicion  doInBackground(Integer... id_exposicion) {
				ExposicionParser expoparser = new ExposicionParser();
				ar_imagenes = expoparser.getImagenesExposicion(id_exposicion[0].intValue());
				ruta_sonido = expoparser.getRutaSonidoExposicion(id_exposicion[0].intValue());
				
				Exposicion.setSonido(new MediaPlayer());
				Exposicion.getSonido().setAudioStreamType(AudioManager.STREAM_MUSIC);
				try {
					Exposicion.getSonido().setDataSource(Parser.getPublicPath()+"/uploads/sonidos/"+ruta_sonido);
					Exposicion.getSonido().prepare();
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
				
				return expoparser.getExposicion(id_exposicion[0].intValue());
			}
			
			protected void onPostExecute(Exposicion expo){
				exposicion = expo;
				
				this.dialogoprogreso.dismiss();
				loadViews();
			}
	    	
	    }
	
}
