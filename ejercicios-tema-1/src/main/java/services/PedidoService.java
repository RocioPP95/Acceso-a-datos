package services;

import java.sql.Connection;
import java.sql.SQLException;

import dao.LineaPedidoDao;
import dao.PedidoDao;
import model.LineaPedido;
import model.Pedido;

public class PedidoService extends Service {
	private LineaPedidoDao lineaPedido;
	private PedidoDao pedidoDao;

	public PedidoService() {
		this.lineaPedido = new LineaPedidoDao();
		this.pedidoDao = new PedidoDao();
	}

	public void registrarPedido(Pedido pedido) throws PedidoException {
		try (Connection conn = abrirConexionSakila()) {
			conn.setAutoCommit(false);
			try {
				Integer id = pedidoDao.insertarPedido(conn, pedido);
				Integer numLinea = 1;

				for (LineaPedido linea : pedido.getPedidos()) {
					linea.setIdPedido(id);
				linea.setNumLiena(numLinea);
					numLinea++;

				}
				conn.commit();
			} catch (SQLException e) {
				conn.rollback();
				throw e;

			}
		} catch (SQLException e) {
			throw new PedidoException("Error pedido", e);
		}

	}
}
