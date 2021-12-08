package com.js.freeproject.domain.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private String content;
    private Long userId;
    private Long parentId;
    private Long boardId;
}
