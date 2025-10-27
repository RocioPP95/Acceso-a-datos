package ceu.dam.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;

import ceu.dam.ad.model.Postre;

@SpringBootApplication
public class ApiSpringPostresApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSpringPostresApplication.class, args);
	}
	
	
}
