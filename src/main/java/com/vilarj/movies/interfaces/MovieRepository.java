package com.vilarj.movies.interfaces;

import com.vilarj.movies.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> { }
