package com.js.freeproject.domain.board.application;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.js.freeproject.domain.amazonS3.S3Service;
import com.js.freeproject.domain.board.domain.Board;
import com.js.freeproject.domain.board.domain.BoardRepository;
import com.js.freeproject.domain.board.dto.BoardListResponse;
import com.js.freeproject.domain.board.dto.BoardRequest;
import com.js.freeproject.domain.board.dto.BoardResponse;
import com.js.freeproject.domain.board.dto.BoardUserResponse;
import com.js.freeproject.domain.comment.application.CommentService;
import com.js.freeproject.domain.file.application.BoardFileService;
import com.js.freeproject.domain.file.domain.BoardFile;
import com.js.freeproject.domain.user.domain.User;
import com.js.freeproject.domain.user.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;
    private final BoardFileService boardFileService;
    private final CommentService commentService;

    @Transactional
    public Board saveQuestion(BoardRequest boardRequest) throws IOException {
        System.out.println("보드리퀘스트"+boardRequest.getDescription());
        User searchUser = userRepository.findById(boardRequest.getUserId()).orElseThrow(IllegalArgumentException::new);
        //Board boardEntity = boardRequest.toEntity();
        Board question = Board.builder()
                .user(searchUser)
                .title(boardRequest.getTitle())
                .description(boardRequest.getDescription())
                .build();
        Board savedBoard = boardRepository.save(question);

        if (boardRequest.getBoardFiles() != null) {
            boardFileService.saveBoardFiles(boardRequest.getBoardFiles(), savedBoard.getId());
        }
        return savedBoard;
    }

    public List<BoardListResponse> findAllBoard() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(BoardListResponse::ofBoard)
                .collect(toList());
    }

    public BoardResponse findById(final Long boardId) {
        Board searchBoard = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 게시판이 존재하지 않습니다."));
        List<BoardFile> searchBoardFiles = boardFileService.findBoardFiles(boardId);
        //댓글들을 담기
        Map<String, List> commentOfBoard = commentService.getCommentOfBoard(searchBoard.getComments());
        //파일 있으면 가져오기
        commentOfBoard.put("files", searchBoardFiles);
        BoardResponse boardResponse = new BoardResponse(searchBoard, commentOfBoard);
        return boardResponse;
    }

    public BoardResponse findContentById(final Long boardId) {
        Board searchBoard = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        List<BoardFile> searchBoardFiles = boardFileService.findBoardFiles(boardId);
        //댓글들을 담기
        Map<String, List> commentOfBoard = commentService.getCommentOfBoard(searchBoard.getComments());
        //파일 있으면 가져오기
        commentOfBoard.put("files", searchBoardFiles);
        BoardResponse boardResponse = new BoardResponse(searchBoard, commentOfBoard);
        return boardResponse;
    }

    @Transactional
    public void updateDescription(final BoardRequest boardRequest, final Long boardId) throws IOException {
        final Board updateBoard = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        updateBoard.updateDescription(boardRequest.toEntity());
        boardFileService.updateBoardFile(boardRequest.getBoardFiles(), boardId);
    }


    public List<BoardUserResponse> findByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        List<Board> boards = boardRepository.findByUserID(user);
        if (boards.isEmpty()) {
            throw new IllegalStateException("해당 유저가 쓴 글이 없습니다.");
        }
        List<BoardUserResponse> boardUserResponse = boards.stream()
                .map(BoardUserResponse::new).collect(toList());
        return boardUserResponse;
    }

    public String saveImage(MultipartFile multipartFile) throws IOException {
        String imageUrl = s3Service.uploadImage(multipartFile, "board");
        return imageUrl;
    }

    @Transactional
    public String deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("해당 게시판이 없습니다."));
        boardRepository.deleteById(id);
        return "success";
    }
}
