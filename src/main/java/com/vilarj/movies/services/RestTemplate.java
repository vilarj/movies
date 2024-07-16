package com.vilarj.movies.services;

import org.springframework.context.annotation.Bean;

public class RestTemplate {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
