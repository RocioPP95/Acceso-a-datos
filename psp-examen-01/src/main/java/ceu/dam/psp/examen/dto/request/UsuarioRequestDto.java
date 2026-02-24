package ceu.dam.psp.examen.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRequestDto {

	@NotEmpty
	@Size(max = 30)
	private String identificador;
	@NotEmpty
	private String descripcion;
	@NotEmpty
	@Size(min = 10)
	private String contraseña;

}
