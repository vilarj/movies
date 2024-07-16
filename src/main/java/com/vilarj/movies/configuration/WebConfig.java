package com.vilarj.movies.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Web MVC configuration class.
 * <summary>
 * This class implements the WebMvcConfigurer interface and provides CORS (Cross-Origin Resource Sharing)
 * configuration for the application. It allows requests from the specified origin ("http://localhost:3000")
 * to access resources on this server. You might need to adjust this configuration based on your specific
 * deployment environment and security requirements.
 * </summary>
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:3000");
    }
}
