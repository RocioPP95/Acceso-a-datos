package ceu.dam.psp.examen.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequestDto {
	@NotEmpty(message = "El username es obligatorio")
	@Schema(description = "Username o email")
	private String identificador;
	
	@NotEmpty(message = "El password es obligatorio")
	@Schema(description = "Password en claro sin cifrar")
	private String contraseña;

}
