package app;

import java.util.List;

import model.Pago;
import services.ClientesException;
import services.PagoService;

public class AppPago {
	public static void main(String[] args) {

		PagoService service = new PagoService();

		try {
			List<Pago> pagos = service.consultarPagosClientes().get("MARILYN.ROSS@sakilacustomer.org");
			pagos.forEach(System.out::println);
		} catch (ClientesException e) {
			e.printStackTrace();
		}

	}

}
