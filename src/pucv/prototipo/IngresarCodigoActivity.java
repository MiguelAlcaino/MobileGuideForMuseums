package pucv.prototipo;

import guiamovil.Parsers.ExposicionParser;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IngresarCodigoActivity extends Activity {
	protected int codigo;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.ingresarcodigoview);
        
        Button boton = (Button) findViewById(R.id.boton_ingresar_codigo_activity);
        final EditText text = (EditText) findViewById(R.id.edittext_ingresar_codigo);
        
        //
        
        boton.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {
				codigo = Integer.parseInt(text.getText().toString());
//				Toast.makeText(v.getContext(), String.valueOf(codigo), Toast.LENGTH_LONG).show();
				ExposicionParser expoparser = new ExposicionParser();
				if(expoparser.existeExposicion(codigo)){
//					Toast.makeText(v.getContext(), "Hola", Toast.LENGTH_LONG).show();
					int id_exposicion = expoparser.getIdbyCodigo(codigo);
					Intent intent = new Intent(v.getContext(), ExposicionActivity.class);
					intent.putExtra("id_exposicion", id_exposicion);
					startActivity(intent);
				}else{
					Toast.makeText(v.getContext(), "El código no existe o la exposición esta descativada. Vuelva a intentarlo.", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
