package guiamovil.Parsers;

import guiamovil.clases.Ayudante;
import guiamovil.clases.Coordenada;
import guiamovil.clases.Panoramica;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PanoramicaParser extends Parser {

	private String recurso = "panoramicas";
	private String nodo = "panoramica";
	
	public ArrayList<Panoramica> getPanoramicas(int id_sala){
		ArrayList<Panoramica> lista_panoramicas = new ArrayList<Panoramica>();
		try{
			NodeList nl = listaNodos("salas", id_sala, "panoramicaCollection", nodo);
			
			for(int i=0; i<nl.getLength();i++){
				Panoramica panoramica = new Panoramica();
				
				Node nodo = nl.item(i);
				Element elemento = (Element) nodo;
				
				NodeList idLista = elemento.getElementsByTagName("id");
				Element idElement = (Element) idLista.item(0);
				idLista = idElement.getChildNodes();
				panoramica.setId(Integer.parseInt(((Node) idLista.item(0)).getNodeValue()));
				
				NodeList descripcionLista = elemento.getElementsByTagName("descripcion");
				Element descripcionElement = (Element) descripcionLista.item(0);
				descripcionLista = descripcionElement.getChildNodes();
				panoramica.setDescripcion(((Node) descripcionLista.item(0)).getNodeValue().toString());
				
				NodeList imagenLista = elemento.getElementsByTagName("rutaImagen");
				Element imagenElement = (Element) imagenLista.item(0);
				imagenLista = imagenElement.getChildNodes();
				Ayudante ay = new Ayudante();
				String path = ((Node) imagenLista.item(0)).getNodeValue().toString();
				panoramica.setImagen(ay.DrawabledesdeUrl(Parser.getPublicPath()+"/uploads/imagenes/"+path));
				
				//FALTA AGREGAR EL SETEO DE LA IMAGEN EN MINIATURA
				/*
				NodeList miniaturaLista = elemento.getElementsByTagName("rutaImagenMiniatura");
				Element miniaturaElement = (Element) miniaturaLista.item(0);
				imagenLista = imagenElement.getChildNodes();
				panoramica.setImagenMiniatura(ay.DrawabledesdeUrl(((Node) idLista.item(0)).getNodeValue().toString()));
				*/
				
				NodeList nOrdenLista = elemento.getElementsByTagName("numeroOrden");
				Element nOrdenElement = (Element) nOrdenLista.item(0);
				nOrdenLista = nOrdenElement.getChildNodes();
				panoramica.setNumeroOrden(Integer.parseInt(((Node) nOrdenLista.item(0)).getNodeValue()));
				
				lista_panoramicas.add(panoramica);
			}
		}catch(Exception e){
			
		}
		
		return lista_panoramicas;
	}

	public ArrayList<Coordenada> getCoordenadas(int id_panoramica){
		ArrayList<Coordenada> lista_coordenadas = new ArrayList<Coordenada>();
		
		try {
			NodeList nl = listaNodos(recurso,id_panoramica,"exposicionCollection", "exposicion");
			
			for (int i = 0; i<nl.getLength();i++){
				
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
					
					lista_coordenadas.add(coordenada);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista_coordenadas;
	}
	
	public ArrayList<Integer> getIdsPanoramicas(int id_sala){
		ArrayList<Integer> lista_ids = new ArrayList<Integer>();
		try{
			NodeList nl = listaNodos("salas",id_sala,"panoramicaCollection", nodo);
			
			for(int i=0; i<nl.getLength();i++){
				
				
				Node nodo = nl.item(i);
				Element elemento = (Element) nodo;
				
				NodeList idLista = elemento.getElementsByTagName("id");
				Element idElement = (Element) idLista.item(0);
				idLista = idElement.getChildNodes();
				Integer id = new Integer((Integer.parseInt(((Node) idLista.item(0)).getNodeValue())));
				
				lista_ids.add(id);
			}
		}catch(Exception e){
			
		}
		
		return lista_ids;
	}
}
