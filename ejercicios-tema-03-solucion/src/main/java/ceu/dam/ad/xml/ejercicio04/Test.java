package ceu.dam.ad.xml.ejercicio04;

import ceu.dam.ad.xml.ejercicio04.modelo.Libro;
import ceu.dam.ad.xml.exceptions.XMLExportException;

public class Test {

	public static void main(String[] args) {
		Ejercicio04Service service = new Ejercicio04ServiceImpl();
		try {
			service.exportXML(Libro.createRandomList(10), "c:/temporal/librosTest.xml");
		} catch (XMLExportException e) {
			e.printStackTrace();
		}
	}

}
