package ceu.dam.ad.tema3.ejercicios.ejercicio01.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ceu.dam.ad.tema3.ejercicios.ejercicio01.model.Pelicula;
import ceu.dam.ad.tema3.ejercicios.ejercicio01.repository.PeliculasDao;

public class PeliculasService {

	private static final Logger log = LoggerFactory.getLogger(PeliculasService.class);
	
	private PeliculasDao dao;
	
	public PeliculasService() {
		dao = new PeliculasDao();
	}
	
	
	public List<Pelicula> consultarPeliculas() throws PeliculasException {
		return consultarPeliculas(100);
	}

	public List<Pelicula> consultarPeliculas(Integer longitud) throws PeliculasException {
		try (Connection conn = null){
			return dao.consultarPeliculas(conn)
					.stream()
					.filter(p -> p.getLongitud() < longitud)
					.toList();
		} 
		catch (SQLException e) {
			System.err.println("Error al consultar peliculas");
			throw new PeliculasException("Error al consultar peliculas en BBDD", e);

		}

	}



}
