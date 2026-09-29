package com.filmjury.repository;

import com.filmjury.entity.Evaluation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EvaluationRepository
        extends JpaRepository<Evaluation, Long> {

    List<Evaluation> findByFilmId(Long filmId);

    List<Evaluation> findByJuryId(Long juryId);

    Optional<Evaluation> findByFilmIdAndJuryId(
            Long filmId,
            Long juryId
    );

    boolean existsByFilmIdAndJuryId(
            Long filmId,
            Long juryId
    );
}