package ceu.dam.ad.exam.service;

import java.util.List;

import ceu.dam.ad.exam.dto.NewExamRequest;
import ceu.dam.ad.exam.exception.NotEnoughQuestionsException;
import ceu.dam.ad.exam.exception.QuestionNotFoundException;
import ceu.dam.ad.exam.exception.QuestionValidateException;
import ceu.dam.ad.exam.model.Question;
import ceu.dam.ad.exam.model.QuestionType;

public interface QuestionService {

	public static final String TF = "TF";
	public static final String SC = "SC";
	public static final String MC = "MC";

	
	Question createQuestion(Question question) throws QuestionValidateException;

	List<Question> getAllQuestions();

	List<QuestionType> getAllQuestionTypes();

	List<Question> getAllQuestionByType(String typeCode);

	Question getQuestionById(Long id) throws QuestionNotFoundException;

	Question updateQuestion(Question question) throws QuestionNotFoundException, QuestionValidateException;

	void deleteQuestion(Long id);

	List<Question> generateExam(NewExamRequest newExamRequest) throws NotEnoughQuestionsException;

}