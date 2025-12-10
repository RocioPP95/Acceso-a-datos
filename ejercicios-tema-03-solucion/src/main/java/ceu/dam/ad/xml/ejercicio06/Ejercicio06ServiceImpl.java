package ceu.dam.ad.xml.ejercicio06;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ceu.dam.ad.xml.ejercicio06.modelo.Largometraje;
import ceu.dam.ad.xml.ejercicio06.modelo.Persona;
import ceu.dam.ad.xml.exceptions.XMLExportException;

public class Ejercicio06ServiceImpl implements Ejercicio06Service {

	private static final Logger log = LoggerFactory.getLogger(Ejercicio06ServiceImpl.class);
	
	@Override
	public void exportarXML(List<Largometraje> peliculas, String pathFile) throws XMLExportException {
		try {
			log.info("Exportando lista de peliculas a XML " + pathFile);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document xml = builder.newDocument();
			
			Element peliculasTag = xml.createElement("peliculas");
			xml.appendChild(peliculasTag);
			
			for (Largometraje pelicula : peliculas) {
				log.debug("Incluyendo nueva película en el XML: " + pelicula);
				Element peliculaTag = xml.createElement("pelicula");
				peliculasTag.appendChild(peliculaTag);
				
				Element tituloTag = xml.createElement("titulo");
				tituloTag.setTextContent(pelicula.getTitulo());
				peliculaTag.appendChild(tituloTag);

				Element duracionTag = xml.createElement("duracion");
				duracionTag.setTextContent(pelicula.getDuracion().toString());
				peliculaTag.appendChild(duracionTag);
				
				Element añoTag = xml.createElement("año");
				añoTag.setTextContent(pelicula.getAño().toString());
				peliculaTag.appendChild(añoTag);

				Element artistasTag = xml.createElement("artistas");
				peliculaTag.appendChild(artistasTag);

				crearArtistaTag(xml, Persona.DIRECCION, pelicula.getDireccion(), artistasTag);
				
				crearArtistaTag(xml, Persona.PRODUCCION, pelicula.getProduccion(), artistasTag);
				
				for (Persona actor : pelicula.getActores()) {
					crearArtistaTag(xml, Persona.INTERPRETACION, actor, artistasTag);
				}
				
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(xml);
			StreamResult sr = new StreamResult(new File(pathFile));
			transformer.transform(source, sr);
			log.info("Fichero XML generado correctamente");
		}
		catch(Exception e) {
			log.error("Error exportando fichero XML " , e);
			throw new XMLExportException("Error exportando XML de Películas", e);
		}
		
		
	}

	private void crearArtistaTag(Document xml, String tipo, Persona persona, Element artistasTag) {
		Element artistaTag = xml.createElement("artista");
		artistaTag.setAttribute("tipo", tipo);
		artistasTag.appendChild(artistaTag);
		
		Element nombreTag = xml.createElement("nombre");
		nombreTag.setTextContent(persona.getNombre());
		artistaTag.appendChild(nombreTag);
		
		Element nacionalidadTag = xml.createElement("nacionalidad");
		nacionalidadTag.setTextContent(persona.getNacionalidad());
		artistaTag.appendChild(nacionalidadTag);
	}

	
	
}
