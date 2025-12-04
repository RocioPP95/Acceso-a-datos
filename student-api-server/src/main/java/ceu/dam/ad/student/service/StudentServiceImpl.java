package ceu.dam.ad.student.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ceu.dam.ad.student.api.request.FilterDto;
import ceu.dam.ad.student.exception.StudentDuplicateException;
import ceu.dam.ad.student.exception.StudentNotFoundException;
import ceu.dam.ad.student.model.Student;
import ceu.dam.ad.student.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepository repository;
	
	@Override
	public Student create(Student student) throws StudentDuplicateException {
		if (repository.findByEmail(student.getEmail()).isPresent()) {
			throw new StudentDuplicateException("A student with the specified email already exists.");
		}
		if (repository.findByDni(student.getDni()).isPresent()) {
			throw new StudentDuplicateException("A student with the specified dni already exists.");
		}
		student.setCreatedDate(LocalDate.now());
		return repository.save(student);
	}

	@Override
	public void remove(Long id) throws StudentNotFoundException {
		findById(id);
		repository.deleteById(id);
	}

	@Override
	public Student findById(Long id) throws StudentNotFoundException {
		return repository.findById(id).orElseThrow(() -> new StudentNotFoundException("No student exists with the specified ID."));
	}

	@Override
	public List<Student> findAll() throws StudentNotFoundException {
		List<Student> students = repository.findAll(Sort.by("dni").ascending());
		if (students.isEmpty()) {
			throw new StudentNotFoundException("No students found");
		}
		return students;
	}

	
	
	
	@Override
	public List<Student> findByAgeRange(Integer minAge, Integer maxAge) {
		LocalDate dateFrom = LocalDate.now().minusYears(maxAge);
		LocalDate dateUntil = LocalDate.now().minusYears(minAge);
		return repository.findByDateOfBirthBetween(dateFrom, dateUntil, Sort.by("dateOfBirth").descending());
	}

	
	
	
	
	@Override
	public List<Student> search(FilterDto filter) {
		 Student student = new ModelMapper().map(filter, Student.class);
		 ExampleMatcher matcher = ExampleMatcher.matching()
				 .withIgnoreCase()
				 .withStringMatcher(StringMatcher.CONTAINING);
		 Example<Student> example = Example.of(student, matcher);
		 List<Student> students = repository.findAll(example);
		
		 if (filter.getAge()!=null) {
			 return students.stream().filter(s -> s.getAge().equals(filter.getAge())).toList();
		 }
		 
		 return students;
	}

	
	
	
	
	
}
