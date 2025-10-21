package ceu.dam.ad.tema3.ejercicios.ejercicio02.test;

import java.util.Map;

import ceu.dam.ad.tema3.ejercicios.ejercicio02.model.Cliente;
import ceu.dam.ad.tema3.ejercicios.ejercicio02.service.ClienteService;
import ceu.dam.ad.tema3.ejercicios.ejercicio02.service.ClientesException;

public class TestEj2 {

	public void test() {
		ClienteService service = new ClienteService();
		try {
			Map<String, Cliente> clientes = service.consultarMapaClientes();
			Cliente cliente = clientes.get("MARILYN.ROSS@sakilacustomer.org");
			System.out.println(cliente);
		
		} catch (ClientesException e) {
			e.printStackTrace();
		}
	}

}
