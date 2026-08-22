package com.tracking.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private final String[] allowedOrigins;
    private final RequestTimeZoneHolder requestTimeZoneHolder;

    public CorsConfig(@Value("${app.cors.allowed-origins}") String allowedOrigins,
                       RequestTimeZoneHolder requestTimeZoneHolder) {
        this.allowedOrigins = allowedOrigins.split(",");
        this.requestTimeZoneHolder = requestTimeZoneHolder;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("GET", "POST")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimezoneInterceptor(requestTimeZoneHolder));
    }
}
