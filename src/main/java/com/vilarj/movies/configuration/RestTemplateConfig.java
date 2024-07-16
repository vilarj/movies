package com.vilarj.movies.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Spring configuration class for creating a RestTemplate bean.
 * <summary>
 * This class defines a @Bean method named `restTemplate` that creates and returns a new instance of
 * RestTemplate. RestTemplate is a class from Spring Web that simplifies making HTTP requests to external APIs.
 * This bean can be injected into other beans or services that need to make API calls.
 * </summary>
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
