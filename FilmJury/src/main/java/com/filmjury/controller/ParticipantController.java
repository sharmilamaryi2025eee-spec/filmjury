package com.filmjury.controller;

import com.filmjury.entity.Film;
import com.filmjury.entity.User;
import com.filmjury.service.FilmService;
import com.filmjury.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
@CrossOrigin(origins = "*")
public class ParticipantController {

    private final UserService userService;
    private final FilmService filmService;

    public ParticipantController(
            UserService userService,
            FilmService filmService) {

        this.userService = userService;
        this.filmService = filmService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestBody User user) {

        return ResponseEntity.ok(
                userService.registerParticipant(user)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getParticipant(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.getUser(id)
        );
    }

    @PostMapping("/{participantId}/films")
    public ResponseEntity<Film> submitFilm(
            @PathVariable Long participantId,
            @RequestBody Film film) {

        return ResponseEntity.ok(
                filmService.submitFilm(film, participantId)
        );
    }

    @GetMapping("/{participantId}/films")
    public ResponseEntity<List<Film>> getMyFilms(
            @PathVariable Long participantId) {

        return ResponseEntity.ok(
                filmService.getParticipantFilms(participantId)
        );
    }
}