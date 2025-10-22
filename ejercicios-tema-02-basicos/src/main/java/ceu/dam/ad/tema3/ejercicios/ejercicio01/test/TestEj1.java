package ceu.dam.ad.tema3.ejercicios.ejercicio01.test;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ceu.dam.ad.tema3.ejercicios.ejercicio01.model.Pelicula;
import ceu.dam.ad.tema3.ejercicios.ejercicio01.repository.PeliculaRepository;
import ceu.dam.ad.tema3.ejercicios.ejercicio01.services.PeliculasException;
import ceu.dam.ad.tema3.ejercicios.ejercicio01.services.PeliculasService;

@Component
public class TestEj1 {

	private PeliculaRepository peliculaRepository;
	@Autowired
	private PeliculasService service;

	TestEj1(PeliculaRepository peliculaRepository) {
		this.peliculaRepository = peliculaRepository;
	}

	public void test() {

		try {
			List<Pelicula> peliculasCortas = service.consultarPeliculaCorta();
			for (Pelicula pelicula : peliculasCortas) {
				System.out.println(pelicula);
			}

			List<Pelicula> peliculas = service.consultarPeliculas();
			
			for (Pelicula pelicula : peliculas) {
				System.out.println(pelicula);
			}

		} catch (PeliculasException e) {
			e.printStackTrace();
		}

	}

}
