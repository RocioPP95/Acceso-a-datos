package ceu.dam.ad.users.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDto {

	@NotBlank(message = "el nombre es obligatorio")
	@Schema(description = "Username o email con el que se hará loging")

	private String login;
	@NotBlank(message = "La password nueva no puede ser vacía")
	@Schema(description = "Password ren lcaro sin cifrar")

	private String password;

}
