package ceu.dam.ad.xml.ejercicio12.test;

import ceu.dam.ad.xml.ejercicio12.modelo.Articulo;
import ceu.dam.ad.xml.ejercicio12.modelo.Catalogo;
import ceu.dam.ad.xml.ejercicio12.services.CatalogoXMLException;
import ceu.dam.ad.xml.ejercicio12.services.Ejercicio12Service;
import ceu.dam.ad.xml.ejercicio12.services.Ejercicio12ServiceDomImpl;
import ceu.dam.ad.xml.ejercicio12.services.Ejercicio12ServiceJacksonImpl;
import ceu.dam.ad.xml.ejercicio12.services.Ejercicio12ServiceSaxImpl;

public class Test {

	// cambia esta ruta para que coincida con una carpeta de tu disco
	private static final String PATH_FILE = "c:/temporal/catalogo.xml";

	public static void main(String[] args) {

		// PRUEBA DOM
		Ejercicio12Service service = new Ejercicio12ServiceDomImpl();
		test(service);
		
		// PRUEBA SAX
		service = new Ejercicio12ServiceSaxImpl();
		test(service);
		
		// PRUEBA JACKSON
		service = new Ejercicio12ServiceJacksonImpl();
		test(service);

	}

	public static void test(Ejercicio12Service service) {
		try {
			Catalogo catalogo = Catalogo.createRandomObject(10);
			service.exportXML(catalogo, PATH_FILE);

			// Comprueba que el XML generado es correcto e igual al que se solicita

			catalogo = service.importXML(PATH_FILE);
			System.out.println("Catálogo leído con tamaño: " + catalogo.getTamaño());
			System.out.println("Cantidad de artículos (DEBE SER IGUAL AL TAMAÑO): " + catalogo.getArticulos().size());
			for (Articulo articulo : catalogo.getArticulos()) {
				System.out.println("\t" + articulo);
			}

			// Comprueba que lo impreso es igual al contenido del XML

		} catch (CatalogoXMLException e) {
			System.err.println("Error al intentar leer o escribir XML");
			e.printStackTrace();
		}
	}

}
