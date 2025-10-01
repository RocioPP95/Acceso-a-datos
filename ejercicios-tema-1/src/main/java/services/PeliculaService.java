package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.PeliculaDao;
import model.Pelicula;

public class PeliculaService extends Service {

	private PeliculaDao dao;

	public PeliculaService() {
		dao = new PeliculaDao();

	}

	public List<Pelicula> findShortFilms() throws PeliculaException {
		try (Connection conn = abrirConexionSakila()) {
			return dao.findAll(conn).stream().filter(p -> p.getLongitud() < 100).toList();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PeliculaException("Error colsuntando películas", e);
		}

	}
}
