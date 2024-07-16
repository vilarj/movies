package com.vilarj.movies.services;

import java.util.List;

/**
 * Represents the structure of a response from the TMDB API.
 * <p>
 * This class is used to map the JSON response received from the TMDB API.
 * It contains a list of `Tmdb` objects, each representing a movie with
 * basic details like title and director (depending on the specific TMDB API endpoint).
 * </p>
 */
public class TmdbResponse {
    private List<Tmdb> results;

    public List<Tmdb> getResults() {
        return results;
    }

    public void setResults(List<Tmdb> results) {
        this.results = results;
    }
}
