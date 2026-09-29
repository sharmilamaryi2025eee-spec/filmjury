package com.filmjury.repository;

import com.filmjury.entity.Film;
import com.filmjury.enums.SubmissionStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> findByParticipantId(Long participantId);

    List<Film> findByStatus(SubmissionStatus status);
}