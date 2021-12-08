package com.js.freeproject.domain.problem.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {

    @Override
    @EntityGraph(attributePaths = {"category", "answers"})
    @Query("select p from Problem p where p.id = :id")
    Optional<Problem> findById(@Param("id") Long id);

    @Query("select p from Problem p where p.id >= 103")
    List<Problem> findTest();

    @EntityGraph(attributePaths = {"category"})
    @Query("select p from Problem p where p.description = :description")
    List<Problem> findByDescription (@Param("description") String description);

    @Override
    @EntityGraph(attributePaths = {"category", "problemPicture"})
    List<Problem> findAll();

    @EntityGraph(attributePaths = {"category", "user", "problemPicture"})
    @Query("select p from Problem p where p.category.name = :name and p.status = 'accept'")
    List<Problem> findByCategory(@Param("name") String name, Pageable pageable);

    @EntityGraph(attributePaths = {"category", "answers", "user"})
    @Query("select p from Problem p where p.status = :status")
    List<Problem> findByStatus(@Param("status") ProblemStatus status);



}
