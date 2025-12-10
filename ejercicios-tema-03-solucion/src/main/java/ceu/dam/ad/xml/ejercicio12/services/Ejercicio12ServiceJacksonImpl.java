package ceu.dam.ad.xml.ejercicio12.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import ceu.dam.ad.xml.ejercicio12.modelo.Catalogo;


public class Ejercicio12ServiceJacksonImpl implements Ejercicio12Service {

	private static final Logger log = LoggerFactory.getLogger(Ejercicio12ServiceJacksonImpl.class);
	
	public void exportXML(Catalogo catalogo, String pathFile) throws CatalogoXMLException{
		try {
			
			File file = new File(pathFile);
			XmlMapper mapper = new XmlMapper();
			mapper.writeValue(file, catalogo);
			
		} catch (Exception e) {
			log.error("Error escribiendo XML" , e);
			throw new CatalogoXMLException("Error al exportar cat�logo", e);
		}
	}
	
	public Catalogo importXML(String pathFile) throws CatalogoXMLException{
		try {
			
			File file = new File(pathFile);
			XmlMapper mapper = new XmlMapper();
			return mapper.readValue(file, Catalogo.class);
			
		} catch (Exception e) {
			log.error("Error leyendo XML" , e);
			throw new CatalogoXMLException("Error al importar cat�logo", e);
		}
	}

	
	
}
