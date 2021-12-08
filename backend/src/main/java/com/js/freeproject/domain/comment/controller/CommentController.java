package com.js.freeproject.domain.comment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.freeproject.domain.comment.application.CommentService;
import com.js.freeproject.domain.comment.dto.CommentRequest;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @ApiOperation(value = "댓글 및 대댓글 작성 - 댓글 parentId = 0, 대댓글 parentId = commentId")
    @PostMapping
    public ResponseEntity<?> saveComment(@RequestBody CommentRequest commentRequest){
        return ResponseEntity.ok().body(commentService.saveComment(commentRequest));
    }

//    @GetMapping("/{boardId}")
//    public ResponseEntity<?> getComments(@PathVariable final Long boardId){
//        Map<String, List> commentMap = commentService.getCommentOfBoard(boardId);
//        return ResponseEntity.ok().body(commentMap);
//    }
}
