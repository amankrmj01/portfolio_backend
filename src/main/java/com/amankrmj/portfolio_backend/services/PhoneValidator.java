package com.amankrmj.portfolio_backend.services;

import com.amankrmj.portfolio_backend.model.phone.PhoneValidationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PhoneValidator extends AbstractValidator<PhoneValidationResponse> {
    private static final Logger logger = LoggerFactory.getLogger(PhoneValidator.class);
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${ABSTRACT_PHONE:}")
    private String apiKey;

    @Override
    public PhoneValidationResponse validate(String value) {
        if (apiKey == null || apiKey.isEmpty()) {
            logger.error("ABSTRACT_PHONE token is not set.");
            throw new RuntimeException("ABSTRACT_PHONE token is not set.");
        }
        try {
            logger.info("\nphone validation started for value: {}", value);
            String url = UriComponentsBuilder.newInstance()
                    .scheme("https")
                    .host("phonevalidation.abstractapi.com")
                    .path("/v1/")
                    .queryParam("api_key", apiKey)
                    .queryParam("phone", value.substring(1))
                    .build()
                    .toUriString();
            ResponseEntity<PhoneValidationResponse> response = restTemplate.getForEntity(url, PhoneValidationResponse.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                logger.info("Phone validated successfully.");
                return response.getBody();
            } else {
                logger.error("Failed to validate phone. Status: {}", response.getStatusCode());
                throw new RuntimeException("Failed to validate phone. Status: " + response.getStatusCode());
            }
        } catch (Exception ex) {
            logger.error("Exception occurred during phone validation", ex);
            throw new RuntimeException("Exception occurred during phone validation: " + ex.getMessage(), ex);
        }
    }
}
