package ceu.dam.ad.xml.ejercicio06;

import ceu.dam.ad.xml.ejercicio06.modelo.Largometraje;
import ceu.dam.ad.xml.exceptions.XMLExportException;

public class Test {

	public static void main(String[] args) {
		Ejercicio06Service service = new Ejercicio06ServiceImpl();
		try {
			service.exportarXML(Largometraje.createRandomList(10), "c:/temporal/peliculasTest.xml");
		} catch (XMLExportException e) {
			e.printStackTrace();
		}
	}

}
