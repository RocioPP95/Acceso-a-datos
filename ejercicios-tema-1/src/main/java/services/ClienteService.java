package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.ClienteDao;
import model.Cliente;

public class ClienteService extends Service {
	private ClienteDao dao;

	public ClienteService() {
		dao = new ClienteDao();

	}

	public List<Cliente> findShortFilms() throws ClienteException {
		try (Connection conn = abrirConexionSakilaCentral()) {

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ClienteException("Error colsuntando clientes", e);
		}
		return null;

	}

}
