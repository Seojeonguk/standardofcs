//package com.js.freeproject.domain.file.domain;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.js.freeproject.domain.board.domain.Board;
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class UserFile {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	private String name;
//
//	@Builder
//	public UserFile(Long id, String name) {
//		this.id = id;
//		this.name = name;
//	}
//
//	public UserFile updateFile(final String fileName){
//		this.name = fileName;
//		return this;
//	}
//}
