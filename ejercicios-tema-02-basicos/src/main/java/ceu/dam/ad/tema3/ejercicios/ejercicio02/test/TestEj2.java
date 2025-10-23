package ceu.dam.ad.tema3.ejercicios.ejercicio02.test;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ceu.dam.ad.tema3.ejercicios.ejercicio02.model.Cliente;
import ceu.dam.ad.tema3.ejercicios.ejercicio02.repository.ClienteRepository;
import ceu.dam.ad.tema3.ejercicios.ejercicio02.service.ClienteService;
import ceu.dam.ad.tema3.ejercicios.ejercicio02.service.ClientesException;

@Component
public class TestEj2 {

	private ClienteRepository clienteRepository;
	@Autowired
	private ClienteService service;

	TestEj2(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;

	}

	public void test() {
		try {
			Map<String, Cliente> clientes = service.findAll();
			System.out.println("Tamaño del mapa: " + clientes.size());
			for (Cliente cliente : clientes.values()) {
				System.out.println(cliente);
			}

		} catch (ClientesException e) {
			e.printStackTrace();
		}
	}

}
