package ceu.dam.ad.exam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceu.dam.ad.exam.dto.NewExamRequest;
import ceu.dam.ad.exam.exception.NotEnoughQuestionsException;
import ceu.dam.ad.exam.exception.QuestionNotFoundException;
import ceu.dam.ad.exam.exception.QuestionValidateException;
import ceu.dam.ad.exam.model.Question;
import ceu.dam.ad.exam.model.QuestionType;
import ceu.dam.ad.exam.repository.QuestionRepository;
import ceu.dam.ad.exam.repository.QuestionTypeRepository;
import jakarta.transaction.Transactional;

@Service
public class QuestionServiceImpl implements QuestionService  {

	@Autowired
    private QuestionRepository questionRepository;
	@Autowired
	private QuestionTypeRepository questionTypeRepository;

	@Override
	@Transactional
    public Question createQuestion(Question question) throws QuestionValidateException {
    	question.validateAnswers();
        return questionRepository.save(question);
    }

    @Override
	public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
	public List<QuestionType> getAllQuestionTypes() {
    	return questionTypeRepository.findAll();
    }
    
    @Override
	public List<Question> getAllQuestionByType(String typeCode) {
    	return questionRepository.findByTypeCode(typeCode);
    }

    @Override
	public Question getQuestionById(Long id) throws QuestionNotFoundException {
        return questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException("Question not found"));
    }
    
    @Override
	@Transactional
    public Question updateQuestion(Question question) throws QuestionNotFoundException, QuestionValidateException {
        if (question.getId() == null) {
            throw new QuestionNotFoundException("Question ID cannot be null for update");
        }
        getQuestionById(question.getId());
        question.validateAnswers();
        return questionRepository.save(question);
    }

    @Override
	@Transactional
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
    
    @Override
    public List<Question> generateExam(NewExamRequest newExamRequest) throws NotEnoughQuestionsException{
    	if (newExamRequest.getMcQuestions() < 1 && 
    			newExamRequest.getTfQuestions() < 1 &&
    			newExamRequest.getScQuestions() < 1) {
    		return new ArrayList<>();
    	}
    	List<Question> exam = new ArrayList<>();
    	exam.addAll(generateExamByType(newExamRequest.getTfQuestions(), TF));
    	exam.addAll(generateExamByType(newExamRequest.getScQuestions(), SC));
    	exam.addAll(generateExamByType(newExamRequest.getMcQuestions(), MC));
    	return exam;
    }
    
    private List<Question> generateExamByType(Integer quantity, String typeCode) throws NotEnoughQuestionsException{
    	List<Question> generatedQuestions = getAllQuestionByType(typeCode);
    	if (generatedQuestions.size() < quantity) {
    		throw new NotEnoughQuestionsException("Not enought true/false question for request");
    	}
    	List<Question> exam = new ArrayList<>();
    	Random rd = new Random();
    	for (int i = 1; i <= quantity;) {
    		int rdQuestion = rd.nextInt(0, generatedQuestions.size());
    		Question q = generatedQuestions.get(rdQuestion);
    		if (!generatedQuestions.contains(q)) {
    			exam.add(q);
    			i++;
    		}
		}
    	return exam;
    }
    
    
    
    

}

