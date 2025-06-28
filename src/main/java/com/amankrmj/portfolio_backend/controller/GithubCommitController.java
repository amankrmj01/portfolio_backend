package com.amankrmj.portfolio_backend.controller;

import com.amankrmj.portfolio_backend.model.contact.details.ContactModel;
import com.amankrmj.portfolio_backend.model.email.EmailValidationResponse;
import com.amankrmj.portfolio_backend.model.github.Committer;
import com.amankrmj.portfolio_backend.model.github.GithubCommitModel;
import com.amankrmj.portfolio_backend.services.EmailValidator;
import com.amankrmj.portfolio_backend.services.GithubCommitService;
import com.amankrmj.portfolio_backend.services.PhoneValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@RestController
@RequestMapping("/add")
public class GithubCommitController {

    private final GithubCommitService githubCommitService;
    private final EmailValidator emailValidatorService;
    private final PhoneValidator phoneValidatorService;

    public GithubCommitController(GithubCommitService githubCommitService, EmailValidator emailValidatorService, PhoneValidator phoneValidatorService) {
        this.githubCommitService = githubCommitService;
        this.emailValidatorService = emailValidatorService;
        this.phoneValidatorService = phoneValidatorService;
    }


    @PutMapping("/file")
    public ResponseEntity<String> createOrUpdateFile(@RequestParam String path, @RequestBody GithubCommitModel commit) {
        return githubCommitService.createOrUpdateFile(path, commit);
    }

    @PostMapping("/contact")
    public ResponseEntity<String> commitContactForm(@RequestBody ContactModel contactForm) {
        // Validate phone number
        boolean isPhoneValid = phoneValidatorService.validate(contactForm.getCountryCode() + contactForm.getPhoneNumber()).isValid();

//        (contactForm.getPhoneNumber(), contactForm.getCountryCode());
        if (!isPhoneValid) {
            return ResponseEntity.badRequest().body("{\"error\":\"Invalid phone number\"}");
        }
        // Validate email
        EmailValidationResponse emailValidationResponse = emailValidatorService.validate(contactForm.getEmail());
        if (!emailValidationResponse.getIs_valid_format().isValue()) {
            return ResponseEntity.badRequest().body("{\"error\":\"Invalid email address\"}");
        }
        if (emailValidationResponse.getIs_disposable_email().isValue()) {
            return ResponseEntity.badRequest().body("{\"error\":\"Disposable email address not allowed\"}");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            String contactJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(contactForm);
            String contentBase64 = Base64.getEncoder().encodeToString(contactJson.getBytes(StandardCharsets.UTF_8));
            String name = contactForm.getName() != null ? contactForm.getName() : "Unknown";
            String email = contactForm.getEmail() != null ? contactForm.getEmail() : "unknown@example.com";
            GithubCommitModel commitModel = new GithubCommitModel(
                    name + " wants to contact you",
                    contentBase64,
                    new Committer(name, email)
            );
            String formatted = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filePath = "contact" + formatted + ".json";
            return githubCommitService.createOrUpdateFile(filePath, commitModel);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to process contact form: " + e.getMessage());
        }
    }
}
