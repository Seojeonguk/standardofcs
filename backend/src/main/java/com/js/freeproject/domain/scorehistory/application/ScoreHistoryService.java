package com.js.freeproject.domain.scorehistory.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.js.freeproject.domain.category.domain.Category;
import com.js.freeproject.domain.scorehistory.domain.ScoreHistory;
import com.js.freeproject.domain.scorehistory.domain.ScoreHistoryRepository;
import com.js.freeproject.domain.scorehistory.domain.ScoreHistoryResponse;
import com.js.freeproject.domain.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScoreHistoryService {
	private final ScoreHistoryRepository scoreHistoryRepo;
	
	public List<ScoreHistoryResponse> getScore(User user) {
		List<ScoreHistory> scoreHistories = scoreHistoryRepo.findByUser(user);
		return scoreHistories.stream().map(ScoreHistoryResponse::new).collect(Collectors.toList());
	}
	
	public List<ScoreHistoryResponse> getScoreCategory(User user, Category category) {
		List<ScoreHistory> scoreHistories = scoreHistoryRepo.findByCategoryAndUser(category, user);
		return scoreHistories.stream().map(ScoreHistoryResponse::new).collect(Collectors.toList());
	}
	
	public void saveScore(User user,Long cateogry_id, Integer score) {
		Category category = new Category();
		category.setId(cateogry_id);
		ScoreHistory scoreHistory = ScoreHistory.builder().score(score).user(user).category(category).build();
		
		scoreHistoryRepo.save(scoreHistory);
	}
}
