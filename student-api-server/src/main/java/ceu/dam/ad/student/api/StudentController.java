package ceu.dam.ad.student.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ceu.dam.ad.student.api.dto.response.StudentResponseDto;
import ceu.dam.ad.student.api.request.FilterDto;
import ceu.dam.ad.student.api.request.StudentRequestDto;
import ceu.dam.ad.student.exception.StudentDuplicateException;
import ceu.dam.ad.student.exception.StudentNotFoundException;
import ceu.dam.ad.student.model.Student;
import ceu.dam.ad.student.service.StudentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
@SecurityRequirement(name="Authorization")
public class StudentController {

	@Autowired
	private StudentServiceImpl service;

	@Operation(summary = "Crea Estudiantes", description = "Permite crear nuevos estudiantes")
	@PostMapping("")
	public StudentResponseDto postEstudent(@Valid @RequestBody StudentRequestDto studentDto)
			throws StudentDuplicateException {
		Student studentEntity = new ModelMapper().map(studentDto, Student.class);
		service.create(studentEntity);
		return new ModelMapper().map(studentEntity, StudentResponseDto.class);

	}

	@Operation(summary = "Consulta Estudiante", description = "Permite consultar un  estudiante por su ID")
	@GetMapping("/{id}")
	public StudentResponseDto getEstudent(@PathVariable Long id) throws StudentNotFoundException {
		Student studentEntity = service.findById(id);
		return new ModelMapper().map(studentEntity, StudentResponseDto.class); 

	}

	@Operation(summary = "Consulta Estudiantes", description = "Permite consultar todos estudiantes")

	@GetMapping("")
	public List<StudentResponseDto> getEstudents() throws StudentNotFoundException {
		List<Student> students = service.findAll();
		return students.stream().map(student -> new ModelMapper().map(student, StudentResponseDto.class)).toList();
	}

	@Operation(summary = "Borra Estudiantes", description = "Permite borrar estudiantes")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) throws StudentNotFoundException {

		service.remove(id);
	}

	@Operation(summary = "Consulta Estudiantes por edad", description = "Permite consultar estudiantes por rango de edad")
	@GetMapping("/age")
	public List<StudentResponseDto> getByAgeRange(@RequestParam(required = false) Integer min,
			@RequestParam(required = false) Integer max) {
		if (min == null) {
			min = 0;
		}
		if (max == null) {
			max = Integer.MAX_VALUE;
		}

		List<Student> students = service.findByAgeRange(min, max);

		return students.stream().map(s -> new ModelMapper().map(s, StudentResponseDto.class)).toList();
	}

	@Operation(summary = "Consulta Estudiantes por filtro", description = "Permite consultar estudiantes por un filtro")
	@PostMapping("/search")
	public List<StudentResponseDto> search(@RequestBody @Valid FilterDto filter) {

		List<Student> students = service.search(filter);

		return students.stream().map(s -> new ModelMapper().map(s, StudentResponseDto.class)).toList();
	}

}
