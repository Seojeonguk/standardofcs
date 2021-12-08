package com.js.freeproject.domain.problem.domain;

import java.util.List;

import lombok.Getter;

@Getter
public class AnswerResponse {
    private List<String> proper;
    private List<String> wrong;

    public AnswerResponse(List<String> proper, List<String> wrong) {
        this.proper = proper;
        this.wrong = wrong;
    }
}
