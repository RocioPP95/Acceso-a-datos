package ceu.dam.ad.exam.model;

import java.util.List;

import ceu.dam.ad.exam.exception.QuestionValidateException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "questions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_code")
@Data
public abstract class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_code", nullable = false, insertable = false, updatable = false)
    private QuestionType type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", nullable = false)
    private List<Answer> answers;

    
    protected Integer getCountCorrectAnswers() {
    	return Long.valueOf(answers.stream().filter(a -> a.getCorrect()).count()).intValue();
    }
    
    public void validateAnswers() throws QuestionValidateException {
        if (answers == null || answers.isEmpty()) {
            throw new QuestionValidateException("Question must have answers");
        }
    }
    
    
}
