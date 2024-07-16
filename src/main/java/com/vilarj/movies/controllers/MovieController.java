package com.vilarj.movies.controllers;

import com.vilarj.movies.entities.Movie;
import com.vilarj.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling movie-related API requests.
 * <summary>
 * This class exposes endpoints for fetching movie data from TMDB (The Movie Database)
 * based on the configured API key in the MovieService. It provides functionalities
 * to retrieve popular movies, search movies by title, and get trending movies for a
 * specified time window.
 * </summary>
 */
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Fetches a list of popular movies.
     * <summary>
     * This endpoint retrieves a list of popular movies from TMDB.
     *
     * @return A list of Movie objects containing details about popular movies.
     *         An empty list is returned if an error occurs during the API call.
     * </summary>
     */
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    /**
     * Retrieves a list of movies matching the provided title.
     * <summary>
     * This method performs a case-insensitive strict title search for movies using the TMDB API.
     * If a matching movie is found, a single `Movie` object is wrapped in a `List` and returned.
     * Otherwise, an empty list is returned.
     * </summary>
     * <p>
     * In case of network errors or API issues during the TMDB call, a `RuntimeException` is thrown.
     *
     * @param title {String} - The exact title of the movie to search for.
     * @return A `List` containing a single `Movie` object if found, otherwise an empty list.
     * @throws RuntimeException Thrown if an error occurs while fetching data from TMDB.
     *                          </p>
     */
    @GetMapping("/getExactMovieByTitle")
    public List<Movie> getExactMovieByTitle(String title) {
        return movieService.getExactMovieByTitle(title);
    }

    /**
     * Searches for movies by title.
     *  <summary>
     * This endpoint searches for movies based on the provided title string.
     *
     * @param title {String} - The title of the movie to search for.
     * @return A list of Movie objects containing details about movies matching the search title.
     *         An empty list is returned if an error occurs during the API call or no movies are found.
     * </summary>
     */
    @GetMapping("/getMovieByTitle")
    public List<Movie> getMovieByTitle(String title) {
        return movieService.getMovieByTitle(title);
    }

    /**
     * Fetches a list of trending movies for a specified time window.
     * <summary>
     * This endpoint retrieves a list of trending movies on TMDB for a specified time window.
     * The time window parameter can be either "day" or "week".
     *
     * @param time_window {String} - The time window for trending movies (literal string: "day" or "week").
     * @return A list of Movie objects containing details about trending movies.
     *         An empty list is returned if an error occurs during the API call or
     *         if the provided time window is invalid.
     * </summary>
     */
    @GetMapping("/getTrendingMovies")
    public List<Movie> getLatest(String time_window) {
        return movieService.getTrendingMovies(time_window);
    }

}
