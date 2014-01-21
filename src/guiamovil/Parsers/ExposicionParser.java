package guiamovil.Parsers;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.graphics.drawable.Drawable;

import guiamovil.clases.Ayudante;
import guiamovil.clases.Exposicion;

public class ExposicionParser extends Parser {

	private String recurso = "exposicions";
	private String nodo = "exposicion";
	
	public boolean existeExposicion(int codigo_expo){
		boolean retorno = false;
		try {
			NodeList nl = listaNodos(recurso, nodo, "SELECT%20e%20FROM%20Exposicion%20e%20where%20e.codigo%20%3D%20"+codigo_expo);
			if(nl.getLength()>0){
				for(int i=0;i<nl.getLength();i++){
					Node nodo = nl.item(i);
					Element elemento = (Element) nodo;
					
					NodeList idLista = elemento.getElementsByTagName("estado");
					Element idElement = (Element) idLista.item(0);
					idLista = idElement.getChildNodes();
					if(Boolean.parseBoolean(((Node) idLista.item(0)).getNodeValue().toString() ) ){
						return true;
					}else{
						return false;
					}
				}
				retorno = true;
			}else{
				retorno =  false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}
	
	public int getIdbyCodigo(int codigo_expo){
		int id=1;
		try {
			NodeList nl = listaNodos(recurso, nodo, "SELECT%20e%20FROM%20Exposicion%20e%20where%20e.codigo%20%3D%20"+codigo_expo);
			for(int i=0;i<nl.getLength();i++){
				Node nodo = nl.item(i);
				Element elemento = (Element) nodo;
				
				NodeList idLista = elemento.getElementsByTagName("id");
				Element idElement = (Element) idLista.item(0);
				idLista = idElement.getChildNodes();
				id = Integer.parseInt(((Node) idLista.item(0)).getNodeValue());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public Exposicion getExposicion(int id_exposicion){
		Exposicion exposicion = new Exposicion();
		
		try {
			Element elemento = Nodo(recurso, nodo, id_exposicion);
			
			NodeList idList = elemento.getElementsByTagName("id");
			Element idElement = (Element) idList.item(0);
			idList = idElement.getChildNodes();
			exposicion.setId((Integer.parseInt(((Node) idList.item(0)).getNodeValue())));
			
			NodeList codigoList = elemento.getElementsByTagName("codigo");
			Element codigoElement = (Element) codigoList.item(0);
			codigoList = codigoElement.getChildNodes();
			exposicion.setCodigo((Integer.parseInt(((Node) codigoList.item(0)).getNodeValue())));
			
			NodeList nombreList = elemento.getElementsByTagName("nombre");
			Element nombreElement = (Element) nombreList.item(0);
			nombreList = nombreElement.getChildNodes();
			exposicion.setNombre(((Node) nombreList.item(0)).getNodeValue().toString());
			
			NodeList descripcionList = elemento.getElementsByTagName("descripcion");
			Element descripcionElement = (Element) descripcionList.item(0);
			descripcionList = descripcionElement.getChildNodes();
			exposicion.setDescripcion(((Node) descripcionList.item(0)).getNodeValue().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return exposicion;
		
		
	}
	
	public ArrayList<Drawable> getImagenesExposicion(int id_exposicion){
		
		ArrayList<Drawable> imagenes = new ArrayList<Drawable>();
		try {
			NodeList nl = listaNodos(recurso, id_exposicion, "exposicionImagenCollection", "exposicionImagen");
			for(int i=0;i<nl.getLength();i++){
				Node nodo = nl.item(i);
				Element elemento = (Element) nodo;
				
				NodeList rutaLista = elemento.getElementsByTagName("rutaImagen");
				Element rutaElement = (Element) rutaLista.item(0);
				rutaLista = rutaElement.getChildNodes();
				String ruta = ((Node) rutaLista.item(0)).getNodeValue().toString();
				Ayudante ay = new Ayudante();
				Drawable dr = ay.DrawabledesdeUrl(Parser.getPublicPath()+"/uploads/imagenes/"+ruta);
				
				imagenes.add(dr);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imagenes;
	}
	
	public String getRutaSonidoExposicion(int id_exposicion){
		String ruta="";
		
		try {
			NodeList nl = listaNodos("exposicions", id_exposicion, "exposicionSonidoCollection", "exposicionSonido");
			for(int i=0;i<nl.getLength();i++){
				Node nodo = nl.item(i);
				Element elemento = (Element) nodo;
				
				NodeList rutaSonidoLista = elemento.getElementsByTagName("rutaSonido");
				Element rutaSonidoElement = (Element) rutaSonidoLista.item(0);
				rutaSonidoLista = rutaSonidoElement.getChildNodes();
				ruta = ((Node) rutaSonidoLista.item(0)).getNodeValue().toString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ruta;
	}
	
}
