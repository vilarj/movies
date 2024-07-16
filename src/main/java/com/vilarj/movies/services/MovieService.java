package com.vilarj.movies.services;

import com.vilarj.movies.entities.Movie;
import com.vilarj.movies.interfaces.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository MOVIE_REPOSITORY;

    @Value("${tmdb.api.key}")
    private String API_KEY;
//    private final String API_READ_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YzMxODg1OTdjOTEyNTE4NmRmYjY1Mjg5YTRiZGNiNCIsIm5iZiI6MTcyMTEzMDAxNy40MTE3MTEsInN1YiI6IjY2OTY1YjgzNTcwZmNiYmFmOGQ1ZmMwYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.1jBqE2Qp__lojmnm6dF9ga0utXQh6BybUuhIyI2ohBE";
    private RestTemplate restTemplate;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.MOVIE_REPOSITORY = movieRepository;
        restTemplate = new RestTemplate();
    }

    public List<Movie> getAllMovies() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY + "&language=en-US&page=1";

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
    }

    public List<Movie> getMovieByTitle(String title) {
        String encodedTitle = title.replace(" ", "+");
        String url = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&query=" + encodedTitle;

        // Make the API call
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
}
