package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.LineaPedido;

public class LineaPedidoDao {
	public void insertarLineaPedido(Connection conn, LineaPedido linea) throws SQLException {
		String sql = "insert into pedidos_lineas (id_pedido, numero_linea, articulo, precio) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, linea.getIdPedido());
			stmt.setInt(2, linea.getNumLiena());
			stmt.setString(3, linea.getArticulo());
			stmt.setBigDecimal(4, linea.getPrecio());
			stmt.execute();
		}
	}
}
