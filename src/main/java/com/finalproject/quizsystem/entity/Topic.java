package com.finalproject.quizsystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data

public class Topic {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String topicName;

    @OneToMany(mappedBy = "topic", orphanRemoval=true, cascade=CascadeType.PERSIST)
    private List<Test> tests;
}
