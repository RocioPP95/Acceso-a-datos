package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modelo.Student;



@Repository

public interface StudentRepository extends JpaRepository<Student, Long> {

}