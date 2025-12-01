package ceu.dam.ad.student.dto.request;

import lombok.Data;

@Data
public class FilterDto {

	private String dni;
	private String firstName;
	private String lastName;
	private String email;
	private Integer age;
	private String gender;
	private String program;
}
