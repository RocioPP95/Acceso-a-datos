package ceu.dam.ad.tema3.ejercicios.ejercicio02.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ceu.dam.ad.tema3.ejercicios.ejercicio02.model.Cliente;
import ceu.dam.ad.tema3.ejercicios.ejercicio02.repository.ClientesDao;

public class ClienteService {

	private static final Logger log = LoggerFactory.getLogger(ClienteService.class);
	
	private ClientesDao dao;
	
	public ClienteService() {
		dao = new ClientesDao();
	}
	
	
	public Map<String, Cliente> consultarMapaClientes() throws ClientesException{
		try (Connection conn = null){
			List<Cliente> clientes = dao.consultarClientes(conn);
			return clientes.stream().collect(Collectors.toMap(Cliente::getEmail, c->c));
		}
		catch(SQLException e) {
			System.err.println("Error al consultar cliente");
			throw new ClientesException("Error al consultar cliente en BBDD", e);
		}
	}

}
