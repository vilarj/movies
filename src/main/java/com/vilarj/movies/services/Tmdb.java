package com.vilarj.movies.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents a basic movie data structure corresponding to the TMDB API response format.
 * <p>
 * This class is used to map the relevant movie data fields (title, director) from the JSON response
 * received from the TMDB API. The @JsonIgnoreProperties annotation from Jackson Framework instructs
 * the ObjectMapper to ignore any unknown properties present in the JSON response that don't have
 * corresponding fields in this class.
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tmdb {
    private String title;
    private String director;

    public String getTitle() {
        return title;
    }

    /**
     * Gets the title of the movie from the TMDB response.
     *
     * @return The title of the movie.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the director of the movie from the TMDB response.
     *
     * @return The director of the movie (if available in the response).
     */
    public String getDirector() {
        return director;
    }

    /**
     * Sets the director of the movie.
     *
     * @param director - The director of the movie to set.
     */
    public void setDirector(String director) {
        this.director = director;
    }
}
