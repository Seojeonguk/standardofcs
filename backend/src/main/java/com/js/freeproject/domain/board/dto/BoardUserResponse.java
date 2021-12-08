package com.js.freeproject.domain.board.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.js.freeproject.domain.board.domain.Board;

import lombok.Getter;

@Getter
public class BoardUserResponse {
    private Long id;
    private String title;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public BoardUserResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.createdAt = board.getCreateDate();
    }
}