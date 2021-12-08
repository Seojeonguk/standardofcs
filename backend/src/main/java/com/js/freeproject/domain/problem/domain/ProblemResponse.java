package com.js.freeproject.domain.problem.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ProblemResponse {
    private Long id;
    private String description;
    private List<String> problemPicture;
    private String user;

    public ProblemResponse(Problem problem) {
        this.problemPicture = new ArrayList<>();
        this.id = problem.getId();
        this.description = problem.getDescription();
        problem.getProblemPicture().stream().forEach(o -> this.problemPicture.add(o.getImage()));
        this.user = problem.getUser().getName();
    }
}
