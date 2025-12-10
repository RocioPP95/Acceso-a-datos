package ceu.dam.ad.xml.ejercicio11;

import java.util.List;

import ceu.dam.ad.xml.ejercicio11.modelo.Libro;
import ceu.dam.ad.xml.exceptions.XMLExportException;

public class Test {

	
	private static final String PATH_FILE_JSON = "c:/temporal/librosJackson.json";
	private static final String PATH_FILE_YAML = "c:/temporal/librosJackson.yaml";
	
	public static void main(String[] args) {
		Ejercicio11Service service = new Ejercicio11ServiceImpl();
		try {
			List<Libro> randomList = Libro.createRandomList(10);
			
			service.escribirJsonLibros(PATH_FILE_JSON, randomList);
			List<Libro> libros = service.leerJsonLibros(PATH_FILE_JSON);
			System.out.println(libros);
			
			service.escribirYamlLibros(PATH_FILE_YAML, randomList);
			libros = service.leerYamlLibros(PATH_FILE_YAML);
			System.out.println(libros);
			
		} catch (XMLExportException e) {
			e.printStackTrace();
		}
	}

}
