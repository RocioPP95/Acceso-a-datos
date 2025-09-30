package app;

import model.Cliente;
import services.ClienteException;
import services.ClienteService;

public class AppCliente {

	public static void main(String[] args) {

		ClienteService service = new ClienteService();

		try {
			Cliente cliente = service.consultarCLientes().get("MARILYN.ROSS@sakilacustomer.org");
			System.out.println(cliente);
		} catch (ClienteException e) {
			e.printStackTrace();
		}

	}
}
