package com.js.freeproject.domain.scorehistory.domain;

import java.text.SimpleDateFormat;

import lombok.Getter;

@Getter
public class ScoreHistoryResponse {

	private Integer score;
	private String createDate;
	static SimpleDateFormat date = new SimpleDateFormat("MM/dd");

	public ScoreHistoryResponse(ScoreHistory scoreHistory) {
		this.score = scoreHistory.getScore();
		this.createDate = date.format(scoreHistory.getCreatedate())+"\n"+scoreHistory.getCategory().getName();
	}
}
