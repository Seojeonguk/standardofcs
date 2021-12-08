package com.js.freeproject.domain.category.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.js.freeproject.domain.problem.domain.Problem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	private String description;

	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private List<Problem> problems = new ArrayList<Problem>();

	public Category(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
