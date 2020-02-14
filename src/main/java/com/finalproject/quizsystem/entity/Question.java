package com.finalproject.quizsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "question")
public class Question {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "question")
    private String question;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToMany(mappedBy = "question", orphanRemoval=true, cascade=CascadeType.PERSIST)
    private List<Answer> answers;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Answer a : answers){
            stringBuilder.append(a.toString());
        }
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                stringBuilder.toString();
    }
}
