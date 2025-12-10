package ceu.dam.ad.xml.ejercicio07;

import java.util.List;

import ceu.dam.ad.xml.ejercicio06.modelo.Largometraje;
import ceu.dam.ad.xml.exceptions.XMLImportException;

public class Test {

	public static void main(String[] args) {
		Ejercicio07Service service = new Ejercicio07ServiceImpl();
		List<Largometraje> lista;
		try {
			lista = service.importarXML("c:/temporal/peliculasTest.xml");
			System.out.println(lista);
		} catch (XMLImportException e) {
			e.printStackTrace();
		}
	}
}
