package com.vilarj.movies.services;

import org.springframework.context.annotation.Bean;

public class RestTemplate {
    /**
     * Creates a RestTemplate bean for making HTTP requests to external APIs.
     * <summary>
     * This bean is used by the MovieService to interact with the TMDB API and
     * retrieve movie data. It provides a simple and template-based approach for
     * making HTTP requests and handling responses.
     *
     * @return A RestTemplate bean instance.
     * </summary>
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
