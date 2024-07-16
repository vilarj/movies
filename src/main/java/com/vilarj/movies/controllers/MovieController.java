package com.vilarj.movies.controllers;

import com.vilarj.movies.entities.Movie;
import com.vilarj.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/getMovieByTitle")
    public List<Movie> getMovieByTitle(String title) {
        return movieService.getMovieByTitle(title);
    }

}
