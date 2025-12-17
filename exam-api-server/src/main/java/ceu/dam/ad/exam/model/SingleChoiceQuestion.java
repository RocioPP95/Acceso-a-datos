package ceu.dam.ad.exam.model;

import ceu.dam.ad.exam.exception.QuestionValidateException;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SC")
public class SingleChoiceQuestion extends Question {
	
	
	@Override
	public void validateAnswers() throws QuestionValidateException {
		super.validateAnswers();
		 if (getAnswers().size() < 2 || getCountCorrectAnswers() != 1) {
             throw new QuestionValidateException("Single Choice question must have at least 2 answers with exactly 1 correct");
         }
	}
	
}
