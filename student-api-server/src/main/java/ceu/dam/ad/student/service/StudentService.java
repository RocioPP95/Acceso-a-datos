package ceu.dam.ad.student.service;

import java.util.List;

import ceu.dam.ad.student.api.request.FilterDto;
import ceu.dam.ad.student.exception.StudentDuplicateException;
import ceu.dam.ad.student.exception.StudentNotFoundException;
import ceu.dam.ad.student.model.Student;

public interface StudentService {

    Student create(Student student) throws StudentDuplicateException;

    void remove(Long id) throws StudentNotFoundException;

    Student findById(Long id) throws StudentNotFoundException;

    List<Student> findAll() throws StudentNotFoundException;

    List<Student> findByAgeRange(Integer minAge, Integer maxAge);

    List<Student> search(FilterDto filter);
}

