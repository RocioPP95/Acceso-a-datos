package modelo;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.JdbcTypeCode;

import Service.StudentServiceImp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import repository.StudentRepository;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name="students")
public class Student {
	@Id
	@GeneratedValue
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	private Long id;
	private String dni;
	private String first_name;
	private String last_name;
	private String email;
	private Date dateOfBirth;
	private String gender;
	private String program;
	private LocalDate createdAt;

}
