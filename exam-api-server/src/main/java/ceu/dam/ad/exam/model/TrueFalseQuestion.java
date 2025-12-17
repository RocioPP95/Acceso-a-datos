package ceu.dam.ad.exam.model;

import ceu.dam.ad.exam.exception.QuestionValidateException;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TF")
public class TrueFalseQuestion extends Question {
	
	
	@Override
	public void validateAnswers() throws QuestionValidateException {
		super.validateAnswers();
		if (getAnswers().size() != 2 || getCountCorrectAnswers() != 1) {
            throw new QuestionValidateException("True/False question must have exactly 2 answers with 1 correct");
        }
	}
}
