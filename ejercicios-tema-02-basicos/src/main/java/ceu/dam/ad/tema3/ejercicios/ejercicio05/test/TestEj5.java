package ceu.dam.ad.tema3.ejercicios.ejercicio05.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ceu.dam.ad.tema3.ejercicios.ejercicio05.model.User;
import ceu.dam.ad.tema3.ejercicios.ejercicio05.service.DuplicateUserException;
import ceu.dam.ad.tema3.ejercicios.ejercicio05.service.UserException;
import ceu.dam.ad.tema3.ejercicios.ejercicio05.service.UserNotFoundException;
import ceu.dam.ad.tema3.ejercicios.ejercicio05.service.UserService;
import ceu.dam.ad.tema3.ejercicios.ejercicio05.service.UserUnauthorizedException;

@Component


public class TestEj5 {

	@Autowired
	UserService service;
	
	public void test() {
		User user = new User();
		user.setEmail("test@ceu.es");
		user.setUsername("testceu");
		user.setPassword("ceu");
		try {
			service.createUser(user);
		} catch (DuplicateUserException e) {
			System.out.println("ERRROR::: Revisa que no tengas ya en BBDD un usuario con estos datos: ");
			System.out.println(user);
			System.out.println("Si lo tienes, bórralo y vuelve a ejecutar el test. Si no, revisa el error que sale a continuación: ");
			e.printStackTrace();
			return;
		} catch (UserException e) {
			e.printStackTrace();
			return;
		}
		try {
			service.login("test@ceu.es", "ceu2");
			System.out.println("ERROR: Me estás dejando hacer login con un password incorrecto");
			return;
		} 
		catch(UserUnauthorizedException e) {
			System.out.println("Control de password OK");
		}
		catch (UserNotFoundException | UserException e) {
			e.printStackTrace();
			return;
		}
		try {
			service.login("test2@ceu.es", "ceu");
			System.out.println("ERROR: Me estás dejando hacer login con un EMAIL incorrecto");
			return;
		}
		catch(UserNotFoundException e) {
			System.out.println("Control de email incorrecto OK");
		}
		catch (UserUnauthorizedException | UserException e) {
			e.printStackTrace();
			return;
		}
		try {
			user = service.login("test@ceu.es", "ceu");
			System.out.println("Login OK");
			service.changePassword(user.getId(), "ceu", "ceu2");
			System.out.println("Cambio de password ok");
		} catch (UserNotFoundException | UserUnauthorizedException | UserException e) {
			e.printStackTrace();
			return;
		}
		try {
			service.login("test@ceu.es", "ceu2");
			System.out.println("Login OK tras cambiar password");
		} catch (UserNotFoundException | UserUnauthorizedException | UserException e) {
			e.printStackTrace();
			return;
		}
	
	}
}