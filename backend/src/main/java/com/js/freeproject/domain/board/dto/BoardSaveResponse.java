package com.js.freeproject.domain.board.dto;

import com.js.freeproject.domain.board.domain.Board;
import com.js.freeproject.domain.user.domain.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardSaveResponse {
    private Long id;
    private String title;
    private String description;
    private UserResponse user;

    public BoardSaveResponse(final Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.user = new UserResponse(board.getUser());
    }

    @Getter
    static class UserResponse {
        private Long id;
        private String nickname;
        private String image;

        public UserResponse(User user) {
            this.id = user.getId();
            this.nickname = user.getNickName();
            this.image = user.getImage();
        }
    }
}
