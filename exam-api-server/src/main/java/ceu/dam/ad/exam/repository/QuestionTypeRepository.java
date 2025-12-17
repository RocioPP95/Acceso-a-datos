package ceu.dam.ad.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.exam.model.QuestionType;


@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionType, String> {
	
}

