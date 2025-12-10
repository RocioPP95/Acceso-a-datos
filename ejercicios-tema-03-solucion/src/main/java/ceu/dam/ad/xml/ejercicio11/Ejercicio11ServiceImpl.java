package ceu.dam.ad.xml.ejercicio11;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import ceu.dam.ad.xml.ejercicio11.modelo.Libro;
import ceu.dam.ad.xml.exceptions.XMLExportException;

public class Ejercicio11ServiceImpl implements Ejercicio11Service {

	private static final Logger log = LoggerFactory.getLogger(Ejercicio11ServiceImpl.class);
	
	@Override
	public void escribirJsonLibros(String pathFile, List<Libro> lista) throws XMLExportException {
		try {
			JsonMapper mapper = new JsonMapper();
			File file = new File(pathFile);
			mapper.writeValue(file, lista);
		}
		catch(Exception e) {
			log.error("Error escribiendo JSON" , e);
			throw new XMLExportException("Error escribiendo JSON" , e);
		}
	}
	
	@Override
	public List<Libro> leerJsonLibros(String pathFile) throws XMLExportException {
		try {
			JsonMapper mapper = new JsonMapper();
			File file = new File(pathFile);
			return Arrays.asList(mapper.readValue(file, Libro[].class));
		}
		catch(Exception e) {
			log.error("Error leyendo JSON" , e);
			throw new XMLExportException("Error leyendo JSON" , e);
		}
	}

	@Override
	public void escribirYamlLibros(String pathFile, List<Libro> lista) throws XMLExportException {
		try {
			YAMLMapper mapper = new YAMLMapper();
			File file = new File(pathFile);
			mapper.writeValue(file, lista);
		}
		catch(Exception e) {
			log.error("Error escribiendo YAML" , e);
			throw new XMLExportException("Error escribiendo YAML" , e);
		}
	}

	@Override
	public List<Libro> leerYamlLibros(String pathFile) throws XMLExportException {
		try {
			YAMLMapper mapper = new YAMLMapper();
			File file = new File(pathFile);
			return Arrays.asList(mapper.readValue(file, Libro[].class));
		}
		catch(Exception e) {
			log.error("Error leyendo YAML" , e);
			throw new XMLExportException("Error leyendo YAML" , e);
		}
	}
	
	
	
	
	

}
