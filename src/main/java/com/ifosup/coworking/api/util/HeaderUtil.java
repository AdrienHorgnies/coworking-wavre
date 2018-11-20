package com.ifosup.coworking.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 */
public final class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        log.error("Entity processing failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-coworkingApp-error-message", defaultMessage);
        headers.add("X-coworkingApp-error-key", errorKey);
        headers.add("X-coworkingApp-params", entityName);
        return headers;
    }
}

