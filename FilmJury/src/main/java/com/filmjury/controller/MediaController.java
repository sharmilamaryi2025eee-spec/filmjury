package com.filmjury.controller;

import com.filmjury.entity.Film;
import com.filmjury.service.FilmService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/media")
@CrossOrigin(origins = "*")
public class MediaController {

    private final FilmService filmService;

    public MediaController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/{filmId}")
    public ResponseEntity<Film> getMedia(
            @PathVariable Long filmId) {

        return ResponseEntity.ok(
                filmService.getFilm(filmId)
        );
    }
}