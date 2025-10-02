package services;

import java.sql.Connection;
import java.sql.SQLException;

import dao.LineaPedidoDao;
import dao.PedidoDao;
import model.Pedido;

public class PedidoService extends Service {
	private LineaPedidoDao lineaPedido;
	private PedidoDao pedidoDao;

	public PedidoService() {
		this.lineaPedido = new LineaPedidoDao();
		this.pedidoDao = new PedidoDao();
	}

	public void registrarPedido(Pedido pedido) throws SQLException {
		try (Connection conn = abrirConexionSakila()) {

			Integer key = pedidoDao.insertarPedido(conn, pedido);

			for (int i = 0; i < pedido.getPedidos().size(); i++) {

			}
		}

	}
}
