package com.vilarj.movies.interfaces;

import com.vilarj.movies.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for interacting with Movie entities.
 * <p>
 * This interface extends JpaRepository provided by Spring Data JPA, offering methods
 * to perform basic CRUD (Create, Read, Update, Delete) operations on Movie entities
 * persisted in a JPA-managed database. The primary key type for Movie entities is assumed
 * to be Long.
 * </p>
 */
public interface MovieRepository extends JpaRepository<Movie, Long> { }
