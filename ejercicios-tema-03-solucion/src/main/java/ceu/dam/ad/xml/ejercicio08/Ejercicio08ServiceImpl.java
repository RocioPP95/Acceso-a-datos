package ceu.dam.ad.xml.ejercicio08;

import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ceu.dam.ad.xml.ejercicio04.modelo.Libro;
import ceu.dam.ad.xml.exceptions.XMLImportException;

public class Ejercicio08ServiceImpl implements Ejercicio08Service {

	private static final Logger log = LoggerFactory.getLogger(Ejercicio08ServiceImpl.class);
	
	@Override
	public List<Libro> importXML(String pathFile) throws XMLImportException  {
		try {
			log.info("Importando fichero XML de libros " + pathFile);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			
			LibrosXMLHandler handler = new LibrosXMLHandler();
			
			File file = new File(pathFile);
			
			parser.parse(file, handler);
			
			log.info("Importación completada. Total libros obtenidos: " + handler.getLibros().size());
			return handler.getLibros();
		}
		catch(Exception e) {
			log.error("Error leyendo XML", e);
			throw new XMLImportException("Error leyendo XML", e);
		}
	}
}
