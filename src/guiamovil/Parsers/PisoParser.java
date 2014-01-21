package guiamovil.Parsers;

import guiamovil.clases.Ayudante;
import guiamovil.clases.Piso;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PisoParser extends Parser {

	private String recurso = "pisoes";
	private String nodo  ="piso";
	
	public ArrayList<Piso> getPisos(){
		
		ArrayList<Piso> ar_pisos = new ArrayList<Piso>();
		
		try {
			NodeList nl = listaNodos(recurso, nodo);
			
			for(int i=0; i<nl.getLength();i++){
				Piso piso = new Piso();
				
				Node nodo = nl.item(i);
				Element elemento = (Element) nodo;
				
				NodeList numeroPisoLista =  elemento.getElementsByTagName("numeroPiso");
				Element numeroPisoElement = (Element) numeroPisoLista.item(0);
				numeroPisoLista = numeroPisoElement.getChildNodes();
				piso.setNumero_piso(Integer.parseInt(((Node) numeroPisoLista.item(0)).getNodeValue()));
				
				NodeList rutaImagenLista =  elemento.getElementsByTagName("rutaImagen");
				Element rutaImagenElement = (Element) rutaImagenLista.item(0);
				rutaImagenLista = rutaImagenElement.getChildNodes();
				String ruta_imagen = ((Node) rutaImagenLista.item(0)).getNodeValue().toString();
				Ayudante ay = new Ayudante();
				piso.setImagen_piso(ay.DrawabledesdeUrl(Parser.getPublicPath()+"/uploads/imagenes/"+ruta_imagen));
				
				ar_pisos.add(piso);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ar_pisos;
	}
}
