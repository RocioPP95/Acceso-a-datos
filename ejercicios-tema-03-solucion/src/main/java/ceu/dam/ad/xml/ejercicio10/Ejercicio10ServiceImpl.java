package ceu.dam.ad.xml.ejercicio10;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import ceu.dam.ad.xml.ejercicio10.modelo.Libro;
import ceu.dam.ad.xml.ejercicio10.modelo.Libros;
import ceu.dam.ad.xml.exceptions.XMLExportException;

public class Ejercicio10ServiceImpl implements Ejercicio10Service {

	private static final Logger log = LoggerFactory.getLogger(Ejercicio10ServiceImpl.class);
	
	@Override
	public void escribirXMLLibros(String pathFile, List<Libro> lista) throws XMLExportException {
		try {
			XmlMapper mapper = new XmlMapper();
			File file = new File(pathFile);
			Libros libros = new Libros();
			libros.setLibros(lista);
			mapper.writeValue(file, libros);
		}
		catch(Exception e) {
			log.error("Error escribiendo XML" , e);
			throw new XMLExportException("Error escribiendo XML" , e);
		}
	}
	
	@Override
	public List<Libro> leerXMLLibros(String pathFile) throws XMLExportException {
		try {
			XmlMapper mapper = new XmlMapper();
			File file = new File(pathFile);
			Libros libros = mapper.readValue(file, Libros.class);
			return libros.getLibros();
		}
		catch(Exception e) {
			log.error("Error leyendo XML" , e);
			throw new XMLExportException("Error leyendo XML" , e);
		}
	}
	
	
	

}
