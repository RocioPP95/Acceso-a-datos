package ceu.dam.ad.castillo.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ceu.dam.ad.castillo.model.Caballero;
import ceu.dam.ad.castillo.model.Castillo;
import ceu.dam.ad.castillo.model.Dragon;
import tools.jackson.dataformat.xml.XmlMapper;

public class CastilloXmlServiceImpl implements CastilloXmlService {

	@Override
	public void exportCastilloToXmlJackson(String fileName, Castillo castillo) throws CastilloXmlException {
		XmlMapper mapper = new XmlMapper();
		
		File file = new File(fileName);
		mapper.writeValue(file, castillo);
	}	

	@Override
	public void exportCastillosToXmlJackson(String fileName, List<Castillo> castillos) throws CastilloXmlException {
		XmlMapper mapper = new XmlMapper();
		Castillos castillosObject = new Castillos();
		castillosObject.setCastillos(castillos);
		File file = new File(fileName);
		mapper.writeValue(file, castillosObject);
	}	

	
	@Override
	public Castillo importCastilloToXmlJackson(String fileName) throws CastilloXmlException {
		XmlMapper mapper = new XmlMapper();
		
		File file = new File(fileName);
		return mapper.readValue(file, Castillo.class);
	}	
	
	@Override
	public void exportCastilloToXml(String fileName, Castillo castillo) throws CastilloXmlException {
		try {
			// Crear documento
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
		
			// Construir/rellenar documento
			Element castilloTag = document.createElement("castillo");
			document.appendChild(castilloTag);
			
			Element fosoTag = document.createElement("foso");
			castilloTag.appendChild(fosoTag);
			fosoTag.setTextContent(castillo.getFoso().toString());
			
			Element caballerosTag = document.createElement("caballeros");
			castilloTag.appendChild(caballerosTag);
			
			for (Caballero caballero : castillo.getCaballeros()) {
				Element caballeroTag = document.createElement("caballero");
				caballerosTag.appendChild(caballeroTag);
				
				Element nombreTag = document.createElement("nombre");
				caballeroTag.appendChild(nombreTag);
				nombreTag.setTextContent(caballero.getNombre());

				Element caballoTag = document.createElement("caballo");
				caballeroTag.appendChild(caballoTag);
				caballoTag.setTextContent(caballero.getNombre());

				Element escuderoTag = document.createElement("escudero");
				caballeroTag.appendChild(escuderoTag);
				escuderoTag.setTextContent(caballero.getEscudero());
				
				Element edadTag = document.createElement("edad");
				caballeroTag.appendChild(edadTag);
				edadTag.setTextContent(caballero.getEdad().toString());
			}
			
			Element dragonesTag = document.createElement("dragones");
			castilloTag.appendChild(dragonesTag);
			
			for (Dragon dragon : castillo.getDragones()) {
				Element dragonTag = document.createElement("dragon");
				dragonesTag.appendChild(dragonTag);
				dragonTag.setAttribute("alas", dragon.getAlas().toString());
				dragonTag.setAttribute("color", dragon.getColor());
				
				Element razaTag = document.createElement("raza");
				dragonTag.appendChild(razaTag);
				razaTag.setTextContent(dragon.getRaza());

				Element poderTag = document.createElement("poder");
				dragonTag.appendChild(poderTag);
				poderTag.setTextContent(dragon.getPoder().toString());
			}
			
			// Exportar documento a fichero
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			File file = new File(fileName);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
			
		}
		catch(Exception e) {
			throw new CastilloXmlException("Error generando XML" , e);
		}
		
		
	}

	@Override
	public Castillo importCastilloFromXml(String fileName) throws CastilloXmlException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			File file = new File(fileName);
			Document document = builder.parse(file);
			
			Element castilloTag = document.getDocumentElement();
			Castillo castillo = new Castillo();
			
			Element fosoTag = (Element) castilloTag.getElementsByTagName("foso").item(0);
			castillo.setFoso(Boolean.valueOf(fosoTag.getTextContent()));

			Element caballerosTag = (Element) castilloTag.getElementsByTagName("caballeros").item(0);
			castillo.setCaballeros(new ArrayList<>());
			
			NodeList caballeroTagList = caballerosTag.getElementsByTagName("caballero");
			for (int i = 0; i < caballeroTagList.getLength(); i++) {
				Element caballeroTag = (Element) caballeroTagList.item(i);
				Caballero caballero = new Caballero();
				castillo.getCaballeros().add(caballero);
				
				Element nombreTag = (Element) caballeroTag.getElementsByTagName("nombre").item(0);
				caballero.setNombre(nombreTag.getTextContent());
				Element escuderoTag = (Element) caballeroTag.getElementsByTagName("escudero").item(0);
				caballero.setEscudero(escuderoTag.getTextContent());
				Element caballoTag = (Element) caballeroTag.getElementsByTagName("caballo").item(0);
				caballero.setCaballo(caballoTag.getTextContent());
				Element edadTag = (Element) caballeroTag.getElementsByTagName("edad").item(0);
				caballero.setEdad(Integer.valueOf(edadTag.getTextContent()));
			}
			
			Element dragonesTag = (Element) castilloTag.getElementsByTagName("dragones").item(0);
			castillo.setDragones(new ArrayList<>());
			NodeList dragonTagList = dragonesTag.getElementsByTagName("dragon");
			for (int i = 0; i < dragonTagList.getLength(); i++) {
				Element dragonTag = (Element) dragonTagList.item(i);
				Dragon dragon = new Dragon();
				castillo.getDragones().add(dragon);
				
				dragon.setAlas(Boolean.valueOf(dragonTag.getAttribute("alas")));
				dragon.setColor(dragonTag.getAttribute("color"));
				
				Element razaTag = (Element) dragonTag.getElementsByTagName("raza").item(0);
				dragon.setRaza(razaTag.getTextContent());
				Element poderTag = (Element) dragonTag.getElementsByTagName("poder").item(0);
				dragon.setPoder(Integer.valueOf(poderTag.getTextContent()));
			}
			
			return castillo;
			
			
		}
		catch(Exception e) {
			throw new CastilloXmlException("Error generando XML" , e);
		}
		
	}

	
	
}

















