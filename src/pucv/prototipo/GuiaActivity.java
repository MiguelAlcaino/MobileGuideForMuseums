package pucv.prototipo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class GuiaActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.main);
        
        Button boton_mostrar_piso = (Button) findViewById(R.id.boton_ver_piso);
        boton_mostrar_piso.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), PisoActivity.class);
				startActivity(intent);
			}
		});

        Button b_ingresar_codigo = (Button) findViewById(R.id.boton_ingresar_codigo);
        
        b_ingresar_codigo.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),IngresarCodigoActivity.class);
				startActivity(intent);
			}
		});
        
        Button b_realizr_recorrido = (Button) findViewById(R.id.boton_realizar_recorrido);
        
        b_realizr_recorrido.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), RecorridoActivity.class);
				startActivity(intent);
			}
		});
        
        
    }
}