package dto;

import java.sql.Date;
import java.time.LocalDate;

import lombok.Data;

@Data
public class FilterDto {
	private String dni;
	private String firstName;
	private String lastName;
	private String email;
	private Date dateOfBirth;
	private String gender;
	private String program;
	private LocalDate createdAt;

}
