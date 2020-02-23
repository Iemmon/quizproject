package com.finalproject.quizsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="topic_id")
    private Topic topic;

    @OneToMany(mappedBy = "quiz", orphanRemoval=true, cascade=CascadeType.PERSIST)
    private List<Result> results;

    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;
}
