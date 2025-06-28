package com.amankrmj.portfolio_backend.services;

import com.amankrmj.portfolio_backend.model.contact.details.ContactModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class GithubFilesService {
    private static final Logger logger = LoggerFactory.getLogger(GithubFilesService.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${PORTFOLIO_READ_WRITE:}")
    private String token;

    public boolean hasToken() {
        return token != null && !token.isEmpty();
    }

    public List<ContactModel> getFilesInContacts() {
        String url = "https://api.github.com/repos/amankrmj01/portfolio_asset/contents/contacts/";
        if (!hasToken()) {
            logger.error("GitHub token is not set.");
            throw new RuntimeException("GitHub token is not set.");
        }
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            headers.set("Accept", "application/vnd.github+json");
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                logger.info("Fetched files successfully.");

                // Properly parse the JSON array string into a List
                List<Map<String, Object>> files = objectMapper.readValue(response.getBody(), new com.fasterxml.jackson.core.type.TypeReference<List<Map<String, Object>>>() {
                });
                return printContactFiles(files);
            } else {
                logger.error("Failed to fetch files: {}\nResponse: {}\ntoken: {}", response.getStatusCode(), response.getBody(), token);
                throw new RuntimeException("Failed to fetch files: " + response.getStatusCode());
            }
        } catch (Exception ex) {
            logger.error("Exception occurred while fetching files from GitHub", ex);
            throw new RuntimeException("Exception occurred while fetching files from GitHub: " + ex.getMessage(), ex);
        }
    }

    public List<ContactModel> printContactFiles(List<Map<String, Object>> fileObjects) {
        List<ContactModel> result = new ArrayList<>();
        for (Map<String, Object> fileObj : fileObjects) {
            String apiUrl = (String) fileObj.get("url");
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            headers.set("Accept", "application/vnd.github+json");
            HttpEntity<Void> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                try {
                    JsonNode json = objectMapper.readTree(response.getBody());

                    String contentBase64 = json.get("content").asText().replaceAll("\\n", "");
                    byte[] decodedBytes = Base64.getDecoder().decode(contentBase64);
                    String decodedContent = new String(decodedBytes, StandardCharsets.UTF_8);

                    // Deserialize decoded content to ContactModel and add to result
                    ContactModel contact = objectMapper.readValue(decodedContent, ContactModel.class);
                    result.add(contact);
                } catch (Exception e) {
                    logger.error("Failed to decode or parse contact file", e);
                    // Optionally, skip or add null/empty ContactModel
                }
            } else {
                // Optionally, skip or add null/empty ContactModel
            }
        }
        return result;
    }
}
