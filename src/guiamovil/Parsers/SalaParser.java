package guiamovil.Parsers;

import guiamovil.clases.Coordenada;
import guiamovil.clases.Sala;
//import guiamovil.clases.Sala;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SalaParser extends Parser {

	private String recurso = "salas";
	private String nodo = "sala";
	
	public SalaParser(){
		
	}
	/*
	public ArrayList<Sala> getSalas(int piso){
		
		ArrayList<Sala> lista_mapas = new ArrayList<Sala>();
		
		try{
			
		}
		return lista_mapas;
	}
	*/
	
	public Sala getSala(String uri_sala){
		Sala sala = new Sala();
		
		try {
			Element elemento = Nodo(uri_sala);
			
			//Solo capturo el id para usarlo en el parser de recorrido
			NodeList idList = elemento.getElementsByTagName("id");
			Element idElement = (Element) idList.item(0);
			idList = idElement.getChildNodes();
			sala.setId((Integer.parseInt(((Node) idList.item(0)).getNodeValue())));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sala;
	}
	
	public ArrayList<Coordenada> getCoordenadas(int piso){
		ArrayList<Coordenada> lista_coordenadas = new ArrayList<Coordenada>();
		
		try {
			//NodeList nl = listaNodos(recurso, nodo, "SELECT%20e%20FROM%20Sala%20e%20Where%20e.piso%20%3D%20"+piso);
			NodeList nl = listaNodos("pisoes", piso, nodo+"Collection", nodo); 
			for(int i=0; i<nl.getLength();i++){
				Node nodo = nl.item(i);
				Element elemento = (Element) nodo;
				
				NodeList estadoLista = elemento.getElementsByTagName("estado");
				Element estadoElement = (Element) estadoLista.item(0);
				estadoLista = estadoElement.getChildNodes();
				
				if(Boolean.parseBoolean(((Node) estadoLista.item(0)).getNodeValue().toString())){
					Coordenada coordenada = new Coordenada();
					
					NodeList cordXLista =  elemento.getElementsByTagName("cordX");
					Element cordXElement = (Element) cordXLista.item(0);
					cordXLista = cordXElement.getChildNodes();
					coordenada.setX(Integer.parseInt(((Node) cordXLista.item(0)).getNodeValue()));
					
					NodeList cordYLista =  elemento.getElementsByTagName("cordY");
					Element cordYElement = (Element) cordYLista.item(0);
					cordYLista = cordYElement.getChildNodes();
					coordenada.setY(Integer.parseInt(((Node) cordYLista.item(0)).getNodeValue()));
					
					NodeList IdLista = elemento.getElementsByTagName("id");
					Element IdElement = (Element) IdLista.item(0);
					IdLista = IdElement.getChildNodes();
					coordenada.setIdDestino(Integer.parseInt(((Node) IdLista.item(0)).getNodeValue()));
					
					NodeList nombreLista = elemento.getElementsByTagName("nombre");
					Element nombreElement = (Element) nombreLista.item(0);
					nombreLista = nombreElement.getChildNodes();
					coordenada.setTexto(((Node) nombreLista.item(0)).getNodeValue().toString());
					
					lista_coordenadas.add(coordenada);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista_coordenadas;
	}
	}
