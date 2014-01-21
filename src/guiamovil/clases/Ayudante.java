package guiamovil.clases;

import java.io.InputStream;
import java.net.URL;

import android.graphics.drawable.Drawable;


/**
 * @author Miguel
 *Clase que contiene metodos de ayuda.
 */
public class Ayudante {
	
	

	/**
	 * @param url: URL de la imagen PNG o JPG
	 * @return: Retorna un drawable que puede ser seteado en los ImageView y otros.
	 */
	public Drawable DrawabledesdeUrl(String url) throws Exception
    {
 		
		InputStream is = (InputStream) new URL(url).getContent();
		Drawable d = Drawable.createFromStream(is, "src name");
		return d;
    }
	
 	}
