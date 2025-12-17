package ceu.dam.ad.exam.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "question_types")
@Data
public class QuestionType {

    @Id
    private String code; 

    private String description;
}
