package ceu.dam.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ceu.dam.ad.exam.test.Test;

@SpringBootApplication
public class ExamApiServerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ExamApiServerApplication.class, args);
		Test test = context.getBean(Test.class);
		try {
			test.test();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
