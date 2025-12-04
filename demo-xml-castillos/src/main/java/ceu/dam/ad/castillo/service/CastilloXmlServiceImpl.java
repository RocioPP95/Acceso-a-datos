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

public class CastilloXmlServiceImpl implements CastilloXmlService {

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

		} catch (Exception e) {
			throw new CastilloXmlException("Error generando XML", e);
		}

	}

	@Override
	public Castillo importtCastilloToXml(String fileName) throws CastilloXmlException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			File file = new File(fileName);
			Document document = builder.parse(file);

			Element castilloTag = document.getDocumentElement();
			Castillo castillo = new Castillo();

			NodeList fososTag = castilloTag.getElementsByTagName("foso");
			Element fosoTag = (Element) fososTag.item(0);
			String foso = fosoTag.getTextContent();
			castillo.setFoso(Boolean.valueOf(foso));

			Element caballerosTag = (Element) castilloTag.getElementsByTagName("caballeros").item(0);

			List<Caballero> caballeros = new ArrayList<>();
			castillo.setCaballeros(caballeros);

			NodeList caballeroTagList = caballerosTag.getElementsByTagName("caballero");
			for (int i = 0; i < caballeroTagList.getLength(); i++) {
				Element caballeroTag = (Element) caballeroTagList.item(i);
				Caballero caballero = new Caballero();

				Element nombreTag = (Element) caballeroTag.getElementsByTagName("nombre").item(0);
				caballero.setNombre(nombreTag.getTextContent());
				Element escuderoTag = (Element) caballeroTag.getElementsByTagName("nombre").item(0);
				caballero.setEscudero(escuderoTag.getTextContent());

				Element caballoTag = (Element) escuderoTag.getElementsByTagName("nombre").item(0);
				caballero.setCaballo(nombreTag.getTextContent());

				Element edadTag = (Element) caballoTag.getElementsByTagName("nombre").item(0);
				caballero.setEdad(Integer.valueOf(nombreTag.getTextContent()));

			}

			Element dragonesTag = (Element) castilloTag.getElementsByTagName("Dragones").item(0);
			castillo.setDragones(new ArrayList<>());
			NodeList dragonTagList = dragonesTag.getElementsByTagName("dragon");
			for (int i = 0; i < dragonTagList.getLength(); i++) {
				Element dragonTag = (Element) dragonTagList.item(i);
				Dragon dragon = new Dragon();
				castillo.getDragones().add(dragon);
				
				dragon.setAlas(Boolean.valueOf(dragonTag.getAttribute("alas")));
				
			}

		} catch (Exception e) {
			throw new CastilloXmlException("Error generando el xml");
		}
		return null;
	}

}
