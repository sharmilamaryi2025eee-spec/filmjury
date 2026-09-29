package com.filmjury.service;

import com.filmjury.entity.Evaluation;
import com.filmjury.entity.Film;
import com.filmjury.entity.User;
import com.filmjury.enums.SubmissionStatus;
import com.filmjury.exception.ResourceNotFoundException;
import com.filmjury.repository.EvaluationRepository;
import com.filmjury.repository.FilmRepository;
import com.filmjury.repository.UserRepository;
import com.filmjury.util.ScoreUtil;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    public EvaluationService(
            EvaluationRepository evaluationRepository,
            FilmRepository filmRepository,
            UserRepository userRepository) {

        this.evaluationRepository = evaluationRepository;
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    public Evaluation createEvaluation(
            Evaluation evaluation,
            Long filmId,
            Long juryId) {

        if (evaluationRepository
                .existsByFilmIdAndJuryId(filmId, juryId)) {

            throw new RuntimeException(
                    "This jury member has already evaluated this film"
            );
        }

        Film film = filmRepository
                .findById(filmId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Film not found with id: " + filmId
                        )
                );

        User jury = userRepository
                .findById(juryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Jury member not found with id: "
                                        + juryId
                        )
                );

        validateScore(evaluation.getStoryScore());
        validateScore(evaluation.getDirectionScore());
        validateScore(evaluation.getActingScore());
        validateScore(evaluation.getCinematographyScore());
        validateScore(evaluation.getEditingScore());
        validateScore(evaluation.getSoundScore());
        validateScore(evaluation.getCreativityScore());
        validateScore(evaluation.getOverallScore());

        double total = ScoreUtil.calculateTotal(
                evaluation.getStoryScore(),
                evaluation.getDirectionScore(),
                evaluation.getActingScore(),
                evaluation.getCinematographyScore(),
                evaluation.getEditingScore(),
                evaluation.getSoundScore(),
                evaluation.getCreativityScore(),
                evaluation.getOverallScore()
        );

        evaluation.setFilm(film);
        evaluation.setJury(jury);
        evaluation.setTotalScore(total);

        film.setStatus(SubmissionStatus.EVALUATED);

        filmRepository.save(film);

        return evaluationRepository.save(evaluation);
    }

    private void validateScore(Integer score) {

        if (score == null || score < 0 || score > 10) {
            throw new RuntimeException(
                    "Each score must be between 0 and 10"
            );
        }
    }

    public List<Evaluation> getFilmEvaluations(
            Long filmId) {

        return evaluationRepository
                .findByFilmId(filmId);
    }

    public List<Evaluation> getJuryEvaluations(
            Long juryId) {

        return evaluationRepository
                .findByJuryId(juryId);
    }

    public List<Evaluation> getAllEvaluations() {

        return evaluationRepository.findAll();
    }
}