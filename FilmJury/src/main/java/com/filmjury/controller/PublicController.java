package com.filmjury.controller;

import com.filmjury.entity.Film;
import com.filmjury.service.FilmService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "*")
public class PublicController {

    private final FilmService filmService;

    public PublicController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public ResponseEntity<List<Film>> getFilms() {

        return ResponseEntity.ok(
                filmService.getAllFilms()
        );
    }

    @GetMapping("/films/{id}")
    public ResponseEntity<Film> getFilm(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                filmService.getFilm(id)
        );
    }
}