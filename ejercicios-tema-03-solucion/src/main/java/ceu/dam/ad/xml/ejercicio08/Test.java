package ceu.dam.ad.xml.ejercicio08;

import java.util.List;

import ceu.dam.ad.xml.ejercicio04.modelo.Libro;
import ceu.dam.ad.xml.exceptions.XMLImportException;

public class Test {

	public static void main(String[] args) {
		Ejercicio08Service service = new Ejercicio08ServiceImpl();
		try {
			List<Libro> libros =  service.importXML("c:/temporal/librosTest.xml");
			System.out.println(libros);
		} catch (XMLImportException e) {
			e.printStackTrace();
		}
	}

}
