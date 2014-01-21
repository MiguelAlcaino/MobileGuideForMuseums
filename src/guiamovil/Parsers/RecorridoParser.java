package guiamovil.Parsers;

import guiamovil.clases.Recorrido;
import guiamovil.clases.Sala;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class RecorridoParser extends Parser {

	private String recurso = "recorridoes";
	private String nodo = "recorrido";
	static private HashMap<Integer, Integer> orden;
	
	
	public static HashMap<Integer, Integer> getOrden() {
		return orden;
	}


	public static void setOrden(HashMap<Integer, Integer> orden) {
		RecorridoParser.orden = orden;
	}
	
	public ArrayList<String> getRutaSonidoRecorrido(int id_sala){
		
		ArrayList<String> ar_sonidos = new ArrayList<String>();
		
		try {
			NodeList nl = listaNodos("salas", id_sala, "grabacionRecorridoCollection", "grabacionRecorrido");
			
			for(int i=0;i<nl.getLength();i++){
				
				Node nodo = nl.item(i);
				Element elemento = (Element) nodo;
				
				NodeList rutaSonidoLista = elemento.getElementsByTagName("rutaSonido");
				Element rutaSonidoElement = (Element) rutaSonidoLista.item(0);
				rutaSonidoLista = rutaSonidoElement.getChildNodes();
				ar_sonidos.add(((Node) rutaSonidoLista.item(0)).getNodeValue().toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ar_sonidos;
	}
	
	public ArrayList<Recorrido> getRecorridos(){
		ArrayList<Recorrido> ar_recorrido = new ArrayList<Recorrido>();
		
		try {
			NodeList nl = listaNodos(recurso, nodo);
			
			for(int i=0; i<nl.getLength();i++){
				Node nodo = nl.item(i);
				Element elemento = (Element) nodo;
				
				NodeList estadoLista = elemento.getElementsByTagName("estado");
				Element estadoElement = (Element) estadoLista.item(0);
				estadoLista = estadoElement.getChildNodes();
				
				if(Boolean.parseBoolean(((Node) estadoLista.item(0)).getNodeValue().toString())){
					Recorrido recorrido = new Recorrido();
					
					NodeList idLista = elemento.getElementsByTagName("id");
					Element idElement = (Element) idLista.item(0);
					idLista = idElement.getChildNodes();
					recorrido.setId_destino(Integer.parseInt(((Node) idLista.item(0)).getNodeValue()));
					
					NodeList tipoLista = elemento.getElementsByTagName("tipo");
					Element tipoElement = (Element) tipoLista.item(0);
					tipoLista = tipoElement.getChildNodes();
					recorrido.setNombre(((Node) tipoLista.item(0)).getNodeValue().toString());
					
					ar_recorrido.add(recorrido);
				}
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ar_recorrido;
	}

	public void llenarOrden(int id_recorrido){
		RecorridoParser.orden = new HashMap<Integer, Integer>();
		
		try {
			NodeList nl = listaNodos("recorridoes", id_recorrido, "detalleRecorridoCollection", "detalleRecorrido");
			
			for(int i=0; i<nl.getLength();i++){
				Node nodo = nl.item(i);
				Element elemento = (Element) nodo;
				
				NodeList ordenLista = elemento.getElementsByTagName("orden");
				Element ordenElement = (Element) ordenLista.item(0);
				ordenLista = ordenElement.getChildNodes();
				int numero_orden = Integer.parseInt(((Node) ordenLista.item(0)).getNodeValue());
				
				NodeList idSalaLista = elemento.getElementsByTagName("sala");
				Element idSalaElement = (Element) idSalaLista.item(0);
				
				String uri_sala = idSalaElement.getAttribute("uri");
				
				SalaParser sala_parser = new SalaParser();
				Sala sala = sala_parser.getSala(uri_sala);
				
				RecorridoParser.orden.put(numero_orden, sala.getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
