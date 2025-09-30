package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Pago;

public class PagoDao {
	public List<Pago> findAll(Connection conn, Integer id) throws SQLException {
		List<Pago> pagos = new ArrayList<>();

		String sql = "select * from payment where customer_id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Pago pago = new Pago();
			pago.setImporte(rs.getInt("amount"));
			pago.setFecha(rs.getDate("fecha").toLocalDate());

			pagos.add(pago);
		}
		return pagos;
	}

}
