package ceu.dam.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ceu.dam.ad.student.test.Test;

@SpringBootApplication
public class StudentApiServerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(StudentApiServerApplication.class, args);
		Test test = context.getBean(Test.class);
		test.test();
	} 

}
