package ceu.dam.ad.ejerciciosTema2.avanzado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.test.Test2;


@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

		Test2 test = context.getBean(Test2.class);
		test.test();
	}

}
