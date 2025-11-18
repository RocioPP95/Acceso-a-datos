package ceu.dam.ad.users.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {
	@NotBlank(message = "el nombre es obligatorio")
	@Size(max = 50, message = "el nombre tiene que tener menos de 50 caracteres")
	private String username;
	@NotBlank(message = "el email es obligatorio")
	@Email(message = "El email tiene que tener un formato de correo válido")
	private String email;
	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 8, max = 100, message = "La contraseña tiene que tener entre 8 y 100 caracteres")
	private String password;

}
