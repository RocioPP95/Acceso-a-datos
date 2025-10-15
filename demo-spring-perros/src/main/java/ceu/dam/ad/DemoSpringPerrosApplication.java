package ceu.dam.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ceu.dam.ad.model.Perro;
import ceu.dam.ad.repository.perros.PerrosRepository;
import ceu.dam.ad.service.NotFoundException;
import ceu.dam.ad.service.PerroService;

@SpringBootApplication
public class DemoSpringPerrosApplication {

	private final PerrosRepository perrosRepository;

	private final PerroService perroService;

	DemoSpringPerrosApplication(PerroService perroService, PerrosRepository perrosRepository) {
		this.perroService = perroService;
		this.perrosRepository = perrosRepository;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoSpringPerrosApplication.class, args);
		PerroService service = context.getBean(PerroService.class);

		Perro p = new Perro();
		p.setColor("canela");
		p.setPerone("perone");
		p.setRaza("chucho");

		Perro creado = service.crearPerro(p);
		System.out.println(creado);

		try {

			service.consultarPerro(1L);
			//service.buscarPerrosPorNombre("per");
		} catch (NotFoundException e) {
			System.out.println("El chucho no existe");
		}

	}

}
