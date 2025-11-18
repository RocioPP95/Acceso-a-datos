package ceu.dam.ad.users.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDto {

	@NotBlank(message = "el nombre es obligatorio")
	@Size(max = 50, message = "el nombre tiene que tener menos de 50 caracteres")
	private String login;
	@NotBlank(message = "La password nueva no puede ser vacía")
	@Size(min = 8, max = 50, message = "La password tiene que tener una contraseña entre 8 y 20 carácteres")
	private String password;

}
