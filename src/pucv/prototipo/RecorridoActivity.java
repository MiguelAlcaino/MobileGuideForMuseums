package pucv.prototipo;


import java.util.ArrayList;
import java.util.Iterator;
import guiamovil.Parsers.RecorridoParser;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.Toast;
import guiamovil.clases.Recorrido;


public class RecorridoActivity extends Activity {
	
	private ArrayList<Recorrido> ar_recorrido;
	public LinearLayout ll;
	private TextToSpeech mt;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//setContentView(R.layout.recorridoview);
		
		ll = new LinearLayout(this);
//		TextView tv = new TextView(this);
//		tv.setText("Hola");
//		ll.addView(tv);
		Task t1 = new Task();
		t1.execute(new Integer(1));
		
	}
	
	private void loadViews(){
		
//		Toast.makeText(RecorridoActivity.this, String.valueOf(ar_recorrido.size()), Toast.LENGTH_LONG).show();
//		
//		Iterator<Recorrido> it_recorridos = ar_recorrido.iterator();
//		
//		Button b1 = new Button(this);
//		Button b2 = new Button(this);
//		b1.setText("holanda");
//		ll.addView(b1);
//		
////		b1.setText(ar_recorrido.get(0).getNombre());
////		b2.setText(ar_recorrido.get(1).getNombre());
//		b1.setText("hola");
//		b2.setText("pinaola");
//		ll.addView(b1);
//		ll.addView(b2);
				Iterator<Recorrido> it = ar_recorrido.iterator();
		
		while(it.hasNext()){
			final Recorrido recorrido = it.next();
			
			Button b = new Button(this);
			b.setText(recorrido.getNombre());
			
			b.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					RecorridoParser r_parser = new RecorridoParser();
					r_parser.llenarOrden(recorrido.getId_destino());
					Intent intent = new Intent(RecorridoActivity.this, RecorriendoActivity.class);
					int id_sala = RecorridoParser.getOrden().get(1);
					intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
					intent.putExtra("id_sala", id_sala);
					intent.putExtra("num_orden", 1); // Se envia 1 porque en RecorriendoActivity se necesita cada vez que se llame de nuevo.
					startActivity(intent);
					
				}
			});
			
			ll.addView(b);
		}
		setContentView(ll);
	}
	
	private class Task extends AsyncTask<Integer, Void, ArrayList<Recorrido>>{

		private final ProgressDialog dialogoprogreso = new ProgressDialog(RecorridoActivity.this);
    	
    	protected void onPreExecute() {
			dialogoprogreso.setMessage("Cargando lista de recorridos");
			dialogoprogreso.setTitle("Espere un momento");
			dialogoprogreso.show();
		}
		
		@Override
		protected ArrayList<Recorrido> doInBackground(Integer... arg0) {
			// TODO Auto-generated method stub
			RecorridoParser recorrido_parser = new RecorridoParser();
//			Toast.makeText(RecorridoActivity.this, String.valueOf(recorrido_parser.getRecorridos().size()), Toast.LENGTH_SHORT).show();
			return recorrido_parser.getRecorridos();
//			return null;
		}
		
		protected void onPostExecute(ArrayList<Recorrido> r){
			ar_recorrido = r;
//			Toast.makeText(RecorridoActivity.this, String.valueOf(ar_recorrido.size()), Toast.LENGTH_SHORT).show();
			dialogoprogreso.dismiss();
			loadViews();
			
		}
		
	}
}
