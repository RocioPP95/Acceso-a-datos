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

	public List<Pelicula> findShortFilms() {
		// 1. Abrir conexión
		try (Connection conn = abrirConexionSakilaCentral()) {
			// 2. Invocar el método findAll del dao --> dao.findAll(conn)
			dao.findAll(conn);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 3. Con la lista que me devuelve el anterior --> Filtrar películas
		// 4. Devolver la lista filtrada
		// 5. Capturar excepciones y lanzar las que se consideren
		return null;
	}
}
