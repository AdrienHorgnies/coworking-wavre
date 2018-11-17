package com.ifosup.coworking.security;

import com.ifosup.coworking.CoworkingProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfigurer {

    private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    private final CoworkingProperties coworkingProperties;

    public WebConfigurer(CoworkingProperties coworkingProperties) {
        this.coworkingProperties = coworkingProperties;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = coworkingProperties.getCors();
        if (corsConfiguration.getAllowedOrigins() != null && !corsConfiguration.getAllowedOrigins().isEmpty()) {
            log.debug("Registering CORS filter");
            source.registerCorsConfiguration("/api/**", corsConfiguration);
        }
        return new CorsFilter(source);
    }

}
