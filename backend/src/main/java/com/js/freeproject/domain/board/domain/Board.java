package com.js.freeproject.domain.board.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.js.freeproject.domain.comment.domain.Comment;
import com.js.freeproject.domain.file.domain.BoardFile;
import com.js.freeproject.domain.user.domain.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String description;
	private LocalDateTime createDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
//	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
//	private List<BoardPicture> boardPictures = new ArrayList<BoardPicture>();
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<Comment>();

	@JsonManagedReference
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
	private List<BoardFile> boardFiles = new ArrayList<BoardFile>();

	@Builder
	public Board(final String title, final String description, final User user){
		this.title = title;
		this.description = description;
		this.user = user;
		this.createDate = LocalDateTime.now();
	}

	public Board updateDescription(final Board board){
		this.description = board.getDescription();
		this.title = board.getTitle();
		return this;
	}
}
