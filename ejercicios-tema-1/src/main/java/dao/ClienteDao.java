package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteDao {

	public List<Cliente> findAll(Connection conn) throws SQLException {
		List<Cliente> result = new ArrayList<>();
		String sql = "select * from customer";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Cliente c = new Cliente();
			c.setId(rs.getInt("customer_id"));
			c.setFirstName(rs.getString("first_name"));
			c.setLastName(rs.getString("last_name"));
			c.setMail(rs.getString("email"));
			c.setActivo(rs.getBoolean("active"));
			result.add(c);
		}
		return result;
	}
}
