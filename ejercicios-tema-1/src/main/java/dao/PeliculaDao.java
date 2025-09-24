package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Pelicula;

public class PeliculaDao {

	public List<Pelicula> findAll(Connection conn) throws SQLException {
		List<Pelicula> result = new ArrayList<>();
		String sql = "select * from film";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Pelicula p = new Pelicula();
			p.setId(rs.getInt("film_id"));
			p.setTitulo(rs.getString("title"));
			p.setLongitud(rs.getInt("length"));
			result.add(p);
		}
		return result;
	}
}
