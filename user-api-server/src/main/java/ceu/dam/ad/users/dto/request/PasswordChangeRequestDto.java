package ceu.dam.ad.users.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordChangeRequestDto {
	@NotBlank(message="La password antigua no puede ser vacía")
	private String oldPassword;
	@NotBlank(message="La password nueva no puede ser vacía")
	@Size(min = 8, max = 50, message="La password tiene que tener una contraseña entre 8 y 20 carácteres")
	private String newPassword;
}
