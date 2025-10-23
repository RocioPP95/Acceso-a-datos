package ceu.dam.ad.tema3.ejercicios.ejercicio03.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.tema3.ejercicios.ejercicio02.model.Cliente;
import ceu.dam.ad.tema3.ejercicios.ejercicio02.repository.ClienteRepository;
import ceu.dam.ad.tema3.ejercicios.ejercicio03.model.Pago;
import ceu.dam.ad.tema3.ejercicios.ejercicio03.repository.PagoRepository;

@Service
public class PagosService {
	@Autowired
	private PagoRepository repoPago;

	@Autowired
	private ClienteRepository repoCliente;

	public Map<String, List<Pago>> consultarPagosClientes() throws PagosException {
		Map<String, List<Pago>> mapa = new HashMap<>();
		try {

			List<Cliente> clientes = repoCliente.findAll();
			for (Cliente cliente : clientes) {
				List<Pago> pagosCliente = repoPago.findAllByIdCliente(cliente.getId());

				mapa.put(cliente.getEmail(), pagosCliente);

			}

			return mapa;
		} catch (DataAccessException e) {
			throw new PagosException("Error colsuntando pagos de clientes", e);
		}

	}

}
