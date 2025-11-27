package test;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Service.StudentDuplicateException;
import Service.StudentNotFoundException;
import Service.StudentService;
import dto.FilterDto;
import modelo.Student;

@Component
public class Test {

	@Autowired
	private StudentService service;

	public void test() {
		Student student1, student2, student3;
		Student s1 = Student.builder().dni("92345678A").first_name("Carlos").last_name("García")
				.email("carlos.garcia@example.com").dateOfBirth(LocalDate.of(2002, 5, 14)) // 23 años
				.gender("male").program("DAM").build();

		Student s2 = Student.builder().dni("18765432B").first_name("Laura").last_name("Martínez")
				.email("laura.martinez@example.com").dateOfBirth(LocalDate.of(2000, 11, 3)) // 25 años
				.gender("female").program("DAM").build();

		Student s3 = Student.builder().dni("58765432B").first_name("Julia").last_name("Martín")
				.email("julia.martin@example.com").dateOfBirth(LocalDate.of(2004, 11, 3)) // 21 años
				.gender("female").program("DAM").build();

		List<Student> students;

		try {
			student1 = service.create(s1);
			student2 = service.create(s2);
			student3 = service.create(s3);
			System.out.println("Tres estudiantes creados: ");
			System.out.println(student1);
			System.out.println(student2);
			System.out.println(student3);
		} catch (StudentDuplicateException e) {
			e.printStackTrace();
			return;
		}

		try {
			s1.setDni("aaaaaaa");
			service.create(s1);
			System.out.println("ERROR!!! Me está dejando crear un nuevo alumno con email repetido");
			return;
		} catch (StudentDuplicateException e) {
			System.out.println("Comprobación OK al crear alumno con email repetido");
		}
		try {
			s2.setEmail("aaaaaaa");
			service.create(s2);
			System.out.println("ERROR!!! Me está dejando crear un nuevo alumno con dni repetido");
			return;
		} catch (StudentDuplicateException e) {
			System.out.println("Comprobación OK al crear alumno con dni repetido");

		}

		try {
			Student studentGet = service.findById(student1.getId());
			System.out.println("Alumno consultado por ID correctamente: ");
			System.out.println(studentGet);
		} catch (StudentNotFoundException e) {
			e.printStackTrace();
			return;
		}

		try {
			service.findById(9999999L);
			System.out.println("Error!! Alumno consultado que no existe no lanza excepción");
		} catch (StudentNotFoundException e) {
			System.out.println("Control OK de alumno que no existe");
		}

		try {
			students = service.findAll();
			System.out.println("Deben imprimirse todos los alumnos (tres) ordenados por DNI:");
			students.forEach(System.out::println);
		} catch (StudentNotFoundException e) {
			e.printStackTrace();
		}

		students = service.findByAgeRange(20, 24);
		System.out.println("Deben imprimirse dos alumnos ordenados por edad ascendente");
		students.forEach(System.out::println);

		students = service.findByAgeRange(16, 20);
		if (!students.isEmpty()) {
			System.out.println("ERROR!! Estás devolviendo alumnos para edades que no existen");
			students.forEach(System.out::println);
			return;
		} else {
			System.out.println("Consulta OK por rango de edades que no existen");
		}

		FilterDto f1 = new FilterDto();
		f1.setLastName("mart");
		f1.setEmail("example");
		f1.setGender("male");
		f1.setProgram("DAM");
		students = service.search(f1);
		System.out.println("Deben imprimirse dos alumnas: ");
		students.forEach(System.out::println);

		FilterDto f2 = new FilterDto();
		f2.setProgram("dam");
		f2.setAge(23);
		students = service.search(f2);
		System.out.println("Deben imprimirse un alumno: ");
		students.forEach(System.out::println);

	}

}
