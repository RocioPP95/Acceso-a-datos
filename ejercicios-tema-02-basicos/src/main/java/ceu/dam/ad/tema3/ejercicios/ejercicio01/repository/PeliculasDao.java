package ceu.dam.ad.tema3.ejercicios.ejercicio01.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ceu.dam.ad.tema3.ejercicios.ejercicio01.model.Pelicula;

public class PeliculasDao {

	public List<Pelicula> consultarPeliculas(Connection conn) throws SQLException {
		ResultSet rs = null;
		List<Pelicula> resultado = new ArrayList<Pelicula>();
		try (Statement stmt = conn.createStatement()) {
			rs = stmt.executeQuery("select * from film");
			while (rs.next()) {
				Pelicula p = new Pelicula();
				p.setId(rs.getInt("film_id"));
				p.setNombre(rs.getString("title"));
				p.setLongitud(rs.getInt("length"));
				resultado.add(p);
			}
		}
		return resultado;
	}

}
