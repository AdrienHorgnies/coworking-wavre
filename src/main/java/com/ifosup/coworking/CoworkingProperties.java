package com.ifosup.coworking;

import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * Properties specific to Coworking project.
 *
 * <p>
 * Properties are configured in the application.yml file.
 * </p>
 */
@Configuration
@ConfigurationProperties(prefix = "coworking", ignoreUnknownFields = false)
@Validated
public class CoworkingProperties {

    @Valid
    private final Security security = new Security();

    public Security getSecurity() {
        return security;
    }

    public static class Security {

        @Length(min = 32)
        private String secret;

        // 1 hour
        @Min(3600)
        private Long jwtValidityInSeconds;

        // 24 hours
        @Min(86400)
        private Long jwtValidityInSecondsForRememberMe;

        @Min(8)
        private Integer minPasswordLength = 8;

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public Long getJwtValidityInSeconds() {
            return jwtValidityInSeconds;
        }

        public void setJwtValidityInSeconds(Long jwtValidityInSeconds) {
            this.jwtValidityInSeconds = jwtValidityInSeconds;
        }

        public Long getJwtValidityInSecondsForRememberMe() {
            return jwtValidityInSecondsForRememberMe;
        }

        public void setJwtValidityInSecondsForRememberMe(Long jwtValidityInSecondsForRememberMe) {
            this.jwtValidityInSecondsForRememberMe = jwtValidityInSecondsForRememberMe;
        }

        public Integer getMinPasswordLength() {
            return minPasswordLength;
        }

        public void setMinPasswordLength(Integer minPasswordLength) {
            this.minPasswordLength = minPasswordLength;
        }
    }
}
