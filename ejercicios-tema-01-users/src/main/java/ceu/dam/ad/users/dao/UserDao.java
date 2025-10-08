package ceu.dam.ad.users.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ceu.dam.ad.users.model.User;

public class UserDao {

	/** Debe insertar un usuario en BBDD. Devuelve el ID generado. */
	public Long insert(Connection conn, User user) throws SQLException {
		String sql = "insert into pedidos ( username, password, email, fecha_alta, fecha_ult_login) VALUES ( ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setDate(4, Date.valueOf(user.getCreatedDate()));
			stmt.setDate(5, Date.valueOf(user.getLastLoginDate()));

			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			return rs.getLong(1);

		}
		//

	}

	private User rellenar(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setCreatedDate(rs.getDate("fecha_alta").toLocalDate());
		user.setLastLoginDate(rs.getDate("fecha_ult_login").toLocalDate());
		return user;
	}

	/**
	 * Debe consultar un usuario por su email y devolverlo. Si no existe, devolverá
	 * null
	 * 
	 */
	public User getByEmail(Connection conn, String email) throws SQLException {

		String sql = "select * from usuarios where email = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);

		// esto te devuelve todos los datos del usuario que se ha encontrado
		// (( si hubiese mas de un resultado se pone en un while pero como no se utiliza
		// un if))
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			// Aqui se crea y relleno el objeto User con los datos de la consulta(rs)

			return rellenar(rs);

		} else {
			return null;

		}

	}

	/**
	 * Debe consultar un usuario por su ID y devolverlo. Si no existe, devolverá
	 * null. NOTA: no dupliques código
	 */
	public User getById(Connection conn, Long id) throws SQLException {

		String sql = "select * from usuarios where id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {

			return rellenar(rs);

		} else {
			return null;

		}
	}

	/**
	 * Debe consultar un usuario por su email y devolverlo. Si no existe, devolverá
	 * null. NOTA: no dupliques código
	 */
	public User getByUserName(Connection conn, String userName) throws SQLException {
		String sql = "select * from usuarios where username = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, userName);

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {

			return rellenar(rs);

		} else {
			return null;

		}
	}

	/**
	 * Debe actualizar todos los datos de un usuario y devolver el número de
	 * registros actualizados.
	 */
	public Integer update(Connection conn, User user) throws SQLException {
		String sql = "UPDATE usuarios SET username = ?, password = ?, fecha_alta = ?, fecha_ult_login= ? WHERE id = ? ";

		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getPassword());
		stmt.setDate(3, Date.valueOf(user.getCreatedDate()));
		stmt.setDate(4, Date.valueOf(user.getLastLoginDate()));
		stmt.setLong(5, user.getId());

		Integer numeroActualizados = stmt.executeUpdate();
		return numeroActualizados;

	}
}
