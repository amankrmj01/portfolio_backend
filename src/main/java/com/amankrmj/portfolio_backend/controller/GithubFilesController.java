package com.amankrmj.portfolio_backend.controller;

import com.amankrmj.portfolio_backend.model.contact.details.ContactModel;
import com.amankrmj.portfolio_backend.services.GithubFilesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/files")
public class GithubFilesController {

    private static final Logger logger = LoggerFactory.getLogger(GithubFilesController.class);
    private final GithubFilesService githubFilesService;

    public GithubFilesController(GithubFilesService githubFilesService) {
        this.githubFilesService = githubFilesService;
    }

    @GetMapping("/contacts")
    public List<ContactModel> getFilesInContacts(@RequestHeader(value = "User-Agent", required = false) String userAgent, jakarta.servlet.http.HttpServletRequest request) {
        String remoteAddress = request.getHeader("X-Forwarded-For");
        if (remoteAddress == null || remoteAddress.isEmpty() || "unknown".equalsIgnoreCase(remoteAddress)) {
            remoteAddress = request.getRemoteAddr();
        } else {
            // X-Forwarded-For can contain multiple IPs, take the first one
            remoteAddress = remoteAddress.split(",")[0].trim();
        }
        logger.info("Request received from User-Agent: {}, Address: {}", userAgent, remoteAddress);
        return githubFilesService.getFilesInContacts();
    }
}
