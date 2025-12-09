package ceu.dam.ad.castillo.test;

import java.util.ArrayList;
import java.util.List;

import ceu.dam.ad.castillo.model.Caballero;
import ceu.dam.ad.castillo.model.Castillo;
import ceu.dam.ad.castillo.model.Dragon;
import ceu.dam.ad.castillo.service.CastilloXmlException;
import ceu.dam.ad.castillo.service.CastilloXmlService;
import ceu.dam.ad.castillo.service.CastilloXmlServiceImpl;

public class Test {

	public static void main(String[] args) {
		CastilloXmlService service = new CastilloXmlServiceImpl();
		try {
			List<Castillo> castillos = new ArrayList<>();
			castillos.add(createCastillo());
			castillos.add(createCastillo());
			castillos.add(createCastillo());
			service.exportCastillosToXmlJackson("c:/temporal/castilloJackson.xml", castillos);
		} catch (CastilloXmlException e) {
			e.printStackTrace();
		}
//		try {
//			Castillo castillo = service.importCastilloFromXml("c:/temporal/castillo.xml");
//			System.out.println("CASTILLO LEÍDO DE XML CON LOS SIGUIENTES DATOS:");
//			System.out.println("==>> Foso: " + castillo.getFoso());
//			System.out.println("==>> Caballeros:");
//			castillo.getCaballeros().forEach(System.out::println);
//			System.out.println("==>> Dragones:");
//			castillo.getDragones().forEach(System.out::println);
//			
//		} catch (CastilloXmlException e) {
//			e.printStackTrace();
//		}
		
		
	}
	
	public static Castillo createCastillo() {
		Castillo castillo = new Castillo();

		// --- Lista de caballeros ---
		List<Caballero> caballeros = new ArrayList<>();

		Caballero c1 = new Caballero();
		c1.setNombre("Blas de los montes");
		c1.setCaballo("Rocinante");
		c1.setEscudero("Juanma de los cantalejos");
		c1.setEdad(31);

		Caballero c2 = new Caballero();
		c2.setNombre("Kuko de los Kukos");
		c2.setCaballo("Sombragris");
		c2.setEscudero("Sardinilla de los Pacos");
		c2.setEdad(12);

		// Caballero extra
		Caballero c3 = new Caballero();
		c3.setNombre("Rodrigo de la Vega");
		c3.setCaballo("Tormenta");
		c3.setEscudero("Miguelín el Fiel");
		c3.setEdad(27);

		caballeros.add(c1);
		caballeros.add(c2);
		caballeros.add(c3);

		castillo.setCaballeros(caballeros);


		// --- Lista de dragones ---
		List<Dragon> dragones = new ArrayList<>();

		Dragon d1 = new Dragon();
		d1.setAlas(false);
		d1.setColor("verde");
		d1.setRaza("wida");
		d1.setPoder(32);

		Dragon d2 = new Dragon();
		d2.setAlas(true);
		d2.setColor("amarillo");
		d2.setRaza("smaug");
		d2.setPoder(200);

		// Dragón extra
		Dragon d3 = new Dragon();
		d3.setAlas(true);
		d3.setColor("rojo");
		d3.setRaza("ragnarok");
		d3.setPoder(150);

		dragones.add(d1);
		dragones.add(d2);
		dragones.add(d3);

		castillo.setDragones(dragones);


		// --- Otros atributos del castillo ---
		castillo.setFoso(true);
		
		return castillo;
	}

}
