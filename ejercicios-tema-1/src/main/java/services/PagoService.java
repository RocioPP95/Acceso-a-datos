package services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mariadb.jdbc.Connection;

import dao.ClienteDao;
import dao.PagoDao;
import model.Pago;
import model.Cliente;

public class PagoService extends Service {
	private PagoDao daoPagos;
	private ClienteDao daoClientes;

	public PagoService() {
		daoPagos = new PagoDao();
		daoClientes = new ClienteDao();
	}

	public Map<String, List<Pago>> consultarPagosClientes() throws ClientesException {
		Map<String, List<Pago>> mapa = new HashMap<>();

		try (Connection conn = abrirConexionSakila()) {

			List<Cliente> clientes = daoClientes.findAll(conn);

			for (Cliente cliente : clientes) {
				List<Pago> pagosCliente = daoPagos.findAll(conn, cliente.getId());

				mapa.put(cliente.getMail(), pagosCliente);
			}
			return mapa;
		} catch (SQLException e) {
			throw new ClientesException("Error consultando pagos cliente", e);
		}

	}

}
