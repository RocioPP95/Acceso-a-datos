package ceu.dam.ad.xml.ejercicio12.services;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ceu.dam.ad.xml.ejercicio12.modelo.Catalogo;


public class Ejercicio12ServiceSaxImpl implements Ejercicio12Service{

	private static final Logger log = LoggerFactory.getLogger(Ejercicio12ServiceSaxImpl.class);
	
	public void exportXML(Catalogo catalogo, String pathFile) throws CatalogoXMLException{
		// Esto con SAX no se puede hacer. SAX lo usamos sólo para leer
		log.warn("No se puede exportar XML utilizando SAX");
		throw new NoSuchMethodError("Exportación no implementada con SAX"); // excepción estándar de JAVA
	}
	
	
	
	public Catalogo importXML(String pathFile) throws CatalogoXMLException{
		try {
			log.info("Importando fichero XML de catálogo " + pathFile);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();

			CatalogoXMLHandler handler = new CatalogoXMLHandler();

			File file = new File(pathFile);

			parser.parse(file, handler);

			log.info("Importación completada. Total artículos obtenidos en el catálogo: " + handler.getCatalogo().getArticulos().size());
			return handler.getCatalogo();
			
		} catch (Exception e) {
			log.error("Error leyendo XML", e);
			throw new CatalogoXMLException("Error al importar catálogo", e);
		}
	}

	
	
}
