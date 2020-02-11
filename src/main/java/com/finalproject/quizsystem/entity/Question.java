package com.finalproject.quizsystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Question {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "question")
    private String question;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

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
