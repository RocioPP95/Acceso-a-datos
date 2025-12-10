package ceu.dam.ad.xml.ejercicio10;

import java.util.List;

import ceu.dam.ad.xml.ejercicio10.modelo.Libro;
import ceu.dam.ad.xml.exceptions.XMLExportException;

public class Test {

	private static final String PATH_FILE = "c:/temporal/librosJackson.xml";

	public static void main(String[] args) {
		Ejercicio10Service service = new Ejercicio10ServiceImpl();
		try {
			service.escribirXMLLibros(PATH_FILE, Libro.createRandomList(10));
			
			List<Libro> libros = service.leerXMLLibros(PATH_FILE);
			System.out.println(libros);
			
		} catch (XMLExportException e) {
			e.printStackTrace();
		}
	}

}
