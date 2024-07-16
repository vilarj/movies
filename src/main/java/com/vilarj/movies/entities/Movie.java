package com.vilarj.movies.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a Movie entity for persistence in a JPA-managed database.
 * <summary>
 * This class defines the attributes (title, director) of a movie and is annotated
 * with JPA annotations to indicate it's a persistent entity. The `id` field is the primary
 * key (auto-generated using IDENTITY strategy).
 * </summary>
 */
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String director;

    /**
     * Gets the auto-generated ID of the movie entity.
     *
     * @return The ID of the movie.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the movie entity (for potential manual setting).
     *
     * @param id - The ID to set for the movie.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the title of the movie.
     *
     * @return The title of the movie.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the movie.
     *
     * @param title - The title to set for the movie.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the director of the movie.
     *
     * @return The director of the movie (if set).
     */
    public String getDirector() {
        return director;
    }

    /**
     * Sets the director of the movie.
     *
     * @param director - The director to set for the movie.
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Default constructor for Movie entity.
     */
    public Movie() {

    }

    /**
     * Constructor for Movie entity with title and director.
     *
     * @param title - The title of the movie.
     * @param director - The director of the movie.
     */
    public Movie(String title, String director) {
        this.title = title;
        this.director = director;
    }
}
