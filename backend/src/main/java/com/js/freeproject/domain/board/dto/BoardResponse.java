package com.js.freeproject.domain.board.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.js.freeproject.domain.board.domain.Board;
import com.js.freeproject.domain.comment.domain.Comment;
import com.js.freeproject.domain.file.domain.BoardFile;
import com.js.freeproject.domain.user.domain.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardResponse {
    private Long id;
    private String title;
    private String description;
    private User user;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private List<Comment> answerComment;
    private List<Comment> coComment;
    private List<BoardFile> boardFiles;

    public BoardResponse(final Board board, final Map<String, List> map) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.user = board.getUser();
        this.createdAt = board.getCreateDate();
        this.answerComment = map.get("answerComment");
        this.coComment = map.get("coComment");
        this.boardFiles = map.get("files");
    }
}
