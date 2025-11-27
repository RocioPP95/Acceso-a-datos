package Service;

import java.util.List;

import org.springframework.stereotype.Service;

import dto.FilterDto;
import jakarta.transaction.Transactional;
import modelo.Student;
import repository.StudentRepository;

@Service
public class StudentServiceImp implements StudentService {

	private final StudentRepository studentRepository;

	public StudentServiceImp(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public Student create(Student student) throws StudentDuplicateException {
		studentRepository.save(student);
		return null;
	}

	@Transactional
	@Override
	public void remove(Long id) throws StudentNotFoundException {

	}

	@Override
	public Student findById(Long id) throws StudentNotFoundException {
		return null;
	}

	@Override
	public List<Student> findAll() throws StudentNotFoundException {
		return null;
	}

	@Override
	public List<Student> findByAgeRange(Integer minAge, Integer maxAge) {
		return null;
	}

	@Override
	public List<Student> search(FilterDto filter) {
		return null;
	}

}
