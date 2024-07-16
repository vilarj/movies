package com.vilarj.movies.services;

import com.vilarj.movies.constants.Constants;
import com.vilarj.movies.entities.Movie;
import com.vilarj.movies.interfaces.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.vilarj.movies.validators.Validation;

/**
 * Service class for interacting with TMDB (The Movie Database) movie data.
 * <p>
 * This class provides methods to retrieve movie data from TMDB using a configured
 * API key. It offers functionalities to fetch popular movies, search movies by title,
 * and retrieve trending movies for a specified time window.
 * </p>
 */
@Service
public class MovieService {

    private final MovieRepository MOVIE_REPOSITORY;
    private RestTemplate restTemplate;

    @Autowired
    public MovieService(MovieRepository movieRepository, RestTemplate template) {
        this.MOVIE_REPOSITORY = movieRepository;
        restTemplate = template;
    }

    /**
     * Fetches a list of popular movies from TMDB.
     * <p>
     * This method retrieves a list of popular movies from The Movie Database (TMDB)
     * based on the configured API key. The language is set to "en-US" and the page
     * number is set to 1 (default for popular movies).
     *
     * @return A list of Movie objects containing details about popular movies.
     *         An empty list is returned if an error occurs during the API call.
     * @throws RestClientResponseException  - If an error occurs during the API call
     *                                      (e.g., status code errors).
     * @throws Exception                    - If any other unexpected exception occurs.
     * </p>
     */
    public List<Movie> getAllMovies() {
        String url = Constants.TMDB_BASE_URL + "movie/popular?api_key=" + Constants.API_KEY + "&language=en-US&page=1";

        try {
            TmdbResponse response = restTemplate.getForObject(url, TmdbResponse.class);

            List<Movie> movies = new ArrayList<>();

            if (response != null && response.getResults() != null) {
                for (Tmdb tmdbMovie : response.getResults()) {
                    Movie movie = new Movie();
                    movie.setTitle(tmdbMovie.getTitle());
                    movie.setDirector(tmdbMovie.getDirector());
                    movies.add(movie);
                }
            }

            return movies;
        } catch (RestClientResponseException e) {
            System.err.println("Error occurred while fetching popular movies: " + e.getMessage());
            return Collections.emptyList();
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
            return Collections.emptyList();
        }
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
    public List<Movie> getExactMovieByTitle(String title) {
        String encodedTitle = title.replace(" ", "+");
        String url = Constants.TMDB_BASE_URL + "/search/movie?api_key=" + Constants.API_KEY + "&query=" + encodedTitle;

        try {
            TmdbResponse response = restTemplate.getForObject(url, TmdbResponse.class);

            if (response != null && response.getResults() != null) {
                Optional<Tmdb> foundMovie = response.getResults().stream()
                        .filter(tmdb -> tmdb.getTitle().equalsIgnoreCase(title))
                        .findFirst();

                return foundMovie.map(tmdb -> {
                    Movie movie = new Movie();
                    movie.setTitle(tmdb.getTitle());
                    movie.setDirector(tmdb.getDirector());
                    return Collections.singletonList(movie);
                }).orElse(Collections.emptyList());
            }

            return Collections.emptyList();
        } catch (RestClientResponseException e) {
            throw new RuntimeException("Error fetching movie data", e);
        }
    }

    /**
     * Searches for movies by title on TMDB.
     * <p>
     * This method searches for movies based on the provided title string on The Movie Database (TMDB).
     * The title string is URL encoded before making the API call.
     *
     * @param title - The title of the movie to search for.
     * @return A list of Movie objects containing details about movies matching the search title.
     *         An empty list is returned if an error occurs during the API call or no movies are found.
     * @throws RestClientResponseException  - If an error occurs during the API call
     *                                      (e.g., status code errors).
     * @throws Exception                    - If any other unexpected exception occurs.
     * </p>
     */
    public List<Movie> getMovieByTitle(String title) {
        String encodedTitle = title.replace(" ", "+");
        String url = Constants.TMDB_BASE_URL + "/search/movie?api_key=" + Constants.API_KEY + "&query=" + encodedTitle;

        try {
            TmdbResponse response = restTemplate.getForObject(url, TmdbResponse.class);

            List<Movie> movies = new ArrayList<>();
            if (response != null && response.getResults() != null) {
                for (Tmdb tmdb : response.getResults()) {
                    Movie movie = new Movie();
                    movie.setTitle(tmdb.getTitle());
                    movie.setDirector(tmdb.getDirector());
                    movies.add(movie);
                }
            }
            return movies;
        } catch (RestClientResponseException e) {
            System.err.println("Error occurred while searching movies by title: " + e.getMessage());
            return Collections.emptyList();
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Fetches a list of trending movies on TMDB for a specified time window.
     * <p>
     * This method retrieves a list of trending movies on The Movie Database (TMDB)
     * based on the configured API key and provided time window. The time window
     * parameter can be either "day" or "week".
     *
     * @param time_window - The time window for trending movies (literal string: "day" or "week").
     * @return A list of Movie objects containing details about trending movies.
     *         An empty list is returned if an error occurs during the API call or
     *         if the provided time window is invalid.
     * @throws RestClientResponseException  - If an error occurs during the API call
     *                                      (e.g., status code errors).
     * @throws IllegalArgumentException     - If an invalid time window parameter is provided.
     * @throws Exception                    - If any other unexpected exception occurs.
     * </p>
     */
    public List<Movie> getTrendingMovies(String time_window) {
        //String key = "4c3188597c9125186dfb65289a4bdcb4";
        String url = Constants.TMDB_BASE_URL + "trending/movie/" + time_window + "?api_key=" + Constants.API_KEY;

        try {
            if (!Validation.isValidAPIKey(Constants.API_KEY)) {
                throw new IllegalArgumentException("API KEY cannot be null");
            }
            else {
                if (!Validation.isValidTimeWindow(time_window)) {
                    throw new IllegalArgumentException("Invalid time window provided. Supported values: day, week");
                }

                TmdbResponse response = restTemplate.getForObject(url, TmdbResponse.class);

                List<Movie> movies = new ArrayList<>();

                if (response != null && response.getResults() != null) {
                    for (Tmdb tmdb : response.getResults()) {
                        Movie movie = new Movie();
                        movie.setTitle(tmdb.getTitle());
                        movie.setDirector(tmdb.getDirector());
                        movies.add(movie);
                    }
                }

                return movies;
            }
        } catch (RestClientResponseException e) {
            System.err.println("Error occurred while fetching trending movies: " + e.getMessage());
            return Collections.emptyList();
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid time window provided: " + e.getMessage());
            return Collections.emptyList();
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
