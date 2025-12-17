package ceu.dam.ad.exam.model;

import ceu.dam.ad.exam.exception.QuestionValidateException;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MC")
public class MultipleChoiceQuestion extends Question {
	
	@Override
	public void validateAnswers() throws QuestionValidateException {
		super.validateAnswers();
		if (getAnswers().size() < 2 || getCountCorrectAnswers() < 1) {
            throw new IllegalArgumentException("Multiple Choice question must have at least 2 answers with at least 1 correct");
        }
	}
	
}