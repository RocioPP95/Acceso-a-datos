package ceu.dam.ad.tema3.ejercicios.ejercicio01.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.tema3.ejercicios.ejercicio01.model.Pelicula;
import ceu.dam.ad.tema3.ejercicios.ejercicio01.repository.PeliculaRepository;

@Service
public class PeliculasService {

	@Autowired
	private PeliculaRepository repo;

	public List<Pelicula> consultarPeliculaCorta(Integer id) throws PeliculasException {
		try {
			return repo.findAll().stream().filter(p -> p.getLongitud() < 100).toList();
		} catch (DataAccessException e) {
			System.err.println("Error alconsultar pelicula");
			throw new PeliculasException();

		}

	}

	public List<Pelicula> consultarPeliculas(Integer id) throws PeliculasException {
		try {
			return repo.findAll();
		} catch (DataAccessException e) {
			System.err.println("Error alconsultar pelicula");
			throw new PeliculasException();

		}

	}
}
