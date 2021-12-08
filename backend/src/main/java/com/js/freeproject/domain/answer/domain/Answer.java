package com.js.freeproject.domain.answer.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.js.freeproject.domain.problem.domain.Problem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    public static Answer createAnswer(String word){
        Answer answer = new Answer(word);
        return answer;
    }

    public Answer(String word) {
        this.word = word;
    }

    public Answer(String word, Problem problem) {
        this.word = word;
        this.problem = problem;
    }
}
