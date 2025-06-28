package com.amankrmj.portfolio_backend.services;

import com.amankrmj.portfolio_backend.model.email.EmailValidationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EmailValidator extends AbstractValidator<EmailValidationResponse> {
    private static final Logger logger = LoggerFactory.getLogger(EmailValidator.class);
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${ABSTRACT_EMAIL:}")
    private String apiKey;

    @Override
    public EmailValidationResponse validate(String value) {
        if (apiKey == null || apiKey.isEmpty()) {
            logger.error("ABSTRACT_EMAIL token is not set.");
            throw new RuntimeException("ABSTRACT_EMAIL token is not set.");
        }
        try {
            logger.info("\nemail validation started for: {}", value);
            String url = UriComponentsBuilder.newInstance()
                    .scheme("https")
                    .host("emailvalidation.abstractapi.com")
                    .path("/v1/")
                    .queryParam("api_key", apiKey)
                    .queryParam("email", value)
                    .build()
                    .toUriString();
            ResponseEntity<EmailValidationResponse> response = restTemplate.getForEntity(url, EmailValidationResponse.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                logger.info("Email validated successfully.");
                return response.getBody();
            } else {
                logger.error("Failed to validate email. Status: {}", response.getStatusCode());
                throw new RuntimeException("Failed to validate email. Status: " + response.getStatusCode());
            }
        } catch (Exception ex) {
            logger.error("Exception occurred during email validation", ex);
            throw new RuntimeException("Exception occurred during email validation: " + ex.getMessage(), ex);
        }
    }
}
