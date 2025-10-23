package ceu.dam.ad.tema3.ejercicios.ejercicio03.test;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ceu.dam.ad.tema3.ejercicios.ejercicio03.model.Pago;
import ceu.dam.ad.tema3.ejercicios.ejercicio03.repository.PagoRepository;
import ceu.dam.ad.tema3.ejercicios.ejercicio03.service.PagosException;
import ceu.dam.ad.tema3.ejercicios.ejercicio03.service.PagosService;

@Component
public class TestEj3 {
	private PagoRepository pagoRepository;
	@Autowired
	private PagosService service;

	TestEj3(PagoRepository pagoRepository) {
		this.pagoRepository = pagoRepository;

	}

	public void test() {
		try {
			Map<String, List<Pago>> mapa = service.consultarPagosClientes();
			List<Pago>pagos=mapa.get("DOROTHY.TAYLOR@sakilacustomer.org");
			for (Pago pago : pagos) {
				System.out.println(pago);
				
			}
		} catch (PagosException e) {
			e.printStackTrace();
		}

	}

}
