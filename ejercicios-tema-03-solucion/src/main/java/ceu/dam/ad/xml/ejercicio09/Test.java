package ceu.dam.ad.xml.ejercicio09;

import java.util.List;

import ceu.dam.ad.xml.ejercicio06.modelo.Largometraje;
import ceu.dam.ad.xml.exceptions.XMLImportException;

public class Test {

	public static void main(String[] args) {
		Ejercicio09Service service = new Ejercicio09ServiceImpl();
		try {
			List<Largometraje> peliculas =  service.importXML("c:/temporal/peliculasTest.xml");
			System.out.println(peliculas);
		} catch (XMLImportException e) {
			e.printStackTrace();
		}
	}

}
