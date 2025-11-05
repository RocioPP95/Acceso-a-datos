package ceu.dam.ad;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ceu.dam.ad.model.Domicilio;
import ceu.dam.ad.model.Perro;
import ceu.dam.ad.model.Persona;

import ceu.dam.ad.service.PerroService;

@SpringBootApplication
public class DemoSpringPerrosApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoSpringPerrosApplication.class, args);
		PerroService service = context.getBean(PerroService.class);
		try {
			Persona persona = new Persona();
			persona.setDni("12345678B");
			persona.setNombre("Blas de los Montes");
			persona.setPerros(new ArrayList<Perro>());
			for (int i = 1; i <= 3; i++) {
				Perro p = new Perro();
				p.setNombre("Perro " + i);
				p.setNumChip(i + 10000 + "A");
				p.setRaza("Chucho");
				p.setVacunado(true);
				persona.getPerros().add(p);
			}
			persona.setDomicilio(new Domicilio(null, "ole", 34));

			service.crearPersona(persona);

			Persona personaCreada = service.consultarPersona(persona.getIdPersona());
			System.out.println(personaCreada);
			System.out.println();
			personaCreada.getPerros().forEach(System.out::println);

		} catch (Exception e) {
			System.out.println("Petardazo gordo");
			e.printStackTrace();
		}

	}

}
