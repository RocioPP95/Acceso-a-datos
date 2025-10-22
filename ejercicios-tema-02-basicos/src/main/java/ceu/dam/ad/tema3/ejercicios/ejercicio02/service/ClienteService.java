package ceu.dam.ad.tema3.ejercicios.ejercicio02.service;

import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.tema3.ejercicios.ejercicio02.model.Cliente;
import ceu.dam.ad.tema3.ejercicios.ejercicio02.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	private static final Logger log = LoggerFactory.getLogger(ClienteService.class);

	public Map<String, Cliente> findAll() throws ClientesException {
		try {
			return repo.findAll().stream().collect(Collectors.toMap(c -> c.getEmail(), c -> c));
		} catch (DataAccessException e) {
			throw new ClientesException("Error colsuntando clientes", e);
		}

	}

}
