package com.filmjury.service;

import com.filmjury.entity.Film;
import com.filmjury.entity.User;
import com.filmjury.enums.SubmissionStatus;
import com.filmjury.exception.ResourceNotFoundException;
import com.filmjury.repository.FilmRepository;
import com.filmjury.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    public FilmService(
            FilmRepository filmRepository,
            UserRepository userRepository) {

        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    public Film submitFilm(
            Film film,
            Long participantId) {

        User participant = userRepository
                .findById(participantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Participant not found with id: "
                                        + participantId
                        )
                );

        film.setParticipant(participant);
        film.setStatus(SubmissionStatus.SUBMITTED);

        return filmRepository.save(film);
    }

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public Film getFilm(Long id) {

        return filmRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Film not found with id: " + id
                        )
                );
    }

    public List<Film> getParticipantFilms(
            Long participantId) {

        return filmRepository
                .findByParticipantId(participantId);
    }

    public Film updateStatus(
            Long id,
            SubmissionStatus status) {

        Film film = getFilm(id);

        film.setStatus(status);

        return filmRepository.save(film);
    }

    public void deleteFilm(Long id) {

        if (!filmRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Film not found with id: " + id
            );
        }

        filmRepository.deleteById(id);
    }
}