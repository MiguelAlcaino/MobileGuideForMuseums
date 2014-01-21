package guiamovil.Parsers;

import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Element;


/**
 * @author Miguel
 *Clase madre de todos los parseadores 
 */
public class Parser {

//	private final static String ip = "192.168.2.7";
//	private final static String ip = "192.168.1.4";
	private final static String ip = "192.168.2.116";
//	private final static String ip = "192.168.1.101";
//	private final static String ip = "192.168.127.130";
//	private final static String ip = "5.213.93.189";
	private final static String puerto_web = "8282"; // Puerto para extraer imagenes y sonidos
	private final static String puerto = "8080";
	private final static String alias_mantenedor = "mantenedor_guia";
	private final static String ws = "MuseoNaval8/resources";
	public final static String uri="http://"+ip+":"+puerto+"/"+ws+"/";

	public Parser(){
		
	}
	
	public static String getIp() {
		return ip;
	}
	
	public static String getPublicPath(){
		return "http://"+ip+"/"+alias_mantenedor;
	}
	
	/**
	 * @param recurso :Parametro que identifica al recurso del Webservice
	 * @param nodo : Parametro que identifica al nodo de la respuesta del xml
	 * @return Una lista de todos los nodos asociados al recurso
	 * @throws Exception
	 */
	public NodeList listaNodos(String recurso, String nodo) throws Exception{
			URL url = new URL(uri+recurso);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(url.openStream()));
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName(nodo);
			return nodeList;
	}
	
	public NodeList listaNodos(String recurso, String nodo, String query) throws Exception{
		URL url = new URL(uri+recurso+"/?query="+query);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new InputSource(url.openStream()));
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName(nodo);
		return nodeList;
	}
	
	public NodeList listaNodos(String recurso, int id, String collection, String nodo) throws Exception{
		URL url = new URL(uri+recurso+"/"+id+"/"+collection);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new InputSource(url.openStream()));
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName(nodo);
		return nodeList;
}
	
	/**
	 * @param recurso
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Element Nodo(String recurso, String nodo, int id) throws Exception{
		URL url = new URL(uri+recurso+"/"+id);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new InputSource(url.openStream()));
		doc.getDocumentElement().normalize();
		Element elemento =  doc.getDocumentElement();
		//NodeList nodeList = doc.getElementsByTagName(nodo);
		//return nodeList;
		return elemento;
	}
	
	public Element Nodo(String uri_completa) throws Exception{
		URL url = new URL(uri_completa);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new InputSource(url.openStream()));
		doc.getDocumentElement().normalize();
		Element elemento =  doc.getDocumentElement();
		//NodeList nodeList = doc.getElementsByTagName(nodo);
		//return nodeList;
		return elemento;
	}
}
