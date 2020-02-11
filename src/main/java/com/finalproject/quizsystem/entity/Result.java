package com.finalproject.quizsystem.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Result {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "score")
    private Integer score;

}
