package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dao.ClienteDao;
import model.Cliente;

public class ClienteService extends Service {
	private ClienteDao dao;

	public ClienteService() {
		dao = new ClienteDao();

	}

	public Map<String, Cliente> consultarCLientes() throws ClienteException {
		try (Connection conn = abrirConexionSakilaCentral()) {
			List<Cliente> lista = dao.findAll(conn);
			return lista.stream().collect(Collectors.toMap(c -> c.getMail(), c -> c));

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ClienteException("Error colsuntando clientes", e);
		}

	}

}
