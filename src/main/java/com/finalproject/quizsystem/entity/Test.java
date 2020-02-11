package com.finalproject.quizsystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="topic_id")
    private Topic topic;

    @OneToMany(mappedBy = "test", orphanRemoval=true, cascade=CascadeType.PERSIST)
    private List<Result> results;

    @OneToMany(mappedBy = "test")
    private List<Question> questions;
}
