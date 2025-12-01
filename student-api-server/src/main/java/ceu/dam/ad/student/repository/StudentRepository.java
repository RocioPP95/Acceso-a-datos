package ceu.dam.ad.student.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.student.model.Student;
import java.time.LocalDate;



@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	public Optional<Student> findByEmail(String email);

	public Optional<Student> findByDni(String email);

	public List<Student> findByDateOfBirthBetween(LocalDate dateFrom, LocalDate dateUntil, Sort sort);
	
}
