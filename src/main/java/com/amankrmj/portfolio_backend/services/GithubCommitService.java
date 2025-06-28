package com.amankrmj.portfolio_backend.services;

import com.amankrmj.portfolio_backend.model.github.GithubCommitModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubCommitService {
    private static final Logger logger = LoggerFactory.getLogger(GithubCommitService.class);
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${PORTFOLIO_READ_WRITE:}")
    private String token;

    public boolean hasToken() {
        return token != null && !token.isEmpty();
    }

    public ResponseEntity<String> createOrUpdateFile(String path, GithubCommitModel commit) {
        if (!hasToken()) {
            logger.error("GitHub token is not set.");
            throw new RuntimeException("GitHub token is not set.");
        }
        try {
            String url = "https://api.github.com/repos/amankrmj01/portfolio_asset/contents/contacts/" + path;
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            headers.set("Accept", "application/vnd.github+json");
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<GithubCommitModel> entity = new HttpEntity<>(commit, headers);
            return restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        } catch (Exception e) {
            logger.error("Failed to create or update file on GitHub", e);
            throw new RuntimeException("Failed to create or update file on GitHub", e);
        }
    }
}
