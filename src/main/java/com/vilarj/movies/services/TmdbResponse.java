package com.vilarj.movies.services;

import java.util.List;

public class TmdbResponse {
    private List<Tmdb> results;

    public List<Tmdb> getResults() {
        return results;
    }

    public void setResults(List<Tmdb> results) {
        this.results = results;
    }
}
