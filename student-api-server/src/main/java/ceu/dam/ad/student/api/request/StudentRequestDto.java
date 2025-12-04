package ceu.dam.ad.student.api.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentRequestDto {
	@NotBlank(message = "el dni es obligatorio")
	@Size(max = 20, message = "el dni tiene que tener menos de 20 caracteres")
	private String dni;
	@NotBlank(message = "el nombre es obligatorio")
	@Size(max = 100, message = "el nombre tiene que tener menos de 100 caracteres")
	private String firstName;
	@NotBlank(message = "el apellido es obligatorio")
	@Size(max = 100, message = "el apellido tiene que tener menos de 100 caracteres")
	private String lastName;
	@NotBlank(message = "el email es obligatorio")
	@Email(message = "El email tiene que tener un formato de correo válido")
	private String email;
	@NotNull
	@PastOrPresent
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	private String gender;
	private String program;

}
