package ceu.dam.ad.xml.ejercicio09;

import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ceu.dam.ad.xml.ejercicio06.modelo.Largometraje;
import ceu.dam.ad.xml.exceptions.XMLImportException;

public class Ejercicio09ServiceImpl implements Ejercicio09Service {

	private static final Logger log = LoggerFactory.getLogger(Ejercicio09ServiceImpl.class);
	@Override
	public List<Largometraje> importXML(String pathFile) throws XMLImportException {
		try {
			log.info("Importando fichero XML de peliculas " + pathFile);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();

			PeliculasXMLHandler handler = new PeliculasXMLHandler();

			File file = new File(pathFile);

			parser.parse(file, handler);
			
			log.info("Importación completada. Total peliculas obtenidas: " + handler.getPeliculas().size());
			return handler.getPeliculas();
			
		} catch (Exception e) {
			log.error("Error leyendo XML de películas" , e);
			throw new XMLImportException("Error leyendo XML", e);
		}

	}
}
