package ceu.dam.ad.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handle(UserNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handle(Exception e) {
		return ResponseEntity.internalServerError().body("Error inesperado en el servidor");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handle(MethodArgumentNotValidException e) {
		return ResponseEntity.badRequest().body(e.getFieldError().getField() + e.getFieldError().getDefaultMessage());
	}

}
