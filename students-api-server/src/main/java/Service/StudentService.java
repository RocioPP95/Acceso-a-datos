package Service;

import java.util.List;

import dto.FilterDto;
import modelo.Student;

public interface StudentService {

    Student create(Student student) throws StudentDuplicateException;

    void remove(Long id) throws StudentNotFoundException;

    Student findById(Long id) throws StudentNotFoundException;

    List<Student> findAll() throws StudentNotFoundException;

    List<Student> findByAgeRange(Integer minAge, Integer maxAge);

    List<Student> search(FilterDto filter);
}
