package com.amankrmj.portfolio_backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ping")
public class PingController {
    private static final Logger logger = LoggerFactory.getLogger(PingController.class);

    @GetMapping()
    public ResponseEntity<Map<String, String>> ping(
            @RequestHeader(value = "User-Agent", required = false) String userAgent,
            HttpServletRequest request) {
        String remoteAddress = request.getHeader("X-Forwarded-For");
        if (remoteAddress == null || remoteAddress.isEmpty() || "unknown".equalsIgnoreCase(remoteAddress)) {
            remoteAddress = request.getRemoteAddr();
        } else {
            // X-Forwarded-For can contain multiple IPs, take the first one
            remoteAddress = remoteAddress.split(",")[0].trim();
        }
        logger.info("Request received from User-Agent: {}, Address: {}", userAgent, remoteAddress);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Server is awake and ready to serve! 🚀");
        response.put("address", remoteAddress);

        return ResponseEntity.ok(response);
    }
}
