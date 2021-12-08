package com.js.freeproject.domain.problem.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ProblemStatusResponse {
    private Long id;
    private String description;
    private String user;
    private List<String> answers;

    public ProblemStatusResponse(Problem problem) {
        this.answers = new ArrayList<>();
        this.id = problem.getId();
        this.description = problem.getDescription();
        this.user = problem.getUser().getName();
        problem.getAnswers().stream().forEach(o -> this.answers.add(o.getWord()));
    }
}
