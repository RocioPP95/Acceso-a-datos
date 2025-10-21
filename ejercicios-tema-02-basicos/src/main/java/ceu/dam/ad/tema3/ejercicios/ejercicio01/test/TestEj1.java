package ceu.dam.ad.tema3.ejercicios.ejercicio01.test;

import java.util.List;

import ceu.dam.ad.tema3.ejercicios.ejercicio01.model.Pelicula;
import ceu.dam.ad.tema3.ejercicios.ejercicio01.services.PeliculasException;
import ceu.dam.ad.tema3.ejercicios.ejercicio01.services.PeliculasService;

public class TestEj1 {
	
	public void test() {
		PeliculasService service = new PeliculasService();
		try {
			List<Pelicula> peliculas = service.consultarPeliculas();
			peliculas.forEach(System.out::println);
		} catch (PeliculasException e) {
			e.printStackTrace();
		}
	}

}
