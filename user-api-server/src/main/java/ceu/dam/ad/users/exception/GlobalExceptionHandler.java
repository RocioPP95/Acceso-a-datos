package ceu.dam.ad.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.log4j.Log4j2;

@RestControllerAdvice
//te instancia el log
@Log4j2
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handle(UserNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

	}

	@ExceptionHandler(Exception.class)

	public ResponseEntity<String> handle(Exception e) {
		log.error("Error inesperado, consultar traza", e);
		return ResponseEntity.internalServerError().body("Error inesperado en el servidor" + e);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handle(MethodArgumentNotValidException e) {
		return ResponseEntity.badRequest().body(e.getFieldError().getField() + e.getFieldError().getDefaultMessage());
	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<String> handle(UserException e) {
		log.error("Error accediendo a BBDD, consultar traza", e);
		return ResponseEntity.internalServerError().body("Error al acceder a la base de datos" + e);
	}
}
