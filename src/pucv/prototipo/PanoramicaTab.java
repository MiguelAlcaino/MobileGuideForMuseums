package pucv.prototipo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class PanoramicaTab extends Activity {
	protected int id_panoramica;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.panoramicatab);
        
        Bundle recibido = getIntent().getExtras();
		if(recibido!=null){
			id_panoramica = recibido.getInt("id_panoramica");
		}
		TextView tv = (TextView) findViewById(R.id.TextView01_tab);
		tv.setText(String.valueOf(id_panoramica));
    }
}
