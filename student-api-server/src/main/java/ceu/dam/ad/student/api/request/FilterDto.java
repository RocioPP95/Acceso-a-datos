package ceu.dam.ad.student.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FilterDto {

	private String dni;
	private String firstName;
	private String lastName;
	@Email
	private String email;
	@Positive
	private Integer age;
	private String gender;
	private String program;
}
