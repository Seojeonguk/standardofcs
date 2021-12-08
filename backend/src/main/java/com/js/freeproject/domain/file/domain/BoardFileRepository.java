package com.js.freeproject.domain.file.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFile,Long> {
    List<BoardFile> findAllByBoardId(final Long boardId);
}
